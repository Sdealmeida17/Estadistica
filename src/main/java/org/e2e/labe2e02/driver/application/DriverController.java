package org.e2e.labe2e02.driver.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.driver.domain.Driver;
import org.e2e.labe2e02.driver.domain.DriverService;
import org.e2e.labe2e02.driver.dto.DriverDto;
import org.e2e.labe2e02.vehicle.domain.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<> getDriverById() {
    }

    @PostMapping
    public ResponseEntity<> createDriver() {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<> deleteDriverById() {
    }

    @PutMapping("/{id}")
    public ResponseEntity<> updateDriver() {
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<> updateDriverLocation() {
    }

    @PatchMapping("/{id}/car")
    public ResponseEntity<> updateDriverCard() {
    }
}
