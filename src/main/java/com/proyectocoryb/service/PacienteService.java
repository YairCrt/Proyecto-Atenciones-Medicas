package com.proyectocoryb.service;

import com.proyectocoryb.dto.request.PacienteRequestDTO;
import com.proyectocoryb.dto.response.PacienteResponseDTO;
import com.proyectocoryb.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PacienteService {

    PacienteResponseDTO crearPaciente(PacienteRequestDTO requestDTO);

    PacienteResponseDTO obtenerPacientePorId(Long id);

    PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO requestDTO);

    void eliminarPaciente(long id);

    Page<PacienteResponseDTO> listarPacientes(Pageable pageable);

    Page<PacienteResponseDTO> listarPacientesPorEstado(Estado estado, Pageable pageable);

    List<PacienteResponseDTO> listarActivos();


}
