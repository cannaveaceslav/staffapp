package com.staffapp.backend.service.layout;

import com.staffapp.backend.model.Location;
import com.staffapp.backend.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@Slf4j
@ActiveProfiles("test")
class LayoutServiceTest {

  private LayoutService layoutService;
  @SpyBean
  private LocationRepository locationRepository;

  private Location testLocation;


  @BeforeEach
  void setUpTestData() {
    layoutService = new LayoutService(locationRepository);
  }


  @Test
  void assertThatMethodReturnsList() {
    List<Location> expectedResult = new ArrayList<>(Arrays.asList(testLocation, testLocation));
    Collection<Location> actualResult = layoutService.list();

    assertEquals(expectedResult.size(), actualResult.size());
    verify(locationRepository).findAll();
  }

  @Test
  void assertThatReturnsRightLocation() {

    assertEquals(1001, layoutService.getById(1001L).getLocationNumber());
    verify(locationRepository).getById(anyLong());
  }

  @Test
  void assertThatReturnsLocationByEmployeeId() {

    when(locationRepository.findLocationByEmployee_Id(anyLong())).thenReturn(java.util.Optional.ofNullable(testLocation));
    layoutService.getLocationByEmployee(anyLong());

    verify(locationRepository).findLocationByEmployee_Id(anyLong());
  }


  @Test
  void assertThatReturnsTheSameLocationAfterUpdate() {

    testLocation = Location.builder()
            .id(1001L)
            .locationNumber(1001L)
            .available(true)
            .description("Location created for Unit tests")
            .createdAt(LocalDateTime.now())
            .pos_x(400.0)
            .pos_y(200.0)
            .build();

    Location actualResult = layoutService.update(testLocation);

    verify(locationRepository, times(1)).save(testLocation);
    assertEquals(testLocation.getLocationNumber(), actualResult.getLocationNumber());
  }


  @Test
  void assertThatLocationIsDeleted() {
    testLocation = Location.builder()
            .id(1001L)
            .locationNumber(1001L)
            .available(true)
            .description("Location created for Unit tests")
            .createdAt(LocalDateTime.now())
            .pos_x(400.0)
            .pos_y(200.0)
            .build();

    layoutService.delete(testLocation.getId());
    verify(locationRepository, times(1)).deleteById(testLocation.getId());
    locationRepository.save(testLocation);
  }
}