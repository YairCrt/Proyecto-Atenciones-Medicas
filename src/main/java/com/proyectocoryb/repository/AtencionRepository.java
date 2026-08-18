package com.proyectocoryb.repository;

import com.proyectocoryb.model.Atencion;
import com.proyectocoryb.model.Empleado;
import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;

@Repository
public interface AtencionRepository  extends JpaRepository<Atencion, Long> {

    Page<Atencion> findByPaciente(Paciente paciente, Pageable pageable);

    Page<Atencion> findByEmpleado(Empleado empleado, Pageable pageable);

    Page<Atencion> findByEstado(Estado estado, Pageable pageable);

    Page<Atencion> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin, Pageable pageable);

    @Query("SELECT a FROM Atencion a WHERE LOWER(a.motivo) LIKE LOWER (CONCAT('%', :motivo, '%'))")
    Page<Atencion> searchByMotivo(@Param("motivo") String motivo, Pageable pageable);
}
