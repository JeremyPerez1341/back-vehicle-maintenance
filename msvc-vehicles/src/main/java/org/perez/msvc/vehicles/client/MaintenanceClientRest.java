package org.perez.msvc.vehicles.client;


import org.perez.msvc.vehicles.models.Maintenance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-maintenances", url = "msvc-vehicles.ctngfojae0d5.us-east-2.rds.amazonaws.com:5002")
public interface MaintenanceClientRest {

    @GetMapping("/api/maintenances/{id}")
    public Maintenance findById(@PathVariable Long id);

    @PostMapping("/api/maintenances")
    public Maintenance save(@RequestBody Maintenance maintenance);

    @DeleteMapping("/api/maintenances/vehicle/{vehicleId}")
    void deleteByVehicleId(@PathVariable Long vehicleId);
}
