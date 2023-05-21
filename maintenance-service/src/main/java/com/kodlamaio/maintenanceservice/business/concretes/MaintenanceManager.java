package com.kodlamaio.maintenanceservice.business.concretes;

import com.kodlamaio.commonpackage.events.Event;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceCreateEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceDeleteEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceReturnEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.maintenanceservice.business.abstracts.MaintenanceService;
import com.kodlamaio.maintenanceservice.business.dto.request.create.CreateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.request.update.UpdateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.update.UpdateMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.rules.MaintenanceBusinessRules;
import com.kodlamaio.maintenanceservice.entities.Maintenance;
import com.kodlamaio.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final MaintenanceBusinessRules rules;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        var maintenances=repository.findAll();
        var response=maintenances.stream()
                .map(maintenance -> mapper.forResponse().map(maintenance,GetAllMaintenancesResponse.class)).toList();
        return response;
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        var maintenance=repository.findById(id).orElseThrow();
        var response=mapper.forResponse().map(maintenance,GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkCarAvailabilityForMaintenance(request.getCarId());
        var maintenance=mapper.forRequest().map(request, Maintenance.class);
        maintenance.setId(null);
        maintenance.setDateIn(LocalDateTime.now());
        maintenance.setRepaired(false);
        Maintenance saveManMaintenance=repository.save(maintenance);
        sendKafkaMaintenanceEvent(new MaintenanceCreateEvent(saveManMaintenance.getCarId()),"maintenance-created");

        return mapper.forResponse().map(saveManMaintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request) {
        rules.existsMaintenanceById(id);
        Maintenance maintenance=mapper.forRequest().map(request,Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        return mapper.forResponse().map(maintenance,UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(UUID id) {
        rules.existsMaintenanceById(id);
        var maintenance=mapper.forRequest().map(getById(id),Maintenance.class);
        repository.deleteById(id);
        checkAndSendMaintenanceDeleteEvent(maintenance);

    }

    @Override
    public void returnTheCarFromMaintenance(UUID carId) {
        rules.checkCarInMaintenanceAndRepairedFalse(carId);
        Maintenance maintenance=repository.findMaintenanceByCarIdAndIsRepairedFalse(carId);
        maintenance.setDateOut(LocalDateTime.now());
        maintenance.setRepaired(true);
        repository.save(maintenance);
        sendKafkaMaintenanceEvent(new MaintenanceReturnEvent(carId),"maintenance-return");
    }
    private void sendKafkaMaintenanceEvent(Event event, String topic){
        producer.sendMessage(event,topic);
    }
    private void checkAndSendMaintenanceDeleteEvent(Maintenance maintenance){
        if(!maintenance.isRepaired()){
            sendKafkaMaintenanceEvent(new MaintenanceDeleteEvent(maintenance.getCarId()),"maintenance-deleted");
        }
    }
}
