package com.prueba.banco.core.usuario.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.banco.core.config.response.ResponseRest;
import com.prueba.banco.core.usuario.dto.ResponsePerfilDto;
import com.prueba.banco.core.usuario.entity.UsuarioEntity;
import com.prueba.banco.core.usuario.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<ResponseRest<ResponsePerfilDto>> obtenerPerfil(
        Authentication connectedUser
    ) {
        ResponseRest<ResponsePerfilDto> response = new ResponseRest<>();

        var usuario = (UsuarioEntity) connectedUser.getPrincipal();

        var perfil = usuarioService.obtenerPerfil(usuario.getUsername());

        response.setCodigo(200);
        response.setNotificacion("Datos obtenidos correctamente");
        response.setCorrecto(true);
        response.setDatos(perfil);

        return ResponseEntity.ok(response);
    }

}
