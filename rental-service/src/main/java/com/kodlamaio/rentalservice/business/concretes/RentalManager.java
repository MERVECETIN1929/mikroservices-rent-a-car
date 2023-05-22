package com.kodlamaio.rentalservice.business.concretes;

import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.dto.request.PaymentRentalRequest;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;

import com.kodlamaio.rentalservice.api.clients.CarClient;
import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.dto.request.create.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.dto.request.update.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.dto.response.create.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.dto.response.get.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.business.dto.response.get.GetRentalResponse;
import com.kodlamaio.rentalservice.business.dto.response.update.UpdateRentalResponse;
import com.kodlamaio.rentalservice.business.rules.RentalBusinessRules;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final KafkaProducer producer;
    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals=repository.findAll();
        var response=rentals.stream().map(brand -> mapper.forResponse().map(brand, GetAllRentalsResponse.class)).toList();
        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental=repository.findById(id).orElseThrow();
        var response=mapper.forResponse().map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        //carClient.checkIfCarAvailable(request.getCarId());
        rules.ensureCarIsAvailable(request.getCarId());
        Rental rental=mapper.forRequest().map(request, Rental.class);
        rental.setRentedAt(LocalDate.now());
        rental.setId(UUID.randomUUID());
        rental.setTotalPrice(getTotalPrice(rental));
        PaymentRentalRequest rentalRequest=mapper.forRequest().map(request.getPayment(),PaymentRentalRequest.class);
        rentalRequest.setPrice(rental.getTotalPrice());
        rules.makeRentalPayment(rentalRequest);
        var save=repository.save(rental);
        sentKafkaRentalEvent(request.getCarId());
        var response=mapper.forResponse().map(save, CreateRentalResponse.class);
        return response;
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental=mapper.forRequest().map(request,Rental.class);
        rental.setId(id);
        repository.save(rental);
        var response=mapper.forResponse().map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        repository.deleteById(id);

    }
    private double getTotalPrice(Rental rental){
        return rental.getDailyPrice()*rental.getRentedForDays();
    }
    public void sentKafkaRentalEvent(UUID carId){
        producer.sendMessage(new RentalCreatedEvent(carId),"rental-created");
    }
}