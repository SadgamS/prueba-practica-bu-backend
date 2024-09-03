package com.prueba.banco.core.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePerfilDto {
    private String usuario;

    private String nombreCompleto;

    private String rol;
}
