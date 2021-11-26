package com.staffapp.backend.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "APP_EMPLOYEE_HIST")
public class Employee_Hist {

  @SequenceGenerator
          (name = "APP_EMPLOYEE_HIST_SEQUENCE",
                  sequenceName = "APP_EMPLOYEE_HIST_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "APP_EMPLOYEE_HIST_SEQUENCE")
  private Long id;
  private Long employeeId;
  @ManyToOne
  @JoinColumn(name = "app_company_id",
          nullable = false)
  private Company company;
  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false)
  private String email;
  @Enumerated(EnumType.STRING)
  private Department department;
  @Column(nullable = false)
  private LocalDateTime birthday;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;
  private Boolean enabled = false;
  @Lob
  private byte[] image;
  @OneToOne(mappedBy = "employee")
  private Location location;

}
