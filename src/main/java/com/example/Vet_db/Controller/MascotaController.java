package com.example.Vet_db.Controller;

import com.example.Vet_db.Entity.Mascota;
import com.example.Vet_db.Service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService service;

    // GET /api/mascotas
    @GetMapping
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/propietario/{propietario}")
    public ResponseEntity<List<Mascota>> buscarPorPropietario(@PathVariable Long propietario) {
        return ResponseEntity.ok(service.BuscarPorPropietario(propietario));
    }

    @PostMapping
    public ResponseEntity<Mascota> guardar(@Valid @RequestBody Mascota mascota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(mascota));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Mascota mascota) {
        return ResponseEntity.ok(service.actualizar(id, mascota));
    }

    @PutMapping("/{mascotaId}/veterinario/{veterinarioId}")
    public ResponseEntity<Mascota> asignarVeterinario(
            @PathVariable Long mascotaId,
            @PathVariable Long veterinarioId) {
        return ResponseEntity.ok(service.asignarVeterinario(mascotaId, veterinarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
