package com.proyectocoryb.model;

import jakarta.persistence.*;
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

    private LocalDateTime fecha;

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
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
