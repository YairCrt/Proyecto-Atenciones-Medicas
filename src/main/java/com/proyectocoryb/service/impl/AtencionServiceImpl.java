package com.proyectocoryb.service.impl;

import com.proyectocoryb.dto.request.AtencionRequestDTO;
import com.proyectocoryb.dto.response.AtencionResponseDTO;
import com.proyectocoryb.exception.InvalidRequestException;
import com.proyectocoryb.exception.ResourceNotFoundException;
import com.proyectocoryb.mapper.AtencionMapper;
import com.proyectocoryb.model.Atencion;
import com.proyectocoryb.model.Empleado;
import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Paciente;
import com.proyectocoryb.repository.AtencionRepository;
import com.proyectocoryb.repository.EmpleadoRepository;
import com.proyectocoryb.repository.PacienteRepository;
import com.proyectocoryb.service.AtencionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class AtencionServiceImpl implements AtencionService {

    //Inyeccion de dependencias via constructor(más recomendado que 'autowired' Se requiere la anotacion RequiredArgsConstructor)
    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final AtencionMapper atencionMapper;

    @Override
    @Transactional //Define que un metodo clase se ejecuta dentro de una transaccion a la bd
    public AtencionResponseDTO crearAtencion(AtencionRequestDTO requestDTO) {
        if(requestDTO.getFecha() == null){
            throw new InvalidRequestException("La fecha de la atención es obligatoria");
        }

        if(requestDTO.getFecha().isBefore(LocalDateTime.now().minusMinutes(1))){
            throw new InvalidRequestException("La fecha de la atención no puede ser en el pasado");
        }

        Paciente paciente = pacienteRepository.findById(requestDTO.getPacienteId()).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + requestDTO.getPacienteId()));

        Empleado empleado = empleadoRepository.findById(requestDTO.getEmpleadoId()).orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + requestDTO.getEmpleadoId()));

        Atencion atencion = atencionMapper.toEntity(requestDTO);
        atencion.setPaciente(paciente);
        atencion.setEmpleado(empleado);

        atencion = atencionRepository.save(atencion);

        log.info("Atención creada. id={}", atencion.getId());

        return atencionMapper.toResponse(atencion);
    }

    @Override
    @Transactional(readOnly = true) //Operacion de solo lectura
    public AtencionResponseDTO obtenerAtencionPorId(Long id) {
       return atencionRepository.findById(id)
               .map(atencionMapper::toResponse)
               .orElseThrow(() -> new ResourceNotFoundException("Atencion no encontrada con id: " + id));

    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> listarTodas(Pageable pageable) {

        return atencionRepository.findAll(pageable)
                .map(atencionMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> listarPorPaciente(Long pacienteId, Pageable pageable) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + pacienteId));

        return atencionRepository.findByPaciente(paciente, pageable)
                .map(atencionMapper::toResponse); //Obtenemos el paciente y lo retornamos transformado en DTO
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> listarPorEmpleado(Long empleadoId, Pageable pageable) {

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + empleadoId));

        return atencionRepository.findByEmpleado(empleado, pageable)
                .map(atencionMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> listarPorEstado(Estado estado, Pageable pageable) {

        return atencionRepository.findByEstado(estado, pageable)
                .map(atencionMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> listarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable) {
        if(fechaInicio == null || fechaFin == null){
            throw new InvalidRequestException("Debe de proporcionar fecha de inicio y fin para el filtro.");
        }
        if(fechaFin.isBefore(fechaInicio)){
            throw new InvalidRequestException("La fecha de fin NO puede ser anterior a la fecha de inicio.");
        }

        return atencionRepository.findByFechaBetween(fechaInicio, fechaFin, pageable)
                .map(atencionMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> buscarPorMotivo(String motivo, Pageable pageable) {
        if(motivo == null || motivo.trim().isEmpty()){
            throw new InvalidRequestException("El motivo para busqueda no puede estar vacio.");
        }

        return atencionRepository.searchByMotivo(motivo, pageable)
                .map(atencionMapper::toResponse);
    }

    @Override
    public AtencionResponseDTO actualizarAtencion(Long id, AtencionRequestDTO requestDTO) {
        return null;
    }

    @Override
    @Transactional
    public void eliminarAtencion(Long id) {
        if(!atencionRepository.existsById(id)){
            throw new ResourceNotFoundException("Atencion no encontrada con ID: " + id);
        }
        atencionRepository.deleteById(id);
        log.info("Atencion eliminadoa. id = {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtencionResponseDTO> listarAtencionesDelPacienteAutenticado(String username, Pageable pageable) {
        Paciente paciente = pacienteRepository.findByUsuarioUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado para el usuario autenticado."));

        return atencionRepository.findByPaciente(paciente, pageable)
                .map(atencionMapper::toResponse);
    }
}
