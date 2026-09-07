package com.example.Vet_db.Controller;

import com.example.Vet_db.Entity.Propietario;
import com.example.Vet_db.Service.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
public class PropietarioController {

    private final PropietarioService service;

    @GetMapping
    public List<Propietario> listar(){return service.listartodos();}


}
