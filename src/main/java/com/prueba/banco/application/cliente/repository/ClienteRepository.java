package com.prueba.banco.application.cliente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.prueba.banco.application.cliente.entity.ClienteEntity;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.estado = 'ACTIVO'")
    Page<ClienteEntity> findAllByEstado( Pageable pageable);

}
