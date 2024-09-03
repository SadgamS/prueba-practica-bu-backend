package com.prueba.banco.application.cuenta.service;

import com.prueba.banco.application.cuenta.dto.RespuestaCuentaDto;
import com.prueba.banco.application.cuenta.entity.CuentaEntity;

public class CuentaMapperService {
    public static RespuestaCuentaDto toRespuestaCliente(CuentaEntity cuentaEntity) {
        return RespuestaCuentaDto.builder()
                .id(cuentaEntity.getId())
                .tipoProducto(cuentaEntity.getTipoProducto())
                .numeroCuenta(cuentaEntity.getNumeroCuenta())
                .moneda(cuentaEntity.getMoneda().name())
                .monto(cuentaEntity.getMonto().toString())
                .fechaApertura(cuentaEntity.getFechaApertura().toString())
                .sucursal(cuentaEntity.getSucursal())
                .estado(cuentaEntity.getEstado())
                .build();

    }
}
