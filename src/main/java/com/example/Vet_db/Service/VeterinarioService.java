package com.example.Vet_db.Service;

import com.example.Vet_db.Entity.Veterinario;

import java.util.List;

public interface VeterinarioService {
    List<Veterinario> listarTodos ();
    Veterinario buscarPorId (Long id);
    Veterinario guardar(Veterinario veterinario);
    Veterinario actualizar (Long id, Veterinario veterinario);
    void eliminar(Long id);
}
