package org.e2e.labe2e02.driver.dto;

import lombok.Data;
import org.e2e.labe2e02.coordinate.dto.CoordinateDto;
import org.e2e.labe2e02.vehicle.dto.VehicleBasicDto;
import org.e2e.labe2e02.driver.domain.Category;

@Data
public class DriverRequestDto {
    private String name;
    private String email;
    private String password;
    private Category category;
    private VehicleBasicDto vehicle;
    private CoordinateDto coordinate;
}

