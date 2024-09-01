package com.prueba.banco.core.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.banco.core.authorization.entity.UsuarioRolEntity;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRolEntity, Long> {

}
