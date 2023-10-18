package org.perez.msvc.vehicles.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.perez.msvc.vehicles.models.Maintenance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_id")
    private List<VehicleMaintenance> vehicleMaintenances;

    @Transient
    private List<Maintenance> maintenances;

    public Vehicle() {
        vehicleMaintenances = new ArrayList<>();
        maintenances = new ArrayList<>();
    }

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    @NotEmpty
    private String plate;

    @NotNull
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2099, message = "El año debe ser menor o igual a 2099")
    private Number year;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registered_at", nullable = false, updatable = false)
    private Date registeredAt;

    @PrePersist
    protected void onCreate() {
        registeredAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Number getYear() {
        return year;
    }

    public void setYear(Number year) {
        this.year = year;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void addVehicleMaintenance(VehicleMaintenance vehicleMaintenance) {
        vehicleMaintenances.add(vehicleMaintenance);
    }

    public void removeVehicleMaintenance(VehicleMaintenance vehicleMaintenance) {
        vehicleMaintenances.remove(vehicleMaintenance);
    }

    public List<VehicleMaintenance> getVehicleMaintenances() {
        return vehicleMaintenances;
    }

    public void setVehicleMaintenances(List<VehicleMaintenance> vehicleMaintenances) {
        this.vehicleMaintenances = vehicleMaintenances;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }
}
