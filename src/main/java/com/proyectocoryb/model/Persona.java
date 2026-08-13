package com.proyectocoryb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "persona", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String nombre;

    @NotBlank(message = "Email requerido")
    @Email(message = "Email debe de ser válido")
    private String email;

    @NotNull(message = "El estado es requerido")
    @Enumerated(EnumType.STRING)
    private Estado estado;

}
