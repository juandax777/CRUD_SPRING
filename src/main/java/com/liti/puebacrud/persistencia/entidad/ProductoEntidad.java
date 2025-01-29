package com.liti.puebacrud.persistencia.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "producto")
public class ProductoEntidad {

    //etiquetas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(nullable = false, length = 77)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double precio;

    //fecha de creacion
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate fechaCreacion;

}
