package com.staffapp.backend.repository;

import com.staffapp.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
