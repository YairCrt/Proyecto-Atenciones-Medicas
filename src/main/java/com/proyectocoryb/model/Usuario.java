package com.proyectocoryb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "usuario"))
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Usuario obligatorio")
    private String usuario;

    @NotBlank(message = "Contraseña obligatoria")
    private String contrasena;

    //Cada usuario se asocia a una persona
    @OneToOne
    @JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_persona"))
    private Persona persona;

}
