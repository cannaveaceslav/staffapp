package com.staffapp.backend.model;


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
@Table(name = "APP_DEPARTMENT")
public class Department {
    @SequenceGenerator
            (name = "APP_DEPARTMENT_SEQUENCE",
                    sequenceName = "APP_DEPARTMENT_SEQUENCE",
                    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "APP_DEPARTMENT_SEQUENCE")
    private Long id;
    @Column(nullable = false)
    private String departmentName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean enabled = true;

}








