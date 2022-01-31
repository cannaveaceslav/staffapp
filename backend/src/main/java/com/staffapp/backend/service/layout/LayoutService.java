package com.staffapp.backend.service.layout;

import com.staffapp.backend.model.Employee;
import com.staffapp.backend.model.Location;
import com.staffapp.backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LayoutService {
    private final LocationRepository locationRepository;

    public Location create(@NotNull Location location) {
        log.info("Saving new location [{}]", location.getLocationNumber());
        return locationRepository.save(location);
    }

    public Collection<Location> list() {
        log.info("Fetching all locations");
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        log.info("Getting location by id [{}]", id);
        return locationRepository.getById(id);
    }

    public Optional<Location> getLocationByEmployee(@NotNull Employee employee) {
        log.info("Getting location by employee [{}]", employee.getLastName() + " " + employee.getFirstName());
        return locationRepository.findLocationByEmployee_Id(employee.getId());
    }

    public Location update(@NotNull Location location) {
        log.info("Updating location [{}]", location.getLocationNumber());
        return locationRepository.save(location);
    }

    public Boolean delete(Long id) {
        log.info("Deleting location with ID [{}]", id);
        locationRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
