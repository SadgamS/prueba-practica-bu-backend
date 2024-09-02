package com.prueba.banco.application.cliente.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.banco.application.cliente.dto.RespuestaClienteDto;
import com.prueba.banco.application.cliente.entity.ClienteEntity;
import com.prueba.banco.application.cliente.repository.ClienteRepository;
import com.prueba.banco.common.PageResponse;
import com.prueba.banco.core.usuario.entity.UsuarioEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Long crearCliente(UsuarioEntity usuario) {
        var cliente = ClienteEntity.builder()
                .usuario(usuario)
                .build();
        return clienteRepository.save(cliente).getId();
    }

    public RespuestaClienteDto obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).map(
                ClienteMapperService::toRespuestaCliente).orElseThrow(
                        () -> new RuntimeException("Cliente no encontrado"));
    }

    public PageResponse<RespuestaClienteDto> obtenerClientes(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ClienteEntity> clientes = clienteRepository.findAllByEstado(pageable);
        List<RespuestaClienteDto> respuestaClientes = clientes.stream().map(
                ClienteMapperService::toRespuestaCliente).toList();

        return new PageResponse<>(
                clientes.getNumber(),
                clientes.getTotalPages(),
                clientes.getTotalElements(),
                clientes.getSize(),
                respuestaClientes);

    }

    @Transactional
    public void eliminarCliente(Long id) {
       ClienteEntity cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado"));
        
        cliente.setEstado("INACTIVO");

        clienteRepository.save(cliente);

    }

}
