package org.e2e.labe2e02.passenger.exception;

import org.e2e.labe2e02.exception.ResourceNotFoundException;

public class PassengerNotFoundException extends ResourceNotFoundException {
    public PassengerNotFoundException(String message) {
        super(message);
    }
    public PassengerNotFoundException(Long id) {
        super("Passenger with ID " + id + " not found");
    }
}
