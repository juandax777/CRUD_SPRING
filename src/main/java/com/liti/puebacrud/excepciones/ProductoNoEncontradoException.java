package com.liti.puebacrud.excepciones;

// Esta clase define una excepción personalizada para manejar casos en los que un producto no se encuentra.
public class ProductoNoEncontradoException extends RuntimeException {

    // Constructor que acepta un mensaje de error y lo pasa a la clase base RuntimeException.
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}