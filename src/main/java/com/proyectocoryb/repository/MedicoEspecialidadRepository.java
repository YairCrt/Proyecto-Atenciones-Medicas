package com.proyectocoryb.repository;

import com.proyectocoryb.model.Empleado;
import com.proyectocoryb.model.Especialidad;
import com.proyectocoryb.model.MedicoEspecialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, Long> {

    Page<MedicoEspecialidad> findByEmpleado(Empleado empleado, Pageable pageable);

    Page<MedicoEspecialidad> findByEspecialidad(Especialidad especialidad, Pageable pageable);
}
