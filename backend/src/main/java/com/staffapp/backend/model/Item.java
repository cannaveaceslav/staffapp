package com.staffapp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "APP_ITEM")
public class Item {
    @SequenceGenerator
            (name = "APP_ITEM_SEQUENCE",
                    sequenceName = "APP_ITEM_SEQUENCE",
                    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "APP_ITEM_SEQUENCE")
    private Long id;


    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate manufacturedAt;
    @Column(nullable = false, unique = true)
    private String barcode;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean enabled = true;
    @Lob
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "app_itemtype_id", nullable = false)
    private ItemType itemType;
    @ManyToOne
    @JoinColumn(name = "app_employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "app_location_id")
    private Location location;

}
