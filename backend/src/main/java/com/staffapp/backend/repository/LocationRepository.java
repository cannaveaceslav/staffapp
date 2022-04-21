package com.staffapp.backend.repository;

import com.staffapp.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Long> {

//    @Transactional
//    @Query(value = "SELECT e.APP_LOCATION_ID  " +
//            "from APP_EMPLOYEE e " +
//            "WHERE e.ID = ?1",nativeQuery = true)
//    Location findLocationByEmployee(Long employeeId);

    Optional<Location> findLocationByEmployee_Id(Long employeeId);

    @Transactional
    @Modifying
    @Query(value = "update APP_LOCATION e  " +
            "set e.AVAILABLE = 1, e.APP_EMPLOYEE_ID = null " +
            "WHERE e.ID = ?1 ",nativeQuery = true)
    void enableLocation(Long id);

    List<Location> findLocationsByEmployee_Id(Long id);
}
