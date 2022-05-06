package com.staffapp.backend.service.item;

import com.staffapp.backend.model.Item;
import com.staffapp.backend.repository.ItemRepository;
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
public class ItemService {

  private final ItemRepository itemRepository;

  public Collection<Item> list() {
    log.info("Fetching all Items");
    return itemRepository.findAll();
  }

  public Optional<Collection<Item>> listByItemTypeId(Long id) {
    log.info("Fetching all Items");
    return itemRepository.findAllByItemTypeId(id);
  }

  public Item create(@NotNull Item item) {
    log.info("Saving new Item [{}]", item.getItemName());
    return itemRepository.save(item);
  }

  public Optional<Item> getById(Long id) {
    log.info("Getting Item by id [{}]", id);
    return Optional.of(itemRepository.getById(id));
  }

  public Item update(@NotNull Item item) {
    log.info("Updating Item [{}]", item.getItemName());
    return itemRepository.save(item);
  }

  public int updateByEmployeeId(Long employeeId, Long itemId) {
    log.info("Linking item [{}] with employee [{}]", itemId,employeeId);
    return itemRepository.linkEmployeeToItem(employeeId,itemId);
  }


  public Boolean delete(Long id) {
    log.info("Deleting Item [{}]", id);
    itemRepository.deleteById(id);
    return Boolean.TRUE;
  }



  public Optional<Collection<Item>> listByEmployeeId(Long id) {
    log.info("Fetching all Items for employee ["+ id+"]");
    return itemRepository.findAllByEmployeeId(id);
  }

    public Item getByBarcode(String barcode) {
      log.info("Getting Item by barcode [{}]", barcode);
      return itemRepository.findFirstByBarcode(barcode);
    }
}
