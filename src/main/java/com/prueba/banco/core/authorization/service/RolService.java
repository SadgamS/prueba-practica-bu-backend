package com.prueba.banco.core.authorization.service;

import org.springframework.stereotype.Service;

import com.prueba.banco.common.constantes.RoleEnum;
import com.prueba.banco.core.authorization.entity.RolEntity;
import com.prueba.banco.core.authorization.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public RolEntity obtenerRolPorNombre(RoleEnum nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }
}
