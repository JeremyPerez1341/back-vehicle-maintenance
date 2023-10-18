package org.perez.msvcmaintenances.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "maintenances")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "type_maintenance", nullable = false, updatable = false)
    private String typeMaintenance;

    private String note;

    @NotNull
    private Number cost;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "maintenance_date", nullable = false, updatable = false)
    private Date maintenanceDate;

    @PrePersist
    protected void onCreate() {
        maintenanceDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTypeMaintenance() {
        return typeMaintenance;
    }

    public void setTypeMaintenance(String typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Number getCost() {
        return cost;
    }

    public void setCost(Number cost) {
        this.cost = cost;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

}
