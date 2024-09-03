package com.prueba.banco.application.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prueba.banco.application.cuenta.entity.CuentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    @Query("SELECT c FROM CuentaEntity c WHERE c.cliente.id = :idCliente AND c.estado = 'ACTIVO'")
    Page<CuentaEntity> findByClienteId(Long idCliente, Pageable pageable);
}
