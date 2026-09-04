package com.example.Vet_db.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascota")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La especie es obligatoria")
    @Column(name = "especie", nullable = false)
    private String especie;

    @NotBlank(message = "La raza es obligatoria")
    @Column(name = "raza", nullable = false)
    private String raza;

    @NotBlank(message = "El correo es obligatorio")
    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    @NotNull(message = "La edad es obligatoria")   // @NotNull para números
    @Min(value = 1, message = "La edad mínima es 1 año")
    @Column(nullable = false)
    private Integer edad;

    @NotNull(message = "El peso es obligatorio")   // @NotNull para números
    @DecimalMin(value = "0.1", message = "El peso debe ser mayor a 0")
    @Column(nullable = false)
    private Double peso;

    // Relación con Propietario: muchas mascotas -> un propietario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id", nullable = false)
    @ToString.Exclude  // evita bucle infinito con Lombok
    private Propietario propietario;  // tipo corregido: Propietario, no Propietario.propietario

    // Relación con HistoriaClinica: una mascota -> una historia clínica
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistoriaClinica historiaClinica;

    // Relación con Veterinario: muchas mascotas <-> muchos veterinarios
    @ManyToMany
    @JoinTable(
            name = "mascota_veterinario",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinario_id")
    )
    @ToString.Exclude
    private List<Veterinario> veterinarios = new ArrayList<>();
}