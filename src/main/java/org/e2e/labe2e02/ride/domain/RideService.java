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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RideService {
    private final PassengerRepository passengerRepository;

    private final RideRepository rideRepository;

    private final DriverRepository driverRepository;

    private final ModelMapper modelMapper;

    public Ride createRide(RideRequestDto rideRequestDto) {
        // Verificar duplicados
        if (rideRequestDto.getId() != null && rideRepository.existsById(rideRequestDto.getId())) {
            throw new RideNotFoundException("Ride with id " + rideRequestDto.getId() + " already exists");
        }

        // Recuperar entidades necesarias
        Passenger passenger = passengerRepository.findById(rideRequestDto.getPassengerId())
                .orElseThrow(() -> new PassengerNotFoundException("Passenger with id " + rideRequestDto.getPassengerId() + " not found"));

        Driver driver = driverRepository.findById(rideRequestDto.getDriverId())
                .orElseThrow(() -> new DriverNotFoundException("Driver with id " + rideRequestDto.getDriverId() + " not found"));

        // Mapear el DTO al Ride
        Ride ride = modelMapper.map(rideRequestDto, Ride.class);

        // Asignar relaciones manualmente
        ride.setPassenger(passenger);
        ride.setDriver(driver);

        return rideRepository.save(ride);
    }


    public Ride assignDriverToRide(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RideNotFoundException("Ride with id " + rideId + " not found"));
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver with id " + driverId + " not found"));

        if (ride.getStatus() != Status.REQUESTED) {
            throw new IllegalStateException("Cannot assign a driver to a ride that is not in REQUESTED status");
        }

        ride.setDriver(driver);
        ride.setStatus(Status.ACCEPTED);
        return rideRepository.save(ride);
    }

    public Page<RideResponseDto> getPassengerRides(Long passengerId, Pageable pageable) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger with id " + passengerId + " not found"));
        Page<Ride> rides = rideRepository.findByPassenger(passenger, pageable);
        return rides.map(ride -> modelMapper.map(ride, RideResponseDto.class));
    }

    public Ride cancelRide(Long id) {
        Ride ride = rideRepository.findById(id)
                .orElseThrow(() -> new RideNotFoundException("Ride with id " + id + " not found"));
        if (ride.getStatus() == Status.CANCELLED || ride.getStatus() == Status.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a ride that is already canceled or completed");
        }
        ride.setStatus(Status.CANCELLED);
        return rideRepository.save(ride);
    }

}