package com.example.Vet_db.Service.ServiceImpl;

import com.example.Vet_db.Entity.Mascota;
import com.example.Vet_db.Entity.Veterinario;
import com.example.Vet_db.Exception.ResourceNotFoundException;
import com.example.Vet_db.Repository.MascotaRepository;
import com.example.Vet_db.Repository.PropietarioRepository;
import com.example.Vet_db.Repository.VeterinarioRepository;
import com.example.Vet_db.Service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {
    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Override
    @Transactional(readOnly = true)

    public List<Mascota> listarTodas() { return mascotaRepository.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Mascota no encontrado: "+id));
    }

    @Override
    @Transactional
    public Mascota guardar(Mascota mascota) { return mascotaRepository.save(mascota); }

    @Override
    @Transactional
    public Mascota actualizar(Long id, Mascota datos) {
        Mascota actual = buscarPorId(id);

        actual.setNombre(datos.getNombre());
        actual.setEspecie(datos.getEspecie());
        actual.setRaza(datos.getRaza());
        actual.setEdad(datos.getEdad());

        return mascotaRepository.save(actual);
    }

    @Override
    public void eliminar(Long id) {

        Mascota mascota = buscarPorId(id);
        mascotaRepository.delete(mascota);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> BuscarPorPropietario(Long propietario) {
        propietarioRepository.findById(propietario)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado: " + propietario));
        return mascotaRepository.findByPropietarioId(propietario);
    }

    @Override
    @Transactional
    public Mascota asignarVeterinario(Long mascotaId, Long veterinarioId) {
        Mascota mascota = buscarPorId(mascotaId);
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado: " + veterinarioId));

        if (!mascota.getVeterinarios().contains(veterinario)) {
            mascota.getVeterinarios().add(veterinario);
        }
        return mascotaRepository.save(mascota);
    }
}
