package com.staffapp.backend.service.itemType;

import com.staffapp.backend.model.ItemType;
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
public class ItemTypeService {

  private final ItemTypeRepository itemTypeRepository;

  public Collection<ItemType> list() {
    log.info("Fetching all itemTypes");
    return itemTypeRepository.findAll();
  }

  public ItemType create(@NotNull ItemType itemType) {
    log.info("Saving new itemType [{}]", itemType.getTypeName());
    return itemTypeRepository.save(itemType);
  }

  public Optional<ItemType> getById(Long id) {
    log.info("Getting ItemType by id [{}]", id);
    return Optional.of(itemTypeRepository.getById(id));
  }

  public ItemType update(@NotNull ItemType itemType) {
    log.info("Updating itemType [{}]", itemType.getTypeName());
    return itemTypeRepository.save(itemType);
  }

  public Boolean delete(Long id) {
    log.info("Deleting itemType [{}]", id);
    itemTypeRepository.deleteById(id);
    return Boolean.TRUE;
  }

}
