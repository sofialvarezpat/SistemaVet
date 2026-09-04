package com.example.Vet_db.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propietario")
@Data
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    @Size(min = 8, max = 20)
    @Column(name = "documento", unique = true, nullable = false)
    private String documento;

    @NotBlank(message = "El número es obligatorio")
    @Size(min = 7, max = 10)
    @Column(name = "telefono", unique = true, nullable = false)
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    // Relación 1: Un propietario tiene MUCHAS mascotas
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas = new ArrayList<>();
}