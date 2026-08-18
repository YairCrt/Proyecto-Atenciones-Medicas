package com.proyectocoryb.repository;

import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Paciente;
import com.proyectocoryb.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findByEstado(Estado estado, Pageable pageable);

    boolean existsByPersona(Persona persona);

    @Query("SELECT p FROM Paciente p JOIN Usuario u ON u.persona=p.persona WHERE u.usuario = :username")
    Optional<Paciente> findByUsuarioUsername(@Param("username") String username);
}
