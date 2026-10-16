package com.proyectocoryb.controller;

import com.proyectocoryb.dto.request.AtencionRequestDTO;
import com.proyectocoryb.dto.response.AtencionResponseDTO;
import com.proyectocoryb.service.AtencionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/atenciones")
@RequiredArgsConstructor
@Slf4j
public class AtencionController {
    private final AtencionService atencionService;

    @Operation(summary = "Crear una nueva atención médica")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Atención creada"),
            @ApiResponse(responseCode = "400", description = "Request válido"),
            @ApiResponse(responseCode = "404", description = "Paciente o empleado no encontrado")
    })

    @PostMapping
    public ResponseEntity<AtencionResponseDTO> crearAtencion(@Valid @RequestBody AtencionRequestDTO requestDto) {
        log.info("Solicitud para crear atención recibida: {}", requestDto);
        AtencionResponseDTO response = atencionService.crearAtencion(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obtener una atención por su ID")
    @GetMapping
    public ResponseEntity<AtencionResponseDTO> obtenerAtencionPorId(@PathVariable Long id) {
        log.info("Buscando atención con id={}", id);
        return ResponseEntity.ok(atencionService.obtenerAtencionPorId(id));

    }

    @Operation(summary = "Listar todas las atenciones paginadas")
    @GetMapping
    public ResponseEntity<Page<AtencionResponseDTO>> listarTodas(@ParameterObject Pageable pageable){
        log.info("Listando todas las atenciones, page={} size={}", pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(atencionService.listarTodas(pageable));
    }

    @Operation(summary = "Listar atenciones por rango de fechas paginadas")
    @GetMapping("/fecha")
    public ResponseEntity<Page<AtencionResponseDTO>> listarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fechaFin,
            @ParameterObject Pageable pageable){
        log.info("Buscando atenciones estre {} y {}, page={} size={}",
                fechaInicio, fechaFin, pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(atencionService.listarPorRangoFechas(fechaInicio, fechaFin, pageable));
    }

    @Operation(summary = "Buscar atenciones por motivo paginadas")
    @GetMapping("/motivo")
    public ResponseEntity<Page<AtencionResponseDTO>> buscarPorMotivo(
            @RequestParam String motivo,
            @ParameterObject Pageable pageable){
        log.info("Buscando atenciones por motivo='{}', page={} size{}",
                motivo, pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(atencionService.buscarPorMotivo(motivo, pageable));
    }

    @Operation(summary = "Actualizar una atención existente")
    @PutMapping("/{id}")
    public ResponseEntity<AtencionResponseDTO> actualizarAtencion(
            @PathVariable Long id,
            @Valid @RequestBody AtencionRequestDTO requestDTO){
        log.info("Solicitud para actualizar atención con id={}: {}", id, requestDTO);
        AtencionResponseDTO response = atencionService.actualizarAtencion(id, requestDTO);
        return ResponseEntity.ok(response);
    }
}


