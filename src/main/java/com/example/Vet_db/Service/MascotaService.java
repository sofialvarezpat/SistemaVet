package com.example.Vet_db.Service;

import com.example.Vet_db.Entity.Mascota;
import java.util.List;

public interface MascotaService {
    List<Mascota> listarTodas ();
    Mascota buscarPorId(Long id);
    Mascota guardar (Mascota mascota);
    Mascota actualizar (Long id, Mascota mascota);
    void eliminar(Long id);
    List<Mascota>BuscarPorPropietario (Long propietario);
    Mascota asignarVeterinario (Long mascotaId, Long veterinarioId);
}
