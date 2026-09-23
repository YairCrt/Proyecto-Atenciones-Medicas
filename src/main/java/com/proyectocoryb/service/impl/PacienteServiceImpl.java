package com.proyectocoryb.service.impl;

import com.proyectocoryb.dto.request.PacienteRequestDTO;
import com.proyectocoryb.dto.response.PacienteResponseDTO;
import com.proyectocoryb.exception.ResourceNotFoundException;
import com.proyectocoryb.mapper.PacienteMapper;
import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Paciente;
import com.proyectocoryb.model.Persona;
import com.proyectocoryb.repository.PacienteRepository;
import com.proyectocoryb.repository.PersonaRepository;
import com.proyectocoryb.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PersonaRepository personaRepository;
    private final PacienteMapper pacienteMapper;

    @Override
    @Transactional
    public PacienteResponseDTO crearPaciente(PacienteRequestDTO requestDTO) {
        Persona persona = personaRepository.findById(requestDTO.getPersonaId())
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + requestDTO.getPersonaId()));

        Paciente paciente = pacienteMapper.toEntity(requestDTO);
        paciente.setPersona(persona);
        paciente = pacienteRepository.save(paciente);

        log.info("Paciente creado. id={}", paciente.getId());

        return pacienteMapper.toResponse(paciente);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponseDTO obtenerPacientePorId(Long id) {
        log.info("Obteniendo paciente con id = {}", id);

        return pacienteRepository.findById(id)
                .map(pacienteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO requestDTO) {
        log.info("Actualizando paciente con id={}", id);

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + id));

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + id));

        pacienteMapper.updateEntity(paciente, requestDTO);
        paciente.setPersona(persona);

        Paciente updatePaciente = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(updatePaciente);
    }

    @Override
    @Transactional
    public void eliminarPaciente(long id) {
        log.info("Eliminando paciente. id={}", id);

        if(!pacienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Paciente no encontrado con id: " + id);
        }
        pacienteRepository.deleteById(id);
        log.info("Paciente eliminado. id = {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PacienteResponseDTO> listarPacientes(Pageable pageable) {
        log.info("Listando pacientes paginados, page={} size={}", pageable.getPageNumber(), pageable.getPageSize());
        return pacienteRepository.findAll(pageable)
                .map(pacienteMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PacienteResponseDTO> listarPacientesPorEstado(Estado estado, Pageable pageable) {
        log.info("Listando pacientes por estado={}", estado);
        return pacienteRepository.findByEstado(estado)
                .map(pacienteMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listarActivos() {
        log.info("Listando todos los pacientes activos.");
        return pacienteRepository.findByEstado(Estado.ACTIVO)
                .stream()
                .map(pacienteMapper::toResponse)
                .collect(Collectors.toList());
    }
}
