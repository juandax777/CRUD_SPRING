package com.liti.puebacrud.persistencia.repositorio;

import com.liti.puebacrud.persistencia.entidad.ProductoEntidad;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductoRepositorio extends ListCrudRepository<ProductoEntidad, Integer> {

    // Buscar productos cuyo nombre contenga una palabra clave (LIKE %keyword%)
    List<ProductoEntidad> findByNombreContainingIgnoreCase(String nombre);
}
