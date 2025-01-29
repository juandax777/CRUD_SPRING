package com.liti.puebacrud.servicio;

import com.liti.puebacrud.persistencia.entidad.ProductoEntidad;
import com.liti.puebacrud.persistencia.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// Esta clase maneja la lógica de negocio relacionada con los productos.
@Service
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    @Autowired
    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    // Obtiene todos los productos de la base de datos de forma asíncrona.
    @Async
    public CompletableFuture<List<ProductoEntidad>> getAll() {
        return CompletableFuture.completedFuture(this.productoRepositorio.findAll());
    }

    // Obtiene un producto por su ID de forma asíncrona.
    @Async
    public CompletableFuture<ProductoEntidad> get(int idProducto) {
        return CompletableFuture.completedFuture(
                this.productoRepositorio.findById(idProducto).orElse(null)
        );
    }

    // Inserta un producto en la base de datos de forma asíncrona.
    @Async
    public CompletableFuture<ProductoEntidad> save(ProductoEntidad producto) {
        return CompletableFuture.completedFuture(this.productoRepositorio.save(producto));
    }

    // Verifica si un producto existe en la base de datos de forma asíncrona.
    @Async
    public CompletableFuture<Boolean> exists(int idProducto) {
        return CompletableFuture.completedFuture(this.productoRepositorio.existsById(idProducto));
    }

    // Elimina un producto en la base de datos de forma asíncrona.
    @Async
    public CompletableFuture<Void> delete(int idProducto) {
        this.productoRepositorio.deleteById(idProducto);
        return CompletableFuture.completedFuture(null);
    }
}
