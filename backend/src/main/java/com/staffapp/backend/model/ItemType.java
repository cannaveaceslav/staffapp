package com.staffapp.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "APP_ITEMTYPE")
public class ItemType {
  @SequenceGenerator
          (name = "APP_ITEMTYPE_SEQUENCE",
                  sequenceName = "APP_ITEMTYPE_SEQUENCE",
                  allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "APP_ITEMTYPE_SEQUENCE")
  private Long id;
  @Column(nullable = false)
  private String typeName;
  private String description;
  @Lob
  private byte[] image;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Boolean enabled = true;

}








