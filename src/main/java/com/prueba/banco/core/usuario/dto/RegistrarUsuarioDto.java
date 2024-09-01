package com.prueba.banco.core.usuario.dto;

import java.time.LocalDate;
import java.util.Set;

import com.prueba.banco.common.constantes.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarUsuarioDto {

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String documentoIdentidad;
    private LocalDate fechaNacimiento;
    private String genero;
    private String username;
    private String password;

    private Set<RoleEnum> roles;
}
