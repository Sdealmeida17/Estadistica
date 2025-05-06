package org.e2e.labe2e02.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.passenger.domain.Passenger;
import org.e2e.labe2e02.passenger.domain.PassengerService;
import org.e2e.labe2e02.passenger.dto.PassengerLocationDto;
import org.e2e.labe2e02.passenger.dto.PassengerRequestDto;
import org.e2e.labe2e02.passenger.dto.PassengerResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<> getPassengerById() {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<> deletePassengerById() {
    }

    @PatchMapping("/{id}")
    public ResponseEntity<> addPassengerPlace() {
    }

    @GetMapping("/{id}/places")
    public ResponseEntity<> getPassengerPlacesById() {
    }

    @DeleteMapping("/{id}/places/{coordinateId}")
    public ResponseEntity<> deletePassengerPlace() {
    }

    @PostMapping
    public ResponseEntity<> createPassenger() {
    }
}