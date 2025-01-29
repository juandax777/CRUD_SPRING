package com.liti.puebacrud.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.hibernate.PropertyValueException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja la excepción ProductoNoEncontradoException y devuelve un estado HTTP 404 (Not Found).
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<String> handleProductoNoEncontradoException(ProductoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Maneja la excepción HttpMessageNotReadableException y devuelve un estado HTTP 400 (Bad Request).
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la solicitud: " + ex.getMessage());
    }

    // Maneja la excepción PropertyValueException y devuelve un estado HTTP 400 (Bad Request).
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> handlePropertyValueException(PropertyValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la solicitud: " + ex.getMessage());
    }

    // Maneja la excepción EntidadNoValidaException y devuelve un estado HTTP 400 (Bad Request).
    @ExceptionHandler(EntidadNoValidaException.class)
    public ResponseEntity<String> handleEntidadNoValidaException(EntidadNoValidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Maneja cualquier otra excepción no específica y devuelve un estado HTTP 400 (Bad Request).
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la solicitud: " + ex.getMessage());
    }


}