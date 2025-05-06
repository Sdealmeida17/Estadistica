package org.e2e.labe2e02.ride.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.driver.domain.Driver;
import org.e2e.labe2e02.driver.exception.DriverNotFoundException;
import org.e2e.labe2e02.driver.infrastructure.DriverRepository;
import org.e2e.labe2e02.passenger.domain.Passenger;
import org.e2e.labe2e02.passenger.exception.PassengerNotFoundException;
import org.e2e.labe2e02.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e02.ride.dto.RideRequestDto;
import org.e2e.labe2e02.ride.dto.RideResponseDto;
import org.e2e.labe2e02.ride.exception.RideNotFoundException;
import org.e2e.labe2e02.ride.infrastructure.RideRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideService {
    private final PassengerRepository passengerRepository;

    private final RideRepository rideRepository;

    private final DriverRepository driverRepository;

    private final ModelMapper modelMapper;

    public Ride createRide() {
    }

    public Ride assignDriverToRide() {
    }

    public Page<> getPassengerRides() {
    }

    public Ride cancelRide() {
    }
}