package org.e2e.labe2e02.ride.dto;

import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RideResponseDtoValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        validator = localValidatorFactoryBean;
    }

    @Test
    void shouldFailValidationWhenIdIsNull() {
        RideResponseDto rideResponseDto = new RideResponseDto();
        rideResponseDto.setId(null);
        rideResponseDto.setOriginName("Origin");
        rideResponseDto.setDestinationName("Destination");
        rideResponseDto.setPrice(1.0);
        rideResponseDto.setDepartureDate(ZonedDateTime.now());
        var violations = validator.validate(rideResponseDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenOriginNameAndDestinationNameAreNull() {
        RideResponseDto rideResponseDto = new RideResponseDto();
        rideResponseDto.setId(1L);
        rideResponseDto.setOriginName(null);
        rideResponseDto.setDestinationName(null);
        rideResponseDto.setPrice(1.0);
        rideResponseDto.setDepartureDate(ZonedDateTime.now());
        var violations = validator.validate(rideResponseDto);
        assertEquals(2, violations.size());
    }

    @Test
    void shouldFailValidationWhenOriginNameAndDestinationNameAreInvalid() {
        RideResponseDto rideResponseDto = new RideResponseDto();
        rideResponseDto.setId(1L);
        rideResponseDto.setOriginName("a");
        rideResponseDto.setDestinationName("a");
        rideResponseDto.setPrice(1.0);
        rideResponseDto.setDepartureDate(ZonedDateTime.now());
        var violations = validator.validate(rideResponseDto);
        assertEquals(2, violations.size());
    }

    @Test
    void shouldFailValidationWhenPriceIsNegative() {
        RideResponseDto rideResponseDto = new RideResponseDto();
        rideResponseDto.setId(1L);
        rideResponseDto.setOriginName("Origin");
        rideResponseDto.setDestinationName("Destination");
        rideResponseDto.setPrice(-1.0);
        rideResponseDto.setDepartureDate(ZonedDateTime.now());
        var violations = validator.validate(rideResponseDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenDepartureDateIsNull() {
        RideResponseDto rideResponseDto = new RideResponseDto();
        rideResponseDto.setId(1L);
        rideResponseDto.setOriginName("Origin");
        rideResponseDto.setDestinationName("Destination");
        rideResponseDto.setPrice(1.0);
        rideResponseDto.setDepartureDate(null);
        var violations = validator.validate(rideResponseDto);
        assertEquals(1, violations.size());
    }
}
