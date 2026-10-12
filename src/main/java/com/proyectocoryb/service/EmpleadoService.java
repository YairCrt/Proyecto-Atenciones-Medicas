package com.proyectocoryb.service;

import com.proyectocoryb.dto.request.EmpleadoRequestDTO;
import com.proyectocoryb.dto.response.EmpleadoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO requestDTO);

    EmpleadoResponseDTO actualizarEmpleado(Long id, EmpleadoRequestDTO requestDTO);

    List<EmpleadoResponseDTO> listarEmpleados();

    Page<EmpleadoResponseDTO> listarPorEstado(String estado, Pageable pageable);

    void eliminarEmpleado(Long id);

    Optional<EmpleadoResponseDTO> buscarPorId(Long id);
}
