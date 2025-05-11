package org.e2e.labe2e02.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e02.exception.ConflictException;
import org.e2e.labe2e02.passenger.dto.PassengerLocationDto;
import org.e2e.labe2e02.passenger.dto.PassengerRequestDto;
import org.e2e.labe2e02.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e02.passenger.exception.PassengerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final CoordinateRepository coordinateRepository;

    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with ID: " + id));
    }

    public void deletePassengerById(Long id) {
        Passenger passenger = getPassengerById(id);
        passengerRepository.delete(passenger);
    }

    public Passenger addPassengerPlace(Long id, PassengerLocationDto dto) {
        Passenger passenger = getPassengerById(id);

        // Verificar si ya existe una coordenada igual
        boolean alreadyExists = passenger.getPlaces().stream().anyMatch(place ->
                place.getLatitude().equals(dto.getLatitude()) &&
                        place.getLongitude().equals(dto.getLongitude())
        );

        if (alreadyExists) {
            throw new ConflictException("Passenger already has this coordinate registered.");
        }

        Coordinate coordinate = Coordinate.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        coordinateRepository.save(coordinate);
        passenger.getPlaces().add(coordinate);
        return passengerRepository.save(passenger);
    }

    public void deletePassengerPlace(Long passengerId, Long coordinateId) {
        Passenger passenger = getPassengerById(passengerId);
        Coordinate coordinate = coordinateRepository.findById(coordinateId)
                .orElseThrow(() -> new RuntimeException("Coordinate not found with ID: " + coordinateId)); // Puedes crear una excepción específica

        passenger.getPlaces().remove(coordinate);
        passengerRepository.save(passenger);
    }

    public List<Coordinate> getPassengerPlacesById(Long id) {
        Passenger passenger = getPassengerById(id);
        return passenger.getPlaces();
    }

    public Passenger createPassenger(PassengerRequestDto dto) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(dto.getFirstName());
        passenger.setLastName(dto.getLastName());
        passenger.setPhoneNumber(dto.getPhoneNummber());
        passenger.setEmail(dto.getEmail());
        passenger.setPassword(dto.getPassword());
        passenger.setRole(dto.getRole());
        passenger.setAvgRating(0.0); // Inicializar calificación

        return passengerRepository.save(passenger);
    }
}
