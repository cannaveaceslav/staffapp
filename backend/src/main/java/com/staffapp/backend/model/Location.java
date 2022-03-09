package com.staffapp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@Entity
@Builder
@Table(name = "APP_LOCATION")
public class Location {
  @SequenceGenerator
          (name = "APP_LOCATION_SEQUENCE",
                  sequenceName = "APP_LOCATION_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "APP_LOCATION_SEQUENCE")
  private Long id;
  @Column(nullable = false, unique = true)
  @SequenceGenerator
          (name = "APP_LOCATION_NUMBER_SEQUENCE",
                  sequenceName = "APP_LOCATION_NUMBER_SEQUENCE",
                  allocationSize = 1)
  private Long locationNumber;
  private Boolean available = true;
  private String description;
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "app_employee_id")
  private Employee employee;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  @Column(columnDefinition = "NUMBER")
  private Double pos_x;
  @Column(columnDefinition = "NUMBER")
  private Double pos_y;
}
