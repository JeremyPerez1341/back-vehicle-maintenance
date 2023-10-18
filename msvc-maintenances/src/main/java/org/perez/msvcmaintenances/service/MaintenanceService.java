package org.perez.msvcmaintenances.service;

import org.perez.msvcmaintenances.models.entity.Maintenance;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {

    List<Maintenance> findAll();
    Optional<Maintenance> findById(Long id);
    Maintenance save(Maintenance maintenance);
    void deleteById(Long id);
    void deleteByVehicleId(Long vehicleId);
}
