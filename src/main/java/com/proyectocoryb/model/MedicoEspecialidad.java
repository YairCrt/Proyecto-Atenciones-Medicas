package com.proyectocoryb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "medico_especialidad")
public class MedicoEspecialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Un empleado puede tener varias especialidades
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false, foreignKey = @ForeignKey(name = "FK_medico_especialidad_empleado"))
    private Empleado empleado;

    //Una especialidad puede tener muchos medicos(empleados)
    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false, foreignKey = @ForeignKey(name = "FK_medico_especialidad_especialidad"))
    private Especialidad especialidad;



}
