package com.staffapp.backend.model;

import com.staffapp.backend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
  @SequenceGenerator
          //todo
          (name = "CONFIRMATION_TOKEN_SEQUENCE",
                  sequenceName = "CONFIRMATION_TOKEN_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "CONFIRMATION_TOKEN_SEQUENCE")
  private Long id;
  @Column(nullable = false)
  private String token;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @Column(nullable = false)
  private LocalDateTime expiresAt;
  private LocalDateTime confirmedAt;
  @ManyToOne
  @JoinColumn(name = "app_user_id",
          nullable = false)
  private User user;


  public ConfirmationToken(String token,
                           LocalDateTime createdAt,
                           LocalDateTime expiresAt,
                           User user) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.user = user;
  }
}
