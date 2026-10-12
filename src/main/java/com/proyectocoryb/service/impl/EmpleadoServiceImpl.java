package com.proyectocoryb.service.impl;

import com.proyectocoryb.dto.request.EmpleadoRequestDTO;
import com.proyectocoryb.dto.response.EmpleadoResponseDTO;
import com.proyectocoryb.exception.ResourceNotFoundException;
import com.proyectocoryb.mapper.EmpleadoMapper;
import com.proyectocoryb.model.Empleado;
import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Persona;
import com.proyectocoryb.repository.EmpleadoRepository;
import com.proyectocoryb.repository.PersonaRepository;
import com.proyectocoryb.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;
    private final PersonaRepository personaRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    @Transactional
    public EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO requestDTO) {
        Persona persona = personaRepository.findById(requestDTO.getPersonaId())
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + requestDTO.getPersonaId()));

        Empleado empleado = empleadoMapper.toEntity(requestDTO);
        empleado.setPersona(persona);
        empleado = empleadoRepository.save(empleado);

        log.info("Empleado creado. id={}", empleado.getId());
        return empleadoMapper.toResponse(empleado);
    }

    @Override
    public EmpleadoResponseDTO actualizarEmpleado(Long id, EmpleadoRequestDTO requestDTO) {

        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        if(!empleado.getPersona().getId().equals(requestDTO.getPersonaId())){
            Persona persona = personaRepository.findById(requestDTO.getPersonaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + id));
            empleado.setPersona(persona);
        }

        empleadoMapper.updateEntity(empleado, requestDTO);
        Empleado updateEmpleado = empleadoRepository.save(empleado);

        log.info("Empleado actualizado. id{}", empleado.getId());
        return empleadoMapper.toResponse(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoResponseDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmpleadoResponseDTO> listarPorEstado(String estado, Pageable pageable) {
        Page<Empleado> page = empleadoRepository.findByEstado(Enum.valueOf(Estado.class, estado.toUpperCase()), pageable);
        return page.map(empleadoMapper::toResponse);
    }

    @Override
    @Transactional
    public void eliminarEmpleado(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id ));

        empleadoRepository.delete(empleado);

        log.info("Empleado eliminado. id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoResponseDTO> buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .map(empleadoMapper::toResponse);
    }
}
