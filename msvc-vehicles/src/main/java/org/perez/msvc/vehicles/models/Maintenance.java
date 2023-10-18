package org.perez.msvc.vehicles.models;

import jakarta.persistence.Column;

import java.util.Date;

public class Maintenance {
    private Long id;

    private Long vehicleId;

    private String typeMaintenance;

    private String note;

    private Number cost;

    private Date maintenanceDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeMaintenance() {
        return typeMaintenance;
    }

    public void setTypeMaintenance(String typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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
