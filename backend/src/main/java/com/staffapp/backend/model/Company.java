package com.staffapp.backend.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Table(name = "APP_COMPANY")
public class Company {
  @SequenceGenerator
          (name = "APP_COMPANY_SEQUENCE",
                  sequenceName = "APP_COMPANY_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "APP_COMPANY_SEQUENCE")
  private Long id;
  @Column(nullable = false)
  private String companyName;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Boolean enabled = true;


}
