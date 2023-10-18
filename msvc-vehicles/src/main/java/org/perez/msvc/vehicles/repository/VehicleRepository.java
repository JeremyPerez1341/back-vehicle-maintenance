package org.perez.msvc.vehicles.repository;

import org.perez.msvc.vehicles.models.entity.Vehicle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Modifying
    @Query("delete from VehicleMaintenance vm where vm.maintenanceId = ?1")
    void deleteVehicleMaintenanceByMaintenanceId(Long maintenanceId);

    Optional<Vehicle> findByPlate(String plate);

    boolean existsByPlate(String plate);
}
