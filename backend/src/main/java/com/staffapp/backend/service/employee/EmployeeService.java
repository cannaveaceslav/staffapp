package com.staffapp.backend.service.employee;

import com.staffapp.backend.model.Employee;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        log.info("Saving new  employee [{}]", employee.getLastName() + " " + employee.getFirstName());
        return employeeRepository.save(employee);
    }

    public Collection<Employee> list(int limit) {
        log.info("Fetching all employees");
        return employeeRepository.findAll(PageRequest.of(0, limit, Sort.by("lastName"))).toList();
    }
}
