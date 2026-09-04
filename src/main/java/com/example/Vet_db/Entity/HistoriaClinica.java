package com.example.Vet_db.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "historia_clinica")
@Data
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha de apertura es obligatoria")  // @NotNull para fechas
    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;

    @NotBlank(message = "Los antecedentes son obligatorios")
    @Column(name = "antecedentes", nullable = false)
    private String antecedentes;

    @NotBlank(message = "Las observaciones son obligatorias")
    @Column(name = "observaciones", nullable = false)
    private String observaciones;

    // Relación con Mascota: la historia clínica ES DUEÑA de la FK
    // Por eso aquí va @JoinColumn, y en Mascota va mappedBy = "mascota"
    @OneToOne
    @JoinColumn(name = "mascota_id", nullable = false, unique = true)
    @ToString.Exclude  // evita bucle infinito
    private Mascota mascota;
}