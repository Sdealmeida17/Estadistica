package org.e2e.labe2e02.driver.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e02.driver.dto.DriverDto;
import org.e2e.labe2e02.driver.infrastructure.DriverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    private final CoordinateRepository coordinateRepository;

    private final ModelMapper modelMapper;

    public DriverDto getDriverById() {
    }

    public Driver createDriver() {
    }

    public void deleteDriverById() {
    }

    public Driver updateDriver() {
    }

    public Driver updateDriverLocation() {
    }

    public DriverDto updateDriverCar() {
    }
}