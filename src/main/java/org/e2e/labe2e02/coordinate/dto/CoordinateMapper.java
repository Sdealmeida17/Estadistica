package org.e2e.labe2e02.coordinate.dto;

import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.passenger.dto.PassengerLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CoordinateMapper {

    CoordinateMapper INSTANCE = Mappers.getMapper(CoordinateMapper.class);

    CoordinateDto toDto(Coordinate coordinate);
    Coordinate toEntity(PassengerLocationDto dto);
}


