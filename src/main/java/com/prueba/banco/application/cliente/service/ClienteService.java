package com.prueba.banco.application.cliente.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.banco.application.cliente.dto.CrearClienteDto;
import com.prueba.banco.application.cliente.dto.RespuestaClienteDto;
import com.prueba.banco.application.cliente.entity.ClienteEntity;
import com.prueba.banco.application.cliente.repository.ClienteRepository;
import com.prueba.banco.common.PageResponse;
import com.prueba.banco.common.constantes.RoleEnum;
import com.prueba.banco.core.authorization.entity.UsuarioRolEntity;
import com.prueba.banco.core.authorization.service.RolService;
import com.prueba.banco.core.usuario.dto.CrearPersonaDto;
import com.prueba.banco.core.usuario.entity.UsuarioEntity;
import com.prueba.banco.core.usuario.repository.UsuarioRepository;
import com.prueba.banco.core.usuario.service.PersonaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
        private final ClienteRepository clienteRepository;
        private final PersonaService personaService;
        private final PasswordEncoder passwordEncoder;
        private final UsuarioRepository usuarioRepository;
        private final RolService rolService;

        public Long crearCliente(UsuarioEntity usuario) {
                var cliente = ClienteEntity.builder()
                                .usuario(usuario)
                                .build();
                return clienteRepository.save(cliente).getId();
        }

        @Transactional
        public Long crearCliente(CrearClienteDto crearClienteDto) {
                var personaExistente = personaService
                                .existePersonaPorDocumentoIdentidad(crearClienteDto.getDocumentoIdentidad());

                if (personaExistente) {
                        throw new RuntimeException("Ya existe una persona con el documento de identidad ingresado");
                }

                var personaDto = CrearPersonaDto.builder()
                                .nombres(crearClienteDto.getNombres())
                                .apellidoPaterno(crearClienteDto.getApellidoPaterno())
                                .apellidoMaterno(crearClienteDto.getApellidoMaterno())
                                .tipoDocumento(crearClienteDto.getTipoDocumento())
                                .documentoIdentidad(crearClienteDto.getDocumentoIdentidad())
                                .fechaNacimiento(crearClienteDto.getFechaNacimiento())
                                .genero(crearClienteDto.getGenero())
                                .build();
                var persona = personaService.crearPersona(personaDto);

                var usuario = UsuarioEntity.builder()
                                .persona(persona)
                                .username(persona.getDocumentoIdentidad())
                                .password(passwordEncoder.encode(persona.getDocumentoIdentidad()))
                                .roles(new HashSet<>())
                                .build();
                Set<UsuarioRolEntity> usuariosRoles = new HashSet<>();
                var rol = rolService.obtenerRolPorNombre(RoleEnum.USUARIO);
                UsuarioRolEntity usuarioRol = UsuarioRolEntity.builder()
                                .rol(rol)
                                .usuario(usuario)
                                .build();
                usuariosRoles.add(usuarioRol);
                usuario.setRoles(usuariosRoles);

                var usuarioGuardado = usuarioRepository.save(usuario);

                return clienteRepository.save(ClienteEntity.builder()
                                .usuario(usuarioGuardado)
                                .build()).getId();
        }

        @Transactional
        public Long editarCliente(CrearClienteDto crearClienteDto, Long id) {
                ClienteEntity cliente = clienteRepository.findById(id).orElseThrow(
                                () -> new RuntimeException("Cliente no encontrado"));

                var persona = cliente.getUsuario().getPersona();
                persona.setNombres(crearClienteDto.getNombres());
                persona.setApellidoPaterno(crearClienteDto.getApellidoPaterno());
                persona.setApellidoMaterno(crearClienteDto.getApellidoMaterno());
                persona.setTipoDocumento(crearClienteDto.getTipoDocumento());
                persona.setDocumentoIdentidad(crearClienteDto.getDocumentoIdentidad());
                persona.setFechaNacimiento(crearClienteDto.getFechaNacimiento());
                persona.setGenero(crearClienteDto.getGenero());

                personaService.editarPersona(persona);

                return cliente.getId();
        }

        public RespuestaClienteDto obtenerClientePorId(Long id) {
                return clienteRepository.findById(id).map(
                                ClienteMapperService::toRespuestaCliente).orElseThrow(
                                                () -> new RuntimeException("Cliente no encontrado"));
        }

        public ClienteEntity obtenerClienteEntityPorId(Long id) {
                return clienteRepository.findById(id).orElseThrow(
                                () -> new RuntimeException("Cliente no encontrado"));
        }

        public PageResponse<RespuestaClienteDto> obtenerClientes(int page, int size, String sort) {
                Sort.Direction direction = Sort.Direction.ASC;
                String sortBy = sort;

                if (sort.startsWith("-")) {
                        direction = Sort.Direction.DESC;
                        sortBy = "usuario.persona." + sort.substring(1);
                }

                Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "usuario.persona." + sortBy));
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
