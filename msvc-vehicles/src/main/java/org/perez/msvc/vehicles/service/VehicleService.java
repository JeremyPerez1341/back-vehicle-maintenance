package org.perez.msvc.vehicles.service;

import org.perez.msvc.vehicles.models.Maintenance;
import org.perez.msvc.vehicles.models.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> findAll();
    Optional<Vehicle> findById(Long id);
    Vehicle save(Vehicle vehicle);
    void deleteById(Long id);

    void deleteVehicleMaintenanceByMaintenanceId(Long maintenanceId);


    Optional<Vehicle> findByPlate(String plate);
    Optional<Maintenance> createMaintenance(Maintenance maintenance);
}
