package org.perez.msvc.vehicles.service;

import org.perez.msvc.vehicles.client.MaintenanceClientRest;
import org.perez.msvc.vehicles.models.Maintenance;
import org.perez.msvc.vehicles.models.entity.Vehicle;
import org.perez.msvc.vehicles.models.entity.VehicleMaintenance;
import org.perez.msvc.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private MaintenanceClientRest client;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, MaintenanceClientRest client) {
        this.vehicleRepository = vehicleRepository;
        this.client = client;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Vehicle> findAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
        client.deleteByVehicleId(id);
    }

    @Override
    @Transactional
    public void deleteVehicleMaintenanceByMaintenanceId(Long maintenanceId) {
        vehicleRepository.deleteVehicleMaintenanceByMaintenanceId(maintenanceId);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Vehicle> findByPlate(String plate) {
        return vehicleRepository.findByPlate(plate);
    }

    @Override
    @Transactional
    public Optional<Maintenance> createMaintenance(Maintenance maintenance) {
        Optional<Vehicle> optionalMaintenance = vehicleRepository.findById(maintenance.getVehicleId());
        if(optionalMaintenance.isPresent()){
            Maintenance maintenanceNewMsvc = client.save(maintenance);

            Vehicle vehicle = optionalMaintenance.get();
            VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();
            vehicleMaintenance.setMaintenanceId(maintenanceNewMsvc.getId());

            vehicle.addVehicleMaintenance(vehicleMaintenance);
            vehicleRepository.save(vehicle);
            return Optional.of(maintenanceNewMsvc);
        } else{
            return Optional.empty();
        }
    }

}
