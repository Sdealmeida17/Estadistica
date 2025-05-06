package org.e2e.labe2e02;

import org.e2e.labe2e02.exception.ConflictException;
import org.e2e.labe2e02.exception.ResourceNotFoundException;

public class GlobalExceptionHandler {
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    public String handleConflictException(ConflictException ex) {
        return ex.getMessage();
    }
}
