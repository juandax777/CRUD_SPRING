package com.liti.puebacrud.servicio;

import com.liti.puebacrud.persistencia.entidad.ProductoEntidad;
import com.liti.puebacrud.persistencia.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    @Autowired
    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    // Obtener productos con paginación y filtro opcional por nombre
    @Async
    public CompletableFuture<List<ProductoEntidad>> getAll(String nombre, int page, int size) {
        List<ProductoEntidad> productos;

        if (nombre != null && !nombre.isEmpty()) {
            productos = productoRepositorio.findByNombreContainingIgnoreCase(nombre);
        } else {
            productos = productoRepositorio.findAll();
        }

        // Paginación manual (porque ListCrudRepository no soporta `Pageable`)
        int fromIndex = Math.min(page * size, productos.size());
        int toIndex = Math.min(fromIndex + size, productos.size());

        return CompletableFuture.supplyAsync(() -> productos.subList(fromIndex, toIndex));
    }


    @Async
    public CompletableFuture<ProductoEntidad> get(int idProducto) {
        return CompletableFuture.completedFuture(
                productoRepositorio.findById(idProducto).orElse(null)
        );
    }

    @Async
    public CompletableFuture<ProductoEntidad> save(ProductoEntidad producto) {
        return CompletableFuture.completedFuture(productoRepositorio.save(producto));
    }

    @Async
    public CompletableFuture<Boolean> exists(int idProducto) {
        return CompletableFuture.completedFuture(productoRepositorio.existsById(idProducto));
    }

    @Async
    public CompletableFuture<Void> delete(int idProducto) {
        productoRepositorio.deleteById(idProducto);
        return CompletableFuture.completedFuture(null);
    }
}