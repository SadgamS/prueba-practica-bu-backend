package com.prueba.banco.application.cliente.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    @GetMapping()
    public String getMethodName() {
        return new String("Listado de clientes");
    }
    

}
