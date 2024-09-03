package com.prueba.banco.application.cuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaCuentaDto {

    private Long id;

    private String tipoProducto;

    private String numeroCuenta;

    private String moneda;

    private String monto;

    private String fechaApertura;

    private String sucursal;

    private String estado;
}
