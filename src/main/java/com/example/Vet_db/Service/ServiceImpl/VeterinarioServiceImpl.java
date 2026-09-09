package com.example.Vet_db.Service.ServiceImpl;

import com.example.Vet_db.Entity.Veterinario;
import com.example.Vet_db.Exception.ResourceNotFoundException;
import com.example.Vet_db.Repository.VeterinarioRepository;
import com.example.Vet_db.Service.VeterinarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Veterinario> listarTodos() { return veterinarioRepository.findAll();    }

    @Override
    @Transactional(readOnly = true)
    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado: " + id));
    }

    @Override
    @Transactional
    public Veterinario guardar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    @Transactional
    public Veterinario actualizar(Long id, Veterinario datos) {
        Veterinario actual = buscarPorId(id);

        actual.setNombre(datos.getNombre());
        actual.setTarjetaProfesional(datos.getTarjetaProfesional());
        actual.setEspecialidad(datos.getEspecialidad());
        actual.setCorreo(datos.getCorreo());

        return veterinarioRepository.save(actual);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!veterinarioRepository.existsById(id)) {
            throw new RuntimeException("Veterinario no existe");
        }
        veterinarioRepository.deleteById(id);
    }
}
