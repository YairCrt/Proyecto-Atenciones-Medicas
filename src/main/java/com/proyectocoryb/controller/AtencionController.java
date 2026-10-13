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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
