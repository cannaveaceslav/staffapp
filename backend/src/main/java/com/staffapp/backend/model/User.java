package com.staffapp.backend.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
public class User implements UserDetails {
  @SequenceGenerator
          //todo
          (name = "APP_USER_SEQUENCE",
                  sequenceName = "APP_USER_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "APP_USER_SEQUENCE")
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private UserRole userRole;
  private Boolean locked;
  private Boolean enabled;

  public User(String firstName,
              String lastName,
              String email,
              String password,
              UserRole userRole) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userRole = userRole;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
    return Collections.singletonList(simpleGrantedAuthority);
  }

  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
