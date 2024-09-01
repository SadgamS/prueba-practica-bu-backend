package com.prueba.banco.core.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    @NotNull(message = "El usuario es obligatorio")
    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @NotNull(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

}
