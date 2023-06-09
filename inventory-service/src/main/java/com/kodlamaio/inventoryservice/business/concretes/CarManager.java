package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.commonpackage.events.inventory.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.inventory.CarDeletedEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.dto.request.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateCarResponse;
import com.kodlamaio.inventoryservice.business.rules.CarBusinessRules;
import com.kodlamaio.inventoryservice.entities.Car;
import com.kodlamaio.inventoryservice.entities.enums.State;
import com.kodlamaio.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor

public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;
    private final CarBusinessRules rules;
   // private final InventoryProducer producer;
    private final KafkaProducer producer;
    @Override
    public List<GetAllCarsResponse> getAll() {
        var cars=repository.findAll();
        var response=cars.stream().map(car->mapper.forResponse().map(car, GetAllCarsResponse.class)).toList();
        return response;
    }

    @Override
    public GetCarResponse getById(UUID id) {
        rules.checkIfCarExists(id);
        var car=repository.findById(id).orElseThrow();
        var response=mapper.forResponse().map(car,GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        var car=mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID());// YADA NULL YA DA UUID.randomUUID()
        car.setState(State.Available);
        var createdCar=repository.save(car);
        sendKafkaCarCreatedEvent(createdCar);
        var response = mapper.forResponse().map(car,CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest request) {
        var car=mapper.forRequest().map(request,Car.class);
        car.setId(id);
        repository.save(car);
        var response=mapper.forResponse().map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
        producer.sendMessage(new CarDeletedEvent(id),"car-deleted");
    }

    @Override
    public ClientResponse checkIfCarAvailable(UUID id) {
        var response=new ClientResponse();
        validateCarAvailable(id, response);

        return response;
    }

    private void validateCarAvailable(UUID id, ClientResponse response) {
        try{
            rules.checkIfCarExists(id);
            rules.checkCarAvailability(id);
            response.setSuccess(true);
        }catch (BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }

    @Override
    public void changeStateByCarId(State state, UUID id) {
        repository.changeStateByCarId(state,id);
    }

    public void sendKafkaCarCreatedEvent(Car car){
        var event=mapper.forResponse().map(car, CarCreatedEvent.class);
        producer.sendMessage(event,"car-created");
    }
    public void sendKafkaCarDeletedEvent(UUID id){
        var deletedCar=repository.findById(id);
        var event=mapper.forResponse().map(deletedCar, CarDeletedEvent.class);
        producer.sendMessage(event,"car-deleted");
    }
}
