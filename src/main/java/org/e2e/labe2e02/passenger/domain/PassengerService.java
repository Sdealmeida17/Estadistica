package org.e2e.labe2e02.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e02.exception.ConflictException;
import org.e2e.labe2e02.passenger.dto.PassengerLocationDto;
import org.e2e.labe2e02.passenger.dto.PassengerRequestDto;
import org.e2e.labe2e02.passenger.infrastructure.PassengerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    private final CoordinateRepository coordinateRepository;

    private final ModelMapper modelMapper;

    public Passenger getPassengerById() {
    }

    public void deletePassengerById() {
    }

    public Passenger addPassengerPlace() {
    }

    public void deletePassengerPlace() {
    }

    public List<> getPassengerPlacesById() {
    }

    public Passenger createPassenger() {
    }
}