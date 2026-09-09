package com.example.Vet_db.Service.ServiceImpl;

import com.example.Vet_db.Entity.HistoriaClinica;
import com.example.Vet_db.Entity.Mascota;
import com.example.Vet_db.Repository.HistoriaClinicaRepository;
import com.example.Vet_db.Repository.MascotaRepository;
import com.example.Vet_db.Service.HistoriaClinicaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HistoriaClinica> listarTodas() {
        return historiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public HistoriaClinica buscarPorId(Long id) {
        return historiaRepository.findById(id).orElseThrow(() -> new RuntimeException("Historia Clínica no encontrada"));
    }

    @Override
    @Transactional
    public HistoriaClinica guardar(HistoriaClinica historiaClinica) {
        return historiaRepository.save(historiaClinica);
    }

    @Override
    @Transactional
    public HistoriaClinica crear(HistoriaClinica historia, Long mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        historia.setMascota(mascota);
        return historiaRepository.save(historia);
    }

    @Override
    @Transactional
    public HistoriaClinica actualizar(Long id, HistoriaClinica historia) {
        HistoriaClinica existente = buscarPorId(id);
        existente.setFechaApertura(historia.getFechaApertura());
        existente.setAntecedentes(historia.getAntecedentes());
        existente.setObservaciones(historia.getObservaciones());
        return historiaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!historiaRepository.existsById(id)) {
            throw new RuntimeException("Historia Clínica no existe");
        }
        historiaRepository.deleteById(id);
    }
}
