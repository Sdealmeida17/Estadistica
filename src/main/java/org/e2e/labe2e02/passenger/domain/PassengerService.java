package org.e2e.labe2e02.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.coordinate.dto.CoordinateMapper;
import org.e2e.labe2e02.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e02.exception.ConflictException;
import org.e2e.labe2e02.passenger.dto.PassengerLocationDto;
import org.e2e.labe2e02.passenger.dto.PassengerRequestDto;
import org.e2e.labe2e02.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e02.passenger.exception.PassengerNotFoundException;
import org.e2e.labe2e02.user.domain.Role;
import org.e2e.labe2e02.userLocations.domain.UserLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        boolean alreadyExists = passenger.getPlaces().stream().anyMatch(userLocation ->
                userLocation.getCoordinate().getLatitude().equals(dto.getLatitude()) &&
                        userLocation.getCoordinate().getLongitude().equals(dto.getLongitude())
        );

        if (alreadyExists) {
            throw new ConflictException("Passenger already has this coordinate registered.");
        }

        CoordinateMapper coordinateMapper = null;
        Coordinate coordinate = coordinateMapper.toEntity(dto);
        coordinateRepository.save(coordinate);


        // Crear y asociar UserLocation
        UserLocation userLocation = new UserLocation(passenger, coordinate, dto.getDescription());
        passenger.getPlaces().add(userLocation);

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
        Passenger passenger = getPassengerById(id);  // Obtienes el pasajero desde la base de datos
        List<UserLocation> userLocations = passenger.getPlaces();  // Obtienes las ubicaciones de tipo UserLocation

        // Convertimos de UserLocation a Coordinate
        return userLocations.stream()
                .map(userLocation -> {
                    Coordinate coordinate = userLocation.getCoordinate();
                    return new Coordinate(coordinate.getLatitude(), coordinate.getLongitude());
                })
                .collect(Collectors.toList());

    }

    public Passenger createPassenger(PassengerRequestDto dto) {
        Passenger passenger = new Passenger();
        passenger.setFirstName(dto.getFirstName());
        passenger.setLastName(dto.getLastName());
        passenger.setPhoneNumber(dto.getPhoneNummber());
        passenger.setEmail(dto.getEmail());
        passenger.setPassword(dto.getPassword());
        passenger.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        passenger.setAvgRating(0.0); // Inicializar calificación

        return passengerRepository.save(passenger);
    }
}
