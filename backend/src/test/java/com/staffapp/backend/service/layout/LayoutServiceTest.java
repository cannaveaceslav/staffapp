package com.staffapp.backend.service.layout;

import com.staffapp.backend.model.Company;
import com.staffapp.backend.model.Department;
import com.staffapp.backend.model.Employee;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
class LayoutServiceTest {

  private LayoutService layoutService;
  @Mock
  private LocationRepository locationRepository;
  @Mock
  private Company testCompany;
  @Mock
  private Department testDepartment;

  private Location testLocation1;
  private Location testLocation2;
  private Employee testEmployee;
  private Random random;


  @BeforeEach
  void setUpTestData() {
    layoutService = new LayoutService(locationRepository);
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

    testLocation1 = Location.builder()
            .id((long) random.nextInt(1000))
            .locationNumber((long) random.nextInt(1000))
            .available(true)
            .description("Location created for Unit tests")
            .employee(null)
            .createdAt(LocalDateTime.now())
            .pos_x(400.0)
            .pos_y(200.0)
            .build();

    testLocation2 = Location.builder()
            .id((long) random.nextInt(1000))
            .locationNumber((long) random.nextInt(1000))
            .available(true)
            .description("Location created for Unit tests")
            .employee(testEmployee)
            .createdAt(LocalDateTime.now())
            .pos_x(425.0)
            .pos_y(250.0)
            .build();
  }


  @Test
  void assertThatLocationIsCreated() {
    when(locationRepository.save(any(Location.class))).thenReturn(testLocation1);

    assertEquals(testLocation1, layoutService.create(testLocation1));
    verify(locationRepository).save(testLocation1);
  }

  @Test
  void assertThatMethodReturnsList() {
    List<Location> expectedResult = new ArrayList<>(Arrays.asList(testLocation1, testLocation2));
    when(locationRepository.findAll()).thenReturn(expectedResult);
    Collection<Location> actualResult = layoutService.list();

    verify(locationRepository).findAll();
    assertTrue(actualResult.containsAll(expectedResult));
  }

  @Test
  void assertThatReturnsRightLocation() {
    when(locationRepository.getById(any(Long.class))).thenReturn(testLocation1);

    assertEquals(testLocation1, layoutService.getById(testLocation1.getId()));
    verify(locationRepository).getById(testLocation1.getId());
  }

  @Test
  void assertThatReturnsLocationByEmployeeId() {
    when(locationRepository.findLocationByEmployee_Id(any(Long.class))).thenReturn(java.util.Optional.ofNullable(testLocation2));

    assertEquals(java.util.Optional.ofNullable(testLocation2), layoutService.getLocationByEmployee(testEmployee));
    verify(locationRepository).findLocationByEmployee_Id(testEmployee.getId());
  }

  @Test
  void assertThatReturnsTheSameLocationAfterUpdate() {
    when(locationRepository.save(any(Location.class))).thenReturn(testLocation2);
    Location actualResult = layoutService.update(testLocation2);

    verify(locationRepository, times(1)).save(testLocation2);
    assertEquals(testLocation2, actualResult);
  }


  @Test
  void assertThatLocationIsDeleted() {
    layoutService.delete(testLocation2.getId());

    verify(locationRepository, times(1)).deleteById(testLocation2.getId());
  }
}