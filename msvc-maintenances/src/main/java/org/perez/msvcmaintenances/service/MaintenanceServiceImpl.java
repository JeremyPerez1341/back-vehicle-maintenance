package org.perez.msvcmaintenances.service;

import org.perez.msvcmaintenances.client.VehicleClientRest;
import org.perez.msvcmaintenances.models.entity.Maintenance;
import org.perez.msvcmaintenances.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private MaintenanceRepository maintenanceRepository;
    private VehicleClientRest client;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, VehicleClientRest client) {
        this.maintenanceRepository = maintenanceRepository;
        this.client = client;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Maintenance> findAll() {
        return (List<Maintenance>) maintenanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Maintenance> findById(Long id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    @Transactional
    public Maintenance save(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        maintenanceRepository.deleteById(id);
        client.deleteVehicleMaintenanceByMaintenanceId(id);
    }

    @Override
    @Transactional
    public void deleteByVehicleId(Long vehicleId) {
        maintenanceRepository.deleteByVehicleId(vehicleId);
    }
}
