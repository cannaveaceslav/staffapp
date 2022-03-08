package com.staffapp.backend.service.company;

import com.staffapp.backend.model.Company;
import com.staffapp.backend.model.ItemType;
import com.staffapp.backend.repository.CompanyRepository;
import com.staffapp.backend.repository.ItemTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Collection<Company> list() {
        log.info("Fetching all Companies");
        return companyRepository.findAll();
    }

    public Company create(@NotNull Company company) {
        log.info("Saving new Company [{}]", company.getCompanyName());
        return companyRepository.save(company);
    }

    public Optional<Company> getById(Long id) {
        log.info("Getting Company by id [{}]", id);
        return Optional.of(companyRepository.getById(id));
    }

    public Company update(@NotNull Company company) {
        log.info("Updating Company [{}]", company.getCompanyName());
        return companyRepository.save(company);
    }

    public Boolean delete(Long id) {
        log.info("Deleting Company [{}]", id);
        companyRepository.deleteById(id);
        return Boolean.TRUE;
    }

}
