package com.staffapp.backend.service.employee;

import com.staffapp.backend.model.Employee;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.repository.EmployeeRepository;
import com.staffapp.backend.repository.LocationRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public Employee create(Employee employee) {
        log.info("Saving new  employee [{}]", employee.getLastName() + " " + employee.getFirstName());
        employeeRepository.save(employee);
        if (employee.getLocation() != null) {
            Location location = employee.getLocation();
            location.setAvailable(false);
            location.setEmployee(employee);
            locationRepository.save(location);
        }
        return employee;
    }

    @Transactional
    public Collection<Employee> list() throws InterruptedException {
        log.info("Fetching all employees");
//    return employeeRepository.findAll(PageRequest.of(0, limit, Sort.by("lastName"))).toList();
        return employeeRepository.findAll(Sort.by("lastName"));
    }

    @Transactional
    public Optional<Employee> getById(Long id) {
        log.info("Getting employee by id [{}]", id);
        return Optional.of(employeeRepository.getById(id));
    }

    @Transactional
    public Employee update(@NotNull Employee employee) {
        log.info("Updating employee [{}], [{}]", employee.getLastName(), employee.getFirstName());
        enableOldLocations(employee);
        if (employee.getLocation() != null) {
            Location location = employee.getLocation();
            location.setAvailable(false);
            location.setEmployee(employee);
            locationRepository.save(location);
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Boolean delete(Long id) {
        log.info("Deleting employee with id: [{}]", id);
        employeeRepository.deleteById(id);
        return Boolean.TRUE;
    }


    private void enableOldLocations(Employee employee){
        List<Location> locationList = locationRepository.findLocationsByEmployee_Id(employee.getId());
        log.info("Found [{}] old locations",locationList.size());
        if (!locationList.isEmpty()){
            for (Location location: locationList){
                log.info("Setting enable to location "+location.getLocationNumber());
                locationRepository.enableLocation(location.getId());
            }

        }
    }
}
