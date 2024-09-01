package com.prueba.banco.core.authorization.service;

import java.util.Set;
import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.banco.core.authorization.entity.UsuarioRolEntity;
import com.prueba.banco.core.authorization.repository.UsuarioRolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {
    private final UsuarioRolRepository usuarioRolRepository;

    public List<UsuarioRolEntity> guardar(Set<UsuarioRolEntity> usuarioRolEntity) {
        return usuarioRolRepository.saveAll(usuarioRolEntity);
    }

}
