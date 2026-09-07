package com.example.Vet_db.Repository;

import com.example.Vet_db.Entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository <Propietario, Long> {

}
