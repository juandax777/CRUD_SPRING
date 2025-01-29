package com.liti.puebacrud.controlador;

import com.liti.puebacrud.excepciones.EntidadNoValidaException;
import com.liti.puebacrud.excepciones.ProductoNoEncontradoException;
import com.liti.puebacrud.persistencia.entidad.ProductoEntidad;
import com.liti.puebacrud.servicio.ProductoServicio;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    @Autowired
    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    // Obtener productos con paginación y filtro opcional por nombre
    @GetMapping
    public CompletableFuture<List<ProductoEntidad>> getAll(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productoServicio.getAll(nombre, page, size);
    }

    // Obtiene un producto por su ID de forma asíncrona.
    @GetMapping("/{idProducto}")
    public CompletableFuture<ResponseEntity<ProductoEntidad>> get(@PathVariable("idProducto") int idProducto) {
        return productoServicio.get(idProducto).thenApply(producto -> {
            if (producto != null) {
                return ResponseEntity.ok(producto);
            } else {
                throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + idProducto);
            }
        });
    }

    // Inserta un producto en la base de datos de forma asíncrona.
    @PostMapping
    public CompletableFuture<ResponseEntity<ProductoEntidad>> add(@RequestBody ProductoEntidad producto) {
        try {
            return productoServicio.exists(producto.getIdProducto() == null ? -1 : producto.getIdProducto()).thenCompose(existe -> {
                if (!existe) {
                    return productoServicio.save(producto).thenApply(ResponseEntity::ok);
                } else {
                    return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
                }
            });
        } catch (PropertyValueException ex) {
            throw new EntidadNoValidaException("Error en la solicitud: " + ex.getMessage());
        }
    }

    // Actualiza un producto en la base de datos de forma asíncrona.
    @PutMapping("/{idProducto}")
    public CompletableFuture<ResponseEntity<ProductoEntidad>> update(@PathVariable("idProducto") int idProducto, @RequestBody ProductoEntidad producto) {
        return productoServicio.exists(idProducto).thenCompose(existe -> {
            if (!existe) {
                throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + idProducto);
            }
            producto.setIdProducto(idProducto);
            return productoServicio.save(producto).thenApply(ResponseEntity::ok);
        });
    }

    // Elimina un producto de la base de datos de forma asíncrona.
    @DeleteMapping("/{idProducto}")
    public CompletableFuture<ResponseEntity<Void>> delete(@PathVariable("idProducto") int idProducto) {
        return productoServicio.exists(idProducto).thenCompose(existe -> {
            if (existe) {
                return productoServicio.delete(idProducto).thenApply(v -> ResponseEntity.ok().build());
            } else {
                throw new ProductoNoEncontradoException("Producto no encontrado con ID: " + idProducto);
            }
        });
    }
}
