package com.staffapp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity
@Table(name = "APP_EMPLOYEE")
public class Employee {

    @SequenceGenerator
            (name = "APP_EMPLOYEE_SEQUENCE",
                    sequenceName = "APP_EMPLOYEE_SEQUENCE",
                    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "APP_EMPLOYEE_SEQUENCE")
    private Long id;
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
    @ManyToOne
    @JoinColumn(name = "app_department_id",
            nullable = false)
    private Department department;
    @Column(nullable = false)
    private LocalDateTime birthday;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean enabled = true;
    @Lob
    private byte[] image;
//    @OneToOne(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "employee"
//    )
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_location_id")
    private Location location;


}
