package org.e2e.labe2e02.ride.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.e2e.labe2e02.coordinate.domain.Coordinate;
import org.e2e.labe2e02.coordinate.dto.CoordinateDto;
import org.e2e.labe2e02.driver.domain.Driver;
import org.e2e.labe2e02.passenger.domain.Passenger;
import org.e2e.labe2e02.ride.domain.Status;

import java.time.ZonedDateTime;

@Data
public class RideRequestDto {

    private Long id;

    @NotNull
    @Positive
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;


    private ZonedDateTime arrivalDate;

    private ZonedDateTime departureDate;

    @NotNull
    private CoordinateDto destinationCoordinates;

    @NotNull
    private CoordinateDto originCoordinates;


    @NotNull
    private Long passengerId;

    @NotNull
    private Long driverId;

    @NotNull
    @Size(min = 2, max = 255)
    private String destinationName;

    @NotNull
    @Size(min = 2, max = 255)
    private String originName;



}