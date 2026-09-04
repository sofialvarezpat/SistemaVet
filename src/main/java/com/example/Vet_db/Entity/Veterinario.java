package com.example.Vet_db.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinario")
@Data
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La tarjeta profesional es obligatoria")
    @Size(min = 4, max = 6)
    @Column(name = "tarjeta_profesional", unique = true, nullable = false)
    private String tarjetaProfesional;

    @NotBlank(message = "La especialidad es obligatoria")
    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @NotBlank(message = "El correo es obligatorio")
    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    // Relación con Mascota: lado INVERSO del @ManyToMany
    // mappedBy apunta al campo "veterinarios" que está en Mascota
    @ManyToMany(mappedBy = "veterinarios")
    @ToString.Exclude  // evita bucle infinito
    private List<Mascota> mascotas = new ArrayList<>();
}