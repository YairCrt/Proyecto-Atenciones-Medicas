package com.proyectocoryb.service.impl;

import com.proyectocoryb.dto.request.AtencionRequestDTO;
import com.proyectocoryb.dto.response.AtencionResponseDTO;
import com.proyectocoryb.exception.InvalidRequestException;
import com.proyectocoryb.exception.ResourceNotFoundException;
import com.proyectocoryb.mapper.AtencionMapper;
import com.proyectocoryb.model.Atencion;
import com.proyectocoryb.model.Empleado;
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
        return null;
    }

    @Override
    public Page<AtencionResponseDTO> listarTodas(Pageable pageable) {
        return null;
    }

    @Override
    public Page<AtencionResponseDTO> listarPorPaciente(Long pacienteId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AtencionResponseDTO> listarPorEmpleado(Long empleadoId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AtencionResponseDTO> listarPorEstado(Long empleadoId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AtencionResponseDTO> listarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AtencionResponseDTO> buscarPorMotivo(String motivo, Pageable pageable) {
        return null;
    }

    @Override
    public AtencionResponseDTO actualizarAtencion(Long id, AtencionRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void eliminarAtencion(Long id) {

    }

    @Override
    public Page<AtencionResponseDTO> listarAtencionesPacienteAutenticado(Pageable pageable) {
        return null;
    }
}
