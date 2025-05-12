package org.e2e.labe2e02.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.passenger.domain.Passenger;
import org.e2e.labe2e02.passenger.domain.PassengerService;
import org.e2e.labe2e02.passenger.dto.PassengerLocationDto;
import org.e2e.labe2e02.passenger.dto.PassengerRequestDto;
import org.e2e.labe2e02.passenger.dto.PassengerResponseDto;
import org.e2e.labe2e02.passenger.exception.PassengerNotFoundException;
import org.e2e.labe2e02.passenger.dto.PassengerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerMapper passengerMapper;

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDto> getPassengerById(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        return ResponseEntity.ok(passengerMapper.toResponseDto(passenger));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassengerById(@PathVariable Long id) {
        passengerService.deletePassengerById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PassengerResponseDto> addPassengerPlace(@PathVariable Long id,
                                                                  @RequestBody PassengerLocationDto locationDto) {
        var updatedPassenger = passengerService.addPassengerPlace(id, locationDto);
        return ResponseEntity.ok(passengerMapper.toResponseDto(updatedPassenger));
    }

    @GetMapping("/{id}/places")
    public ResponseEntity<List<PassengerLocationDto>> getPassengerPlacesById(@PathVariable Long id) {
        List<Coordinate> coordinates = passengerService.getPassengerPlacesById(id);
        // Mapear la lista de coordenadas a DTO.
        List<PassengerLocationDto> locationDtos = coordinates.stream()
                .map(coordinate -> new PassengerLocationDto(coordinate.getLatitude(), coordinate.getLongitude()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(locationDtos);
    }

    @DeleteMapping("/{id}/places/{coordinateId}")
    public ResponseEntity<Void> deletePassengerPlace(@PathVariable Long id, @PathVariable Long coordinateId) {
        passengerService.deletePassengerPlace(id, coordinateId);
        return ResponseEntity.noContent().build(); // 204
    }

    @PostMapping
    public ResponseEntity<PassengerResponseDto> createPassenger(@RequestBody PassengerRequestDto requestDto) {
        var createdPassenger = passengerService.createPassenger(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPassenger.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(passengerMapper.toResponseDto(createdPassenger)); // 201
    }
}
