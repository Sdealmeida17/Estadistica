package org.e2e.labe2e02.driver.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.driver.domain.Driver;
import org.e2e.labe2e02.driver.domain.DriverService;
import org.e2e.labe2e02.driver.dto.DriverDto;
import org.e2e.labe2e02.driver.dto.DriverMapper;
import org.e2e.labe2e02.driver.dto.DriverRequestDto;
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
    private final DriverMapper driverMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id) {
        Driver driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driverMapper.toDto(driver));
    }

    @PostMapping
    public ResponseEntity<Void> createDriver(@RequestBody DriverRequestDto dto) {
        Driver driver = driverService.createDriver(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(driver.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriverById(@PathVariable Long id) {
        driverService.deleteDriverById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDriver(@PathVariable Long id, @RequestBody DriverDto dto) {
        driverService.updateDriver(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/location")
    public ResponseEntity<Void> updateDriverLocation(@PathVariable Long id, @RequestBody Coordinate coordinate) {
        driverService.updateDriverLocation(id, coordinate);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/car")
    public ResponseEntity<DriverDto> updateDriverCard(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Driver updatedDriver = driverService.updateDriverCar(id, vehicle);
        return ResponseEntity.ok(driverMapper.toDto(updatedDriver));
    }
}

