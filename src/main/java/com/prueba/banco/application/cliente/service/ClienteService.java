package com.prueba.banco.application.cliente.service;

import org.springframework.stereotype.Service;

import com.prueba.banco.application.cliente.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
}
