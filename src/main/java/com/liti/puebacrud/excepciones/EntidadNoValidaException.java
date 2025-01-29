package com.liti.puebacrud.excepciones;

// Esta clase define una excepción personalizada para manejar casos en los que una entidad no es válida.
public class EntidadNoValidaException extends RuntimeException {

    // Constructor que acepta un mensaje de error y lo pasa a la clase base RuntimeException.
    public EntidadNoValidaException(String mensaje) {
        super(mensaje);
    }
}