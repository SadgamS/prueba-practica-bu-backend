package com.prueba.banco.core.authentication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.prueba.banco.core.authentication.dto.AuthenticationDto;
import com.prueba.banco.core.authentication.response.AuthenticationResponse;
import com.prueba.banco.core.usuario.dto.RegistrarUsuarioDto;
import com.prueba.banco.core.usuario.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioService usuarioService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registrar(RegistrarUsuarioDto registrarUsuarioDto) {
        var usuario = usuarioService.crearUsuario(registrarUsuarioDto);
        System.out.println("Usuario creado: " + usuario);

        var jwtToken = jwtService.generateToken(usuario);

        String nombreCompleto = usuario.getPersona().getNombres()
                + " "
                + usuario.getPersona().getApellidoPaterno()
                + " "
                + usuario.getPersona().getApellidoMaterno();

        String rol = usuario.getRoles().stream().findFirst().get().getRol().getNombre().toString();

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .usuario(usuario.getUsername())
                .nombreCompleto(nombreCompleto)
                .rol(rol)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationDto authenticationDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsuario(),
                        authenticationDto.getContrasena()));
        var usuario = usuarioService.obtenerUsuarioPorUserName(authenticationDto.getUsuario());

        if (usuario == null) {
            throw new BadCredentialsException("Usuario no encontrado");
        }

        var jwtToken = jwtService.generateToken(usuario);

        String nombreCompleto = usuario.getPersona().getNombres()
                + " "
                + usuario.getPersona().getApellidoPaterno()
                + " "
                + usuario.getPersona().getApellidoMaterno();

        String rol = usuario.getRoles().stream().findFirst().get().getRol().getNombre().toString();

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .usuario(usuario.getUsername())
                .nombreCompleto(nombreCompleto)
                .rol(rol)
                .build();
    }

}
