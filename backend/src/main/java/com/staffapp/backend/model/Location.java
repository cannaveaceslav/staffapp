package com.staffapp.backend.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APP_LOCATION")
public class Location {
  @SequenceGenerator
          //todo
          (name = "APP_LOCATION_SEQUENCE",
                  sequenceName = "APP_LOCATION_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "APP_LOCATION_SEQUENCE")
  private Long id;
  @Column(nullable = false, unique = true)
  private Long locationNumber;
  private Boolean available = true;
  private String description;
  @OneToOne(mappedBy = "location")
  private Employee employee;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
