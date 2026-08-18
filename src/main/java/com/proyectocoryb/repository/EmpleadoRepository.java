package com.proyectocoryb.repository;

import com.proyectocoryb.model.Empleado;
import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Page<Empleado> findByEstado(Estado estado, Pageable pageable);

    Optional<Empleado> findByPersona(Persona persona);
}
