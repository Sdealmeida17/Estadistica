package org.e2e.labe2e02.ride.exception;

import org.e2e.labe2e02.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RideNotFoundException extends ResourceNotFoundException {
    public RideNotFoundException(String message) {
        super(message);
    }
}
