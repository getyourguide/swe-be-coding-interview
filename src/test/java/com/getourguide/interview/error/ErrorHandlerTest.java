package com.getourguide.interview.error;

import com.getourguide.interview.exceptions.ResourceNotFoundException;
import com.getourguide.interview.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

class ErrorHandlerTest {
    private ErrorHandler errorHandler;
    private HttpServletRequest request;

    @BeforeEach
    void setup() {
        this.errorHandler = new ErrorHandler();
        this.request = mock(HttpServletRequest.class);
    }

    @Test
    void testHandleResourceNotFound() {
        when(request.getRequestURI()).thenReturn("/api/test");
        var exception = new ResourceNotFoundException("Resource not found");

        var response = errorHandler.handleResourceNotFound(exception, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Resource not found", response.getBody().getMessage());
        Assertions.assertEquals(404, response.getBody().getStatus());
        Assertions.assertEquals("/api/test", response.getBody().getPath());
    }

    @Test
    void testHandleValidation() {
        when(request.getRequestURI()).thenReturn("/api/validate");
        var exception = new ValidationException("Invalid input");

        var response = errorHandler.handleValidation(exception, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Invalid input", response.getBody().getMessage());
        Assertions.assertEquals(400, response.getBody().getStatus());
        Assertions.assertEquals("/api/validate", response.getBody().getPath());
    }

    @Test
    void testHandleIllegalArgument() {
        when(request.getRequestURI()).thenReturn("/api/illegal");
        var exception = new IllegalArgumentException("Illegal argument");

        var response = errorHandler.handleIllegalArgument(exception, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Illegal argument", response.getBody().getMessage());
        Assertions.assertEquals(400, response.getBody().getStatus());
    }

    @Test
    void testHandleGenericException() {
        when(request.getRequestURI()).thenReturn("/api/error");
        var exception = new Exception("Unexpected error");

        var response = errorHandler.handleException(exception, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Something went wrong", response.getBody().getMessage());
        Assertions.assertEquals(500, response.getBody().getStatus());
        Assertions.assertEquals("/api/error", response.getBody().getPath());
    }
}
