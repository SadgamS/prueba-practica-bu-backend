package com.prueba.banco.core.usuario.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.banco.core.authorization.entity.UsuarioRolEntity;
import com.prueba.banco.core.authorization.service.RolService;
import com.prueba.banco.core.usuario.dto.CrearPersonaDto;
import com.prueba.banco.core.usuario.dto.RegistrarUsuarioDto;
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

    
    public UsuarioEntity obtenerUsuarioPorUserName(String username) {
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public UsuarioEntity crearUsuario(RegistrarUsuarioDto registrarUsuarioDto) {

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

        return usuarioRepository.save(usuario);
    }
}
