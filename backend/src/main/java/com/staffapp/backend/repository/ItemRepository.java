package com.staffapp.backend.repository;

import com.staffapp.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<Item, Long> {

  Optional<Collection<Item>> findAllByItemTypeId(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Item c " +
            "SET c.employee.id = ?1 " +
            "WHERE c.id = ?2")
  int linkEmployeeToItem(Long employeeId, Long itemId);
}
