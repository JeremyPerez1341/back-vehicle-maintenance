package org.perez.msvcmaintenances.controller;

import org.perez.msvcmaintenances.models.entity.Maintenance;
import org.perez.msvcmaintenances.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/api/maintenances")
    public ResponseEntity<List<Maintenance>> findAll() {
        return ResponseEntity.ok(maintenanceService.findAll());
    }

    @GetMapping("/api/maintenances/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        Optional<Maintenance> maintenanceOptional = maintenanceService.findById(id);
        if(maintenanceOptional.isPresent()){
            return ResponseEntity.ok(maintenanceOptional.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/maintenances")
    public ResponseEntity<?> save(@RequestBody Maintenance maintenance, BindingResult result) {
        if(result.hasErrors()){
            return validate(result);
        }

        Maintenance maintenanceDB = maintenanceService.save(maintenance);
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceDB);
    }

    @DeleteMapping("/api/maintenances/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        Optional<Maintenance> maintenanceOptional = maintenanceService.findById(id);
        if(maintenanceOptional.isPresent()){
            maintenanceService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/maintenances/vehicle/{vehicleId}")
    public ResponseEntity<?> deleteByVehicleId(@PathVariable(name = "vehicleId") Long vehicleId) {
        maintenanceService.deleteByVehicleId(vehicleId);
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
