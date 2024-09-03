package com.prueba.banco.core.usuario.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.banco.application.cliente.service.ClienteService;
import com.prueba.banco.core.authorization.entity.UsuarioRolEntity;
import com.prueba.banco.core.authorization.service.RolService;
import com.prueba.banco.core.usuario.dto.CrearPersonaDto;
import com.prueba.banco.core.usuario.dto.RegistrarUsuarioDto;
import com.prueba.banco.core.usuario.dto.ResponsePerfilDto;
import com.prueba.banco.core.usuario.entity.UsuarioEntity;
import com.prueba.banco.core.usuario.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaService personaService;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;
    private final ClienteService clienteService;

    public UsuarioEntity obtenerUsuarioPorUserName(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    public ResponsePerfilDto obtenerPerfil(
            String username) {
        var usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        String nombreCompleto = usuario.getPersona().getNombres()
                + " "
                + usuario.getPersona().getApellidoPaterno()
                + " "
                + usuario.getPersona().getApellidoMaterno();

        String rol = usuario.getRoles().stream().findFirst().get().getRol().getNombre().toString();

        return ResponsePerfilDto.builder()
                .usuario(usuario.getUsername())
                .nombreCompleto(nombreCompleto)
                .rol(rol)
                .build();
    }

    @Transactional
    public UsuarioEntity crearUsuario(RegistrarUsuarioDto registrarUsuarioDto) {

        var personaExistente = personaService
                .existePersonaPorDocumentoIdentidad(registrarUsuarioDto.getDocumentoIdentidad());

        if (personaExistente) {
            throw new RuntimeException("Ya existe una persona con el documento de identidad ingresado");
        }

        var personaDto = CrearPersonaDto.builder()
                .nombres(registrarUsuarioDto.getNombres())
                .apellidoPaterno(registrarUsuarioDto.getApellidoPaterno())
                .apellidoMaterno(registrarUsuarioDto.getApellidoMaterno())
                .tipoDocumento(registrarUsuarioDto.getTipoDocumento())
                .documentoIdentidad(registrarUsuarioDto.getDocumentoIdentidad())
                .fechaNacimiento(registrarUsuarioDto.getFechaNacimiento())
                .genero(registrarUsuarioDto.getGenero())
                .build();
        var persona = personaService.crearPersona(personaDto);

        var usuario = UsuarioEntity.builder()
                .persona(persona)
                .username(registrarUsuarioDto.getUsername())
                .password(passwordEncoder.encode(registrarUsuarioDto.getPassword()))
                .roles(new HashSet<>())
                .build();

        Set<UsuarioRolEntity> usuariosRoles = new HashSet<>();
        for (var rolNombre : registrarUsuarioDto.getRoles()) {
            var rol = rolService.obtenerRolPorNombre(rolNombre);
            UsuarioRolEntity usuarioRol = UsuarioRolEntity.builder()
                    .rol(rol)
                    .usuario(usuario)
                    .build();
            usuariosRoles.add(usuarioRol);
        }

        usuario.setRoles(usuariosRoles);

        var usuarioGuardado = usuarioRepository.save(usuario);

        clienteService.crearCliente(usuarioGuardado);

        return usuarioGuardado;
    }
}
