package com.staffapp.backend.service.itemType;

import com.staffapp.backend.model.ItemType;
import com.staffapp.backend.repository.ItemTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class ItemTypeServiceTest {
  @Mock
  private ItemTypeRepository itemTypeRepository;
  private ItemTypeService itemTypeService;
  private ItemType testItemType;
  private Random random;

  @BeforeEach
  void setUp() {
    itemTypeService = new ItemTypeService(itemTypeRepository);
    random = new Random();
    testItemType = ItemType.builder()
            .id((long) random.nextInt(1000))
            .typeName("testItemType")
            .createdAt(LocalDateTime.now())
            .description("test description")
            .enabled(true)
            .build();
  }

  @Test
  void assertThatMethodReturnsList() {
    List<ItemType> expectedResult = new ArrayList<>(Collections.singletonList(testItemType));
    when(itemTypeRepository.findAll()).thenReturn(expectedResult);
    Collection<ItemType> actualResult = itemTypeService.list();

    verify(itemTypeRepository).findAll();
    assertTrue(actualResult.containsAll(expectedResult));
  }

  @Test
  void assertThatItemTypeIsCreated() {
    when(itemTypeRepository.save(any(ItemType.class))).thenReturn(testItemType);

    assertEquals(testItemType, itemTypeService.create(testItemType));
    verify(itemTypeRepository).save(testItemType);
  }

  @Test
  void assertThatReturnsProperItemType() {
    when(itemTypeRepository.getById(any(Long.class))).thenReturn(testItemType);

    assertEquals(testItemType, itemTypeService.getById(testItemType.getId()).get());
    verify(itemTypeRepository, atLeast(1)).getById(testItemType.getId());
  }

  @Test
  void assertThatReturnsTheSameItemTypeAfterUpdate() {
    when(itemTypeRepository.save(any(ItemType.class))).thenReturn(testItemType);
    ItemType actualResult = itemTypeService.update(testItemType);

    verify(itemTypeRepository, times(1)).save(testItemType);
    assertEquals(testItemType, actualResult);
  }

  @Test
  void assertThatItemTypeIsDeleted() {
    itemTypeService.delete(testItemType.getId());

    verify(itemTypeRepository, times(1)).deleteById(testItemType.getId());
  }
}