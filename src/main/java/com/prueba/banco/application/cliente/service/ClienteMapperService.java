package com.prueba.banco.application.cliente.service;

import com.prueba.banco.application.cliente.dto.RespuestaClienteDto;
import com.prueba.banco.application.cliente.entity.ClienteEntity;

public class ClienteMapperService {

    public static RespuestaClienteDto toRespuestaCliente(ClienteEntity clienteEntity) {
        return RespuestaClienteDto.builder()
                .id(clienteEntity.getId())
                .nombres(clienteEntity.getUsuario().getPersona().getNombres())
                .apellidoPaterno(clienteEntity.getUsuario().getPersona().getApellidoPaterno())
                .apellidoMaterno(clienteEntity.getUsuario().getPersona().getApellidoMaterno())
                .documentoIdentidad(clienteEntity.getUsuario().getPersona().getDocumentoIdentidad())
                .tipoDocumento(clienteEntity.getUsuario().getPersona().getTipoDocumento())
                .fechaNacimiento(clienteEntity.getUsuario().getPersona().getFechaNacimiento())
                .genero(clienteEntity.getUsuario().getPersona().getGenero())
                .estado(clienteEntity.getEstado())
                .build();

    }

}
