package org.e2e.labe2e02.ride.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.ride.domain.Ride;
import org.e2e.labe2e02.ride.domain.RideService;
import org.e2e.labe2e02.ride.dto.RideRequestDto;
import org.e2e.labe2e02.ride.dto.RideResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping
    public ResponseEntity<> createRide() {
    }

    @PatchMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<> assignDriverToRide() {
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Page<>> getRidesByPassengerId() {
    }

    @PatchMapping("/{id}")
    public ResponseEntity<> cancelRide() {
    }
}