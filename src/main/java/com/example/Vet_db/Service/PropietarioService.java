package com.example.Vet_db.Service;

import com.example.Vet_db.Entity.Propietario;
import java.util.List;

public interface PropietarioService {
    List<Propietario> listartodos ();
    Propietario buscarPorId(Long id);
    Propietario guardar(Propietario propietario);
    Propietario actualizar (Long id, Propietario propietario);
    void eliminar(Long id);


}
