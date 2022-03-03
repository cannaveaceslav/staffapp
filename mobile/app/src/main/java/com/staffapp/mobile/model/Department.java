package com.staffapp.mobile.model;

import java.time.LocalDateTime;

public class Department {
    private Long id;
    private String departmentName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean enabled = true;

    public Department(Long id, String departmentName, String description, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean enabled) {
        this.id = id;
        this.departmentName = departmentName;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
