package com.staffapp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToOne(mappedBy = "location", fetch = FetchType.EAGER)
  private Employee employee;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
