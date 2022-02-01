package com.staffapp.backend.service.item;

import com.staffapp.backend.model.Employee;
import com.staffapp.backend.model.Item;
import com.staffapp.backend.model.ItemType;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.repository.ItemRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class ItemServiceTest {
  @Mock
  private ItemRepository itemRepository;
  @Mock
  private ItemType testItemType;
  @Mock
  private Employee testEmployee;
  @Mock
  private Location testLocation;

  private ItemService itemService;
  private Item testItem;
  private Random random;

  @BeforeEach
  void setUp() {
    itemService = new ItemService(itemRepository);
    random = new Random();
    testItem = Item.builder()
            .id((long) random.nextInt(1000))
            .barcode(String.valueOf(random.nextInt(1000000)))
            .createdAt(LocalDateTime.now())
            .description("test description")
            .enabled(true)
            .itemType(testItemType)
            .employee(testEmployee)
            .location(testLocation)
            .manufacturedAt(LocalDateTime.of(2020, Month.OCTOBER, 15, 12, 30))
            .itemName("test item model xxx")
            .build();
  }

  @Test
  void assertThatMethodReturnsList() {
    List<Item> expectedResult = new ArrayList<>(Collections.singletonList(testItem));
    when(itemRepository.findAll()).thenReturn(expectedResult);
    Collection<Item> actualResult = itemService.list();

    verify(itemRepository).findAll();
    assertTrue(actualResult.containsAll(expectedResult));
  }

  @Test
  void assertThatReturnsListByItemTypeId() {
    List<Item> expectedResult = new ArrayList<>(Arrays.asList(testItem, testItem));
    when(itemRepository.findAllByItemTypeId(any(Long.class))).thenReturn(java.util.Optional.of(expectedResult));
    Collection<Item> actualResult = itemService.listByItemTypeId(any(Long.class)).get();

    verify(itemRepository).findAllByItemTypeId(any(Long.class));
    assertTrue(actualResult.containsAll(expectedResult));
    assertEquals(2, actualResult.size());


  }

  @Test
  void assertThatItemTypeIsCreated() {
    when(itemRepository.save(any(Item.class))).thenReturn(testItem);

    assertEquals(testItem, itemService.create(testItem));
    verify(itemRepository).save(testItem);
  }

  @Test
  void assertThatReturnsProperItemType() {
    when(itemRepository.getById(any(Long.class))).thenReturn(testItem);

    assertEquals(testItem, itemService.getById(testItem.getId()).get());
    verify(itemRepository, atLeast(1)).getById(testItem.getId());
  }

  @Test
  void assertThatReturnsTheSameItemTypeAfterUpdate() {
    when(itemRepository.save(any(Item.class))).thenReturn(testItem);
    Item actualResult = itemService.update(testItem);

    verify(itemRepository, times(1)).save(testItem);
    assertEquals(testItem, actualResult);
  }

  @Test
  void assertThatItemTypeIsDeleted() {
    itemService.delete(testItem.getId());

    verify(itemRepository, times(1)).deleteById(testItem.getId());
  }
}