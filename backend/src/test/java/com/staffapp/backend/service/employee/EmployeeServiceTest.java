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
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class EmployeeServiceTest {
  @Mock
  private EmployeeRepository employeeRepositoryMock;
  @SpyBean
  private EmployeeRepository employeeRepositorySpy;
  @Mock
  private Company testCompany;
  @Mock
  private Department testDepartment;
  private EmployeeService employeeServiceWithMock;
  private EmployeeService employeeServiceWithSpy;
  private Employee testEmployee;


  @BeforeEach
  void setUp() {
    employeeServiceWithMock = new EmployeeService(employeeRepositoryMock);
    employeeServiceWithSpy = new EmployeeService(employeeRepositorySpy);
    Random random = new Random();

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
    when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(testEmployee);

    assertEquals(testEmployee, employeeServiceWithMock.create(testEmployee));
    verify(employeeRepositoryMock, times(1)).save(testEmployee);

  }


  @SneakyThrows
  @Test
  void assertThatReturnsListOfEmployeesWithMock() {
    List<Employee> expectedResult = new ArrayList<>(Collections.singletonList(testEmployee));
    when(employeeRepositoryMock.findAll(Sort.by("lastName"))).thenReturn(expectedResult);
    Collection<Employee> actualResult = employeeServiceWithMock.list();

    verify(employeeRepositoryMock).findAll(Sort.by("lastName"));
    assertTrue(actualResult.containsAll(expectedResult));
    assertEquals(1, actualResult.size());
  }

  @SneakyThrows
  @Test
  void assertThatReturnsListOfEmployeesWithSpy() {
    List<Employee> expectedResult = new ArrayList<>(Collections.singletonList(testEmployee));
    Collection<Employee> actualResult = employeeServiceWithSpy.list();

    verify(employeeRepositorySpy).findAll(Sort.by("lastName"));
    assertNotEquals(actualResult.size(), expectedResult.size());

    when(employeeRepositorySpy.findAll(Sort.by("lastName"))).thenReturn(Arrays.asList(testEmployee,testEmployee));
    assertEquals(2,employeeServiceWithSpy.list().size());
  }

  @Test
  void assertThatReturnsProperEmployee() {
    when(employeeRepositoryMock.getById(any(Long.class))).thenReturn(testEmployee);

    assertEquals(testEmployee, employeeServiceWithMock.getById(testEmployee.getId()).get());
    verify(employeeRepositoryMock).getById(testEmployee.getId());
  }

  @Test
  void assertThatReturnsTheSameEmployeeAfterUpdate() {
    when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(testEmployee);
    Employee actualResult = employeeServiceWithMock.update(testEmployee);

    verify(employeeRepositoryMock, times(1)).save(testEmployee);
    assertEquals(testEmployee, actualResult);
  }

  @Test
  void assertThatEmployeeIsDeleted() {
    employeeServiceWithMock.delete(testEmployee.getId());

    verify(employeeRepositoryMock, times(1)).deleteById(testEmployee.getId());
  }
}