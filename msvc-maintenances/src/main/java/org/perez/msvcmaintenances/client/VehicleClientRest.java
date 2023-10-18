package org.perez.msvcmaintenances.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-vehicles", url = "msvc-vehicles.ctngfojae0d5.us-east-2.rds.amazonaws.com:5001")
public interface VehicleClientRest {

    @DeleteMapping("/api/vehicles/delete-maintenance/{vehicleId}")
    void deleteVehicleMaintenanceByMaintenanceId(@PathVariable Long vehicleId);
}
