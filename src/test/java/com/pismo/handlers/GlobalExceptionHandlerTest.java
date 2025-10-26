package com.pismo.handlers;

import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.DuplicateDocumentNumberException;
import com.pismo.exceptions.OperationTypeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleDuplicateDoc() {
        DuplicateDocumentNumberException ex = new DuplicateDocumentNumberException("Duplicate doc");
        ResponseEntity<String> response = handler.handleDuplicateDoc(ex);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Duplicate doc", response.getBody());
    }

    @Test
    void testHandleAccountNotFound() {
        AccountNotFoundException ex = new AccountNotFoundException(1L);
        ResponseEntity<String> response = handler.handleAccountNotFound(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("1"));
    }

    @Test
    void testHandleOperationNotFound() {
        OperationTypeNotFoundException ex = new OperationTypeNotFoundException(2L);
        ResponseEntity<String> response = handler.handleOperationNotFound(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("2"));
    }

    @Test
    void testHandleValidationExceptions() {
        FieldError fieldError = new FieldError("objectName", "fieldName", "must not be blank");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Map<String, String>> response = handler.handleValidationExceptions(ex);

        assertEquals(400, response.getStatusCodeValue());
        Map<String, String> body = response.getBody();
        assertNotNull(body);
        assertEquals("must not be blank", body.get("fieldName"));
    }
}
