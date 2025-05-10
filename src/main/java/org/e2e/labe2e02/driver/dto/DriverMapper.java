package org.e2e.labe2e02.driver.dto;

import org.e2e.labe2e02.driver.domain.Driver;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public DriverDto toDto(Driver driver) {
        return modelMapper.map(driver, DriverDto.class);
    }

    public Driver fromRequestDto(DriverRequestDto dto) {
        return modelMapper.map(dto, Driver.class);
    }

    public void updateFromDto(DriverDto dto, Driver driver) {
        modelMapper.map(dto, driver);
    }
}

