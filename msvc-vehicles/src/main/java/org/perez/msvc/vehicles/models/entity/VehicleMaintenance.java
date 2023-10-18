package org.perez.msvc.vehicles.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles_maintenances")
public class VehicleMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "maintenance_id", unique = true)
    private Long maintenanceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof VehicleMaintenance)) {
            return false;
        }
        VehicleMaintenance o = (VehicleMaintenance) obj;
        return this.maintenanceId != null && this.maintenanceId.equals(o.maintenanceId);
    }
}
