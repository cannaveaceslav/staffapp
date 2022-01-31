package com.staffapp.backend.service.employee;

import com.staffapp.backend.model.Company;
import com.staffapp.backend.model.Department;
import com.staffapp.backend.model.Employee;
import com.staffapp.backend.repository.EmployeeRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class EmployeeServiceTest {
  @Mock
  private EmployeeRepository employeeRepository;
  @Mock
  private Company testCompany;
  @Mock
  private Department testDepartment;
  private EmployeeService employeeService;
  private Employee testEmployee;
  private Random random;


  @BeforeEach
  void setUp() {
    employeeService = new EmployeeService(employeeRepository);
    random = new Random();
    testEmployee = Employee.builder()
            .id((long) random.nextInt(1000))
            .firstName("testEmployee")
            .lastName("testEmployee")
            .email("testemployeeemail@mail.ru")
            .birthday(LocalDateTime.of(1989, Month.OCTOBER, 3, 12, 30))
            .enabled(true)
            .company(testCompany)
            .department(testDepartment)
            .createdAt(LocalDateTime.now())
            .build();
  }

  @Test
  void assertThatEmployeeIsCreated() {
    when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

    assertEquals(testEmployee, employeeService.create(testEmployee));
    verify(employeeRepository, times(1)).save(testEmployee);

  }


  @SneakyThrows
  @Test
  void assertThatReturnsListOfEmployees() {
    List<Employee> expectedResult = new ArrayList<>(Collections.singletonList(testEmployee));
    when(employeeRepository.findAll(Sort.by("lastName"))).thenReturn(expectedResult);
    Collection<Employee> actualResult = employeeService.list();

    verify(employeeRepository).findAll(Sort.by("lastName"));
    assertTrue(actualResult.containsAll(expectedResult));
    assertEquals(1, actualResult.size());
  }

  @Test
  void assertThatReturnsProperEmployee() {
    when(employeeRepository.getById(any(Long.class))).thenReturn(testEmployee);

    assertEquals(testEmployee, employeeService.getById(testEmployee.getId()).get());
    verify(employeeRepository).getById(testEmployee.getId());
  }

  @Test
  void assertThatReturnsTheSameEmployeeAfterUpdate() {
    when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);
    Employee actualResult = employeeService.update(testEmployee);

    verify(employeeRepository, times(1)).save(testEmployee);
    assertEquals(testEmployee, actualResult);
  }

  @Test
   void assertThatEmployeeIsDeleted() {
    employeeService.delete(testEmployee.getId());

    verify(employeeRepository, times(1)).deleteById(testEmployee.getId());
  }
}