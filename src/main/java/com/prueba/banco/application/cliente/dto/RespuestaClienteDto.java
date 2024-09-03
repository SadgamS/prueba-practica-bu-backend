package com.prueba.banco.application.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaClienteDto {

    private Long id;

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String documentoIdentidad;

    private String tipoDocumento;

    private LocalDate fechaNacimiento;

    private String genero;

    private String estado;

}
