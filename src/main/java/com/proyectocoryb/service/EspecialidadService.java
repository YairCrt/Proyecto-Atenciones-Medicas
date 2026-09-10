package com.proyectocoryb.service;

import com.proyectocoryb.dto.request.EspecialidadRequestDTO;
import com.proyectocoryb.dto.response.EspecialidadResponseDTO;
import com.proyectocoryb.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EspecialidadService {

    EspecialidadResponseDTO crearEspecialidad(EspecialidadRequestDTO requestDTO);

    EspecialidadResponseDTO actualizarEspecialidad(Long id, EspecialidadRequestDTO requestDTO);

    void eliminarEspecialidad(Long id);

    EspecialidadResponseDTO obtenerEspecialidadPorId(Long id);

    Page<EspecialidadResponseDTO> listarEspecialidades(Pageable pageable);

    Page<EspecialidadResponseDTO> buscarEspecialidadPorNombre(String nombre, Pageable pageable);

    Page<EspecialidadResponseDTO> listarEspecialidadPorEstado(Estado estado, Pageable pageable);
}
