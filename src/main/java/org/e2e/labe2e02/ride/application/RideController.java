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

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody RideRequestDto rideRequestDto) {
        Ride createdRide = rideService.createRide(rideRequestDto);
        return ResponseEntity.status(201).body(createdRide);
    }

    @PatchMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<Ride> assignDriverToRide(@PathVariable Long rideId, @PathVariable Long driverId) {
        Ride assignedRide = rideService.assignDriverToRide(rideId, driverId);
        return ResponseEntity.ok(assignedRide);
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Page<RideResponseDto>> getRidesByPassengerId(
            @PathVariable Long passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<RideResponseDto> rides = rideService.getPassengerRides(passengerId, pageRequest);
        return ResponseEntity.ok(rides);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long id) {
        Ride canceledRide = rideService.cancelRide(id);
        return ResponseEntity.ok(canceledRide);
    }
}