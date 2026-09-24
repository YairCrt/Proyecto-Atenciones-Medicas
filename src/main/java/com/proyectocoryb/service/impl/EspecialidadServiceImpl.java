package com.proyectocoryb.service.impl;

import com.proyectocoryb.dto.request.EspecialidadRequestDTO;
import com.proyectocoryb.dto.response.EspecialidadResponseDTO;
import com.proyectocoryb.mapper.EspecialidadMapper;
import com.proyectocoryb.model.Especialidad;
import com.proyectocoryb.model.Estado;
import com.proyectocoryb.repository.EspecialidadRepository;
import com.proyectocoryb.service.EspecialidadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final EspecialidadMapper especialidadMapper;

    @Override
    @Transactional
    public EspecialidadResponseDTO crearEspecialidad(EspecialidadRequestDTO requestDTO) {
        Especialidad especialidad = especialidadMapper.toEntity(requestDTO);
        especialidad = especialidadRepository.save(especialidad);

        log.info("Especialidad creada. id={}", especialidad.getId());

        return especialidadMapper.toResponse(especialidad);
    }

    @Override
    public EspecialidadResponseDTO actualizarEspecialidad(Long id, EspecialidadRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void eliminarEspecialidad(Long id) {

    }

    @Override
    public EspecialidadResponseDTO obtenerEspecialidadPorId(Long id) {
        return null;
    }

    @Override
    public Page<EspecialidadResponseDTO> listarEspecialidades(Pageable pageable) {
        return null;
    }

    @Override
    public Page<EspecialidadResponseDTO> buscarEspecialidadPorNombre(String nombre, Pageable pageable) {
        return null;
    }

    @Override
    public Page<EspecialidadResponseDTO> listarEspecialidadPorEstado(Estado estado, Pageable pageable) {
        return null;
    }
}
