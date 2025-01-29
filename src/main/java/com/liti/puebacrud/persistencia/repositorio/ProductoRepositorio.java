package com.liti.puebacrud.persistencia.repositorio;

import com.liti.puebacrud.persistencia.entidad.ProductoEntidad;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductoRepositorio extends ListCrudRepository<ProductoEntidad, Integer> {
}
