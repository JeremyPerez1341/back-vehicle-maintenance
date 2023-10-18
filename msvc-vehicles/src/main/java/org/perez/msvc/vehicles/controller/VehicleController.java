package org.perez.msvc.vehicles.controller;

import com.sun.tools.javac.Main;
import feign.Body;
import feign.FeignException;
import jakarta.validation.Valid;
import org.perez.msvc.vehicles.models.Maintenance;
import org.perez.msvc.vehicles.models.entity.Vehicle;
import org.perez.msvc.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/api/vehicles")
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @GetMapping("/api/vehicles/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") long id) {
        Optional<Vehicle> vehicleOptional = vehicleService.findById(id);
        if(vehicleOptional.isPresent()){
            return ResponseEntity.ok(vehicleOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/vehicles")
    public ResponseEntity<?> save(@Valid @RequestBody Vehicle vehicle, BindingResult result) {
        if(vehicle.getPlate().isEmpty() || vehicleService.findByPlate(vehicle.getPlate()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("message", "Plate already exists"));
        }

        if (result.hasErrors()) {
            return validate(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.save(vehicle));
    }

    @DeleteMapping("/api/vehicles/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        Optional<Vehicle> vehicleOptional = vehicleService.findById(id);
        if(vehicleOptional.isPresent()){
            vehicleService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/vehicles/create-maintenance")
    public ResponseEntity<?> createMaintenance(@RequestBody Maintenance maintenance) {
        Optional<Maintenance> o;
        try {
            o = vehicleService.createMaintenance(maintenance);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Maintenance not created" +
                            "or failed comunication" + e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/vehicles/delete-maintenance/{vehicleId}")
    public ResponseEntity<?> deleteVehicleMaintenanceByMaintenanceId(@PathVariable(name = "vehicleId") Long vehicleId) {
        vehicleService.deleteVehicleMaintenanceByMaintenanceId(vehicleId);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Map<String, Object>> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "The field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
