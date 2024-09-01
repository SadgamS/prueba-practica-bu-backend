package com.prueba.banco.core.authentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.banco.core.authentication.dto.AuthenticationDto;
import com.prueba.banco.core.authentication.response.AuthenticationResponse;
import com.prueba.banco.core.authentication.service.AuthenticationService;
import com.prueba.banco.core.config.response.ResponseRest;
import com.prueba.banco.core.usuario.dto.RegistrarUsuarioDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registrar")
    public ResponseEntity<ResponseRest<AuthenticationResponse>> registrar(
            @RequestBody RegistrarUsuarioDto registrarUsuarioDto) {
        AuthenticationResponse responseAuth = authenticationService.registrar(registrarUsuarioDto);

        ResponseRest<AuthenticationResponse> response = new ResponseRest<>();

        response.setCodigo(200);
        response.setNotificacion("Usuario registrado");
        response.setCorrecto(true);
        response.setDatos(responseAuth);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseRest<AuthenticationResponse>> login(
            @RequestBody AuthenticationDto authenticationDto) {
        AuthenticationResponse responseAuth = authenticationService.authenticate(authenticationDto);

        ResponseRest<AuthenticationResponse> response = new ResponseRest<>();

        response.setCodigo(200);
        response.setNotificacion("Usuario autenticado");
        response.setCorrecto(true);
        response.setDatos(responseAuth);

        return ResponseEntity.ok(response);

    }

}
