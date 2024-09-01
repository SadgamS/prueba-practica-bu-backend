package com.prueba.banco.core.authorization.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.banco.common.constantes.RoleEnum;
import com.prueba.banco.core.authorization.entity.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findByNombre(RoleEnum nombre);

}
