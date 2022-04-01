package com.staffapp.backend.service.user;

import com.staffapp.backend.model.User;
import com.staffapp.backend.repository.UserRepository;
import com.staffapp.backend.model.ConfirmationToken;
import com.staffapp.backend.service.registration.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String token = UUID.randomUUID().toString();

        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            log.info("User with email [{}] exists. Check if user is enabled.", user.getEmail());
            boolean userIsEnabled = userRepository
                    .findByEmail(user.getEmail()).get().isEnabled();

            if (!userIsEnabled) {
                log.info("User with email  [{}] is disabled", user.getEmail());
                user.setId(userRepository.findByEmail(user.getEmail()).get().getId());
                userRepository.save(user);
                log.info("User with email [{}] was updated.", user.getEmail());
                confirmationTokenService.saveConfirmationToken(generateConfirmationToken(user, token));
                return token;
            } else {
                //TODO fix logs with object
                log.info("User with email [{}] is already enabled. Registration failed.", user.getEmail());
                throw new IllegalStateException("email already taken");
            }
        } else {
            userRepository.save(user);
            log.info("Created new user with email [{}].", user.getEmail());
            confirmationTokenService.saveConfirmationToken(generateConfirmationToken(user, token));
            return token;
        }
    }

    public void enableUser(String userName) {
        userRepository.updateUserEnable(userName);
    }

    private ConfirmationToken generateConfirmationToken(User user, String token) {

        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }

    public Optional<User> loadUserByUsernameAndPassword(String email, String plainPassword) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        boolean matches = false;

        if (optionalUser.isPresent()) {
            String encodedPassword = optionalUser.get().getPassword();
            matches = bCryptPasswordEncoder.matches(plainPassword, encodedPassword);
        }

        if (matches) {
            return optionalUser;

        } else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }

    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User create(@NotNull User user) {
        log.info("Creating new user with email [{}]", user.getEmail());
        return userRepository.save(user);
    }

    public Collection<User> list() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public User getById(Long id) {
        log.info("Getting user by id [{}]", id);
        return userRepository.getById(id);
    }

    public User update(@NotNull User user) {
        log.info("Updating user with email[{}]", user.getEmail());
        return userRepository.save(user);
    }

    public Boolean deleteById(Long id) {
        log.info("Deleting user with ID [{}]", id);
        userRepository.deleteById(id);
        return Boolean.TRUE;
    }


}
