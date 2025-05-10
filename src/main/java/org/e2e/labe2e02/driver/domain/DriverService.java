package org.e2e.labe2e02.driver.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e02.driver.dto.DriverDto;
import org.e2e.labe2e02.driver.dto.DriverMapper;
import org.e2e.labe2e02.driver.dto.DriverRequestDto;
import org.e2e.labe2e02.driver.exception.DriverNotFoundException;
import org.e2e.labe2e02.driver.infrastructure.DriverRepository;
import org.e2e.labe2e02.vehicle.domain.Vehicle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final CoordinateRepository coordinateRepository;
    private final DriverMapper driverMapper;

    public Driver getDriverById(Long id) {
        return driverRepository.getDriverById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + id));
    }

    public Driver createDriver(DriverRequestDto dto) {
        Driver driver = driverMapper.fromRequestDto(dto);
        coordinateRepository.save(driver.getCoordinate());
        return driverRepository.save(driver);
    }

    public void deleteDriverById(Long id) {
        Driver driver = getDriverById(id);
        driverRepository.delete(driver);
    }

    public Driver updateDriver(Long id, DriverDto dto) {
        Driver driver = getDriverById(id);
        driverMapper.updateFromDto(dto, driver); // actualiza campos desde dto.
        return driverRepository.save(driver);
    }

    public Driver updateDriverLocation(Long id, Coordinate newCoordinate) {
        Driver driver = getDriverById(id);
        coordinateRepository.save(newCoordinate);
        driver.setCoordinate(newCoordinate);
        return driverRepository.save(driver);
    }

    public Driver updateDriverCar(Long id, Vehicle newVehicle) {
        Driver driver = getDriverById(id);
        driver.setVehicle(newVehicle);
        return driverRepository.save(driver);
    }
}
