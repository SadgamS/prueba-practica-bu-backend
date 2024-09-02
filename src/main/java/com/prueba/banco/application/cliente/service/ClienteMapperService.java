package com.prueba.banco.application.cliente.service;

import com.prueba.banco.application.cliente.dto.RespuestaClienteDto;
import com.prueba.banco.application.cliente.entity.ClienteEntity;

public class ClienteMapperService {

    public static RespuestaClienteDto toRespuestaCliente(ClienteEntity clienteEntity) {
        return RespuestaClienteDto.builder()
                .id(clienteEntity.getId())
                .nombres(clienteEntity.getUsuario().getPersona().getNombres())
                .apellidos(clienteEntity.getUsuario().getPersona().getApellidoPaterno() + " "
                        + clienteEntity.getUsuario().getPersona().getApellidoMaterno())
                .documentoIdentidad(clienteEntity.getUsuario().getPersona().getDocumentoIdentidad())
                .tipoDocumento(clienteEntity.getUsuario().getPersona().getTipoDocumento())
                .fechaNacimiento(clienteEntity.getUsuario().getPersona().getFechaNacimiento())
                .genero(clienteEntity.getUsuario().getPersona().getGenero())
                .cuentas(clienteEntity.getCuentas())
                .estado(clienteEntity.getEstado())
                .build();

    }

}
