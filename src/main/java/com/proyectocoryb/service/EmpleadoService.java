package com.proyectocoryb.service;

import com.proyectocoryb.dto.request.EmpleadoRequestDTO;
import com.proyectocoryb.dto.response.EmpleadoResponseDTO;
import com.proyectocoryb.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmpleadoService {

    EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO requestDTO);

    EmpleadoResponseDTO actualizarEmpleado(Long id, EmpleadoRequestDTO requestDTO);

    Page<EmpleadoResponseDTO> listarEmpleados(Pageable pageable);

    Page<EmpleadoResponseDTO> listarPorEstadop(Estado estado, Pageable pageable);

    void eliminarEmpleado(Long id);

    Optional<EmpleadoResponseDTO> buscarPorId(Long id);
}
