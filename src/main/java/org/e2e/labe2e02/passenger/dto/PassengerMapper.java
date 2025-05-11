package org.e2e.labe2e02.passenger.dto;
import org.e2e.labe2e02.passenger.domain.Passenger;
import org.e2e.labe2e02.passenger.dto.PassengerRequestDto;
import org.e2e.labe2e02.passenger.dto.PassengerResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    private final ModelMapper modelMapper;

    public PassengerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Passenger toEntity(PassengerRequestDto dto) {
        return modelMapper.map(dto, Passenger.class);
    }

    public PassengerResponseDto toResponseDto(Passenger passenger) {
        return modelMapper.map(passenger, PassengerResponseDto.class);
    }
}

