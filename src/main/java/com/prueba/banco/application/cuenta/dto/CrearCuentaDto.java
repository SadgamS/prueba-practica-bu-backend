package com.prueba.banco.application.cuenta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearCuentaDto {

    @NotNull(message = "El id del cliente es obligatorio")
    private Long idCliente;

    @NotBlank(message = "El tipo de producto es obligatorio")
    @NotNull(message = "El tipo de producto es obligatorio")
    private String tipoProducto;

    @NotBlank(message = "El número de cuenta es obligatorio")
    @NotNull(message = "El número de cuenta es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El número de cuenta solo puede contener números")
    private String numeroCuenta;

    @NotBlank(message = "La moneda es obligatoria")
    @NotNull(message = "La moneda es obligatoria")
    private String moneda;

    @NotBlank(message = "El monto es obligatorio")
    @NotNull(message = "El monto es obligatorio")
    private String monto;

    @NotBlank(message = "La sucursal es obligatoria")
    @NotNull(message = "La sucursal es obligatoria")
    private String sucursal;

}
