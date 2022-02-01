package com.staffapp.backend.repository;

import com.staffapp.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

  Optional<Collection<Item>> findAllByItemTypeId(Long id);
}
