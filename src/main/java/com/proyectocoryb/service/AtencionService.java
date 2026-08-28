package com.proyectocoryb.service;

import com.proyectocoryb.dto.request.AtencionRequestDTO;
import com.proyectocoryb.dto.response.AtencionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface AtencionService {

    AtencionResponseDTO crearAtencion(AtencionRequestDTO requestDTO);

    AtencionResponseDTO obtenerAtencionPorId(Long id);

    Page<AtencionResponseDTO> listarTodas(Pageable pageable);

    Page<AtencionResponseDTO> listarPorPaciente(Long pacienteId, Pageable pageable);

    Page<AtencionResponseDTO> listarPorEmpleado(Long empleadoId, Pageable pageable);

    Page<AtencionResponseDTO> listarPorEstado(Long empleadoId, Pageable pageable);

    Page<AtencionResponseDTO> listarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable);

    Page<AtencionResponseDTO> buscarPorMotivo(String motivo, Pageable pageable);

    AtencionResponseDTO actualizarAtencion(Long id, AtencionRequestDTO requestDTO);

    void eliminarAtencion(Long id);

    Page<AtencionResponseDTO> listarAtencionesPacienteAutenticado(Pageable pageable);
}
