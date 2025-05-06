package org.e2e.labe2e02.driver.dto;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.e2e.labe2e02.driver.domain.Category;
import org.e2e.labe2e02.user.domain.Role;
import org.e2e.labe2e02.vehicle.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DriverRequestDtoValidationTest {
    private Validator validator;

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        validator = localValidatorFactoryBean;
        vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC123");
        vehicle.setBrand("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setFabricationYear(2020);
        vehicle.setCapacity(5);
    }

    @Test
    void shouldFailValidationWhenCategoryIsNull() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setVehicle(vehicle);
        driverRequestDto.setCategory(null);
        driverRequestDto.setRole(Role.DRIVER);
        driverRequestDto.setFirstName("John");
        driverRequestDto.setLastName("Doe");
        driverRequestDto.setEmail("john@example.com");
        driverRequestDto.setPhoneNumber("123456789");
        driverRequestDto.setPassword("password");
        driverRequestDto.setCreatedAt(ZonedDateTime.now());
        Set<ConstraintViolation<DriverRequestDto>> violations = validator.validate(driverRequestDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenFirstNameIsNull() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setCategory(Category.X);
        driverRequestDto.setVehicle(vehicle);
        driverRequestDto.setRole(Role.DRIVER);
        driverRequestDto.setLastName("Doe");
        driverRequestDto.setEmail("john@example.com");
        driverRequestDto.setPhoneNumber("123456789");
        driverRequestDto.setPassword("password");
        driverRequestDto.setCreatedAt(ZonedDateTime.now());
        Set<ConstraintViolation<DriverRequestDto>> violations = validator.validate(driverRequestDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenEmailFormatIsInvalid() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setCategory(Category.X);
        driverRequestDto.setVehicle(vehicle);
        driverRequestDto.setRole(Role.DRIVER);
        driverRequestDto.setFirstName("John");
        driverRequestDto.setLastName("Doe");
        driverRequestDto.setEmail("invalid-email");
        driverRequestDto.setPhoneNumber("123456789");
        driverRequestDto.setPassword("password");
        driverRequestDto.setCreatedAt(ZonedDateTime.now());
        Set<ConstraintViolation<DriverRequestDto>> violations = validator.validate(driverRequestDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenPhoneNumberLengthExceedsLimit() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setCategory(Category.X);
        driverRequestDto.setVehicle(vehicle);
        driverRequestDto.setRole(Role.DRIVER);
        driverRequestDto.setFirstName("John");
        driverRequestDto.setLastName("Doe");
        driverRequestDto.setEmail("john@example.com");
        driverRequestDto.setPhoneNumber("1234567890123456");
        driverRequestDto.setPassword("password");
        driverRequestDto.setCreatedAt(ZonedDateTime.now());
        Set<ConstraintViolation<DriverRequestDto>> violations = validator.validate(driverRequestDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenPasswordLengthIsLessThanMinimum() {
        DriverRequestDto driverRequestDto = new DriverRequestDto();
        driverRequestDto.setCategory(Category.X);
        driverRequestDto.setVehicle(vehicle);
        driverRequestDto.setRole(Role.DRIVER);
        driverRequestDto.setFirstName("John");
        driverRequestDto.setLastName("Doe");
        driverRequestDto.setEmail("john@example.com");
        driverRequestDto.setPhoneNumber("123456789");
        driverRequestDto.setPassword("pass");
        driverRequestDto.setCreatedAt(ZonedDateTime.now());
        Set<ConstraintViolation<DriverRequestDto>> violations = validator.validate(driverRequestDto);
        assertEquals(1, violations.size());
    }
}
