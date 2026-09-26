package com.proyectocoryb.service.impl;

import com.proyectocoryb.dto.request.EspecialidadRequestDTO;
import com.proyectocoryb.dto.response.EspecialidadResponseDTO;
import com.proyectocoryb.exception.ResourceNotFoundException;
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
    @Transactional
    public EspecialidadResponseDTO actualizarEspecialidad(Long id, EspecialidadRequestDTO requestDTO) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));

        especialidadMapper.updateEntity(especialidad, requestDTO);
        Especialidad updateEspecialidad = especialidadRepository.save(especialidad);

        log.info("Especialidad actualizada. id={}", especialidad.getId());
        return especialidadMapper.toResponse(updateEspecialidad);
    }

    @Override
    @Transactional
    public void eliminarEspecialidad(Long id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: "+ id));
        log.info("Especialidad eliminada. id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public EspecialidadResponseDTO obtenerEspecialidadPorId(Long id) {
        return especialidadRepository.findById(id)
                .map(especialidadMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EspecialidadResponseDTO> listarEspecialidades(Pageable pageable) {
        return especialidadRepository.findAll(pageable)
                .map(especialidadMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EspecialidadResponseDTO> buscarEspecialidadPorNombre(String nombre, Pageable pageable) {
        return especialidadRepository.searchByNombre(nombre, pageable)
                .map(especialidadMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EspecialidadResponseDTO> listarEspecialidadPorEstado(String estadoStr, Pageable pageable) {
        Estado estado;
        try{
            estado = Estado.valueOf(estadoStr.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Estado invalido: " + estadoStr);
        }
        return especialidadRepository.findByEstado(estado, pageable)
                .map(especialidadMapper::toResponse);
    }
}
