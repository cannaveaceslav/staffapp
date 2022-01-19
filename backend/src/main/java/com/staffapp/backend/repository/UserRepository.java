package com.staffapp.backend.repository;


import com.staffapp.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
  Optional<User> findByEmailAndPassword(String email, String password);

  @Transactional
  @Modifying
  @Query("UPDATE User c " +
          "SET c.enabled = true " +
          "WHERE c.email = ?1")
  void updateUserEnable(String email);

  @Transactional
  @Modifying
  @Query("UPDATE User c " +
          "SET c.firstName = ?1 , " +
          "c.lastName = ?2 ," +
          "c.password = ?3 " +
          "WHERE c.email = ?4 ")
  void updateUserById(String firstName, String lastName, String password, String email);


}
