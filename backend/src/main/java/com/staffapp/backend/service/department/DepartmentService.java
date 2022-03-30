package com.staffapp.backend.service.department;

import com.staffapp.backend.model.Department;
import com.staffapp.backend.model.ItemType;
import com.staffapp.backend.repository.DepartmentRepository;
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
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Collection<Department> list() {
        log.info("Fetching all Departments");
        return departmentRepository.findAll();
    }

    public Department create(@NotNull Department department) {
        log.info("Saving new Department [{}]", department.getDepartmentName());
        return departmentRepository.save(department);
    }

    public Optional<Department> getById(Long id) {
        log.info("Getting Department by id [{}]", id);
        return Optional.of(departmentRepository.getById(id));
    }

    public Department update(@NotNull Department department) {
        log.info("Updating Department [{}]", department.getDepartmentName());
        return departmentRepository.save(department);
    }

    public Boolean delete(Long id) {
        log.info("Deleting Department [{}]", id);
        departmentRepository.deleteById(id);
        return Boolean.TRUE;
    }

}
