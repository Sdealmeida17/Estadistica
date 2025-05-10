package org.e2e.labe2e02.driver.dto;

import org.e2e.labe2e02.coordinate.dto.CoordinateDto;
import org.e2e.labe2e02.driver.domain.Category;
import org.e2e.labe2e02.vehicle.dto.VehicleBasicDto;

public class DriverDto {
    private String name;
    private String email;
    private String password;
    private Category category;
    private VehicleBasicDto vehicle;
    private CoordinateDto coordinate;
}