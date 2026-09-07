package com.example.Vet_db.Service.ServiceImpl;


import com.example.Vet_db.Entity.Propietario;
import com.example.Vet_db.Exception.ResourceNotFoundException;
import com.example.Vet_db.Repository.PropietarioRepository;
import com.example.Vet_db.Service.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> listartodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Propietario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Propietario no encontrado: "+id));
    }

    @Override
    @Transactional
    public Propietario guardar(Propietario propietario) {
        return repository.save(propietario);
    }

    @Override
    @Transactional
    public Propietario actualizar(Long id, Propietario datos) {
        Propietario actual = buscarPorId(id);

        actual.setNombre(datos.getNombre());
        actual.setDocumento(datos.getDocumento());
        actual.setTelefono(datos.getTelefono());
        actual.setCorreo(datos.getCorreo());
        return repository.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        Propietario propietario = buscarPorId(id);
        repository.delete(propietario);

    }
}
