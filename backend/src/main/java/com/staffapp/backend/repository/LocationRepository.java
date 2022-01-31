package com.staffapp.backend.repository;

import com.staffapp.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
