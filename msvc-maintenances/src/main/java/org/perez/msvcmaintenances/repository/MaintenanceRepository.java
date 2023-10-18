package org.perez.msvcmaintenances.repository;

import org.perez.msvcmaintenances.models.entity.Maintenance;
import org.springframework.data.repository.CrudRepository;

public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {

    void deleteByVehicleId(Long vehicleId);
}
