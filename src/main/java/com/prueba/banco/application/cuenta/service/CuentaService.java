package com.prueba.banco.application.cuenta.service;

import org.springframework.stereotype.Service;

import com.prueba.banco.application.cuenta.repository.CuentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService {
    private final CuentaRepository cuentaRepository;
}
