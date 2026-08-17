package com.proyectocoryb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "atencion")
public class Atencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Fecga de atención requerida")
    private LocalDateTime fecha;

    @NotBlank(message = "Motivo de la atencion requerido")
    private String motivo;

    //Un paciente puede tener varias atenciones medicas
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_atencion_paciente"))
    private Paciente paciente;

    //Un empleado puede registrar varias atenciones medicas
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false, foreignKey = @ForeignKey(name = "FK_atencion_empleado"))
    private Empleado empleado;

    //Guardar el estado de la atencion
    @NotNull(message = "El estado es requerido")
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
