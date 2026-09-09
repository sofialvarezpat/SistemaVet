package com.example.Vet_db.Service;

import com.example.Vet_db.Entity.HistoriaClinica;

import java.util.List;

public interface HistoriaClinicaService {
    List<HistoriaClinica> listarTodas ();
    HistoriaClinica buscarPorId(Long id);
    HistoriaClinica guardar (HistoriaClinica historiaClinica);
    HistoriaClinica crear(HistoriaClinica historia, Long mascotaId);
    HistoriaClinica actualizar (Long id, HistoriaClinica historiaClinica);
    void eliminar(Long id);
}
