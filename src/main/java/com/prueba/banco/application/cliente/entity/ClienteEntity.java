package com.prueba.banco.application.cliente.entity;

import java.util.Set;

import com.prueba.banco.application.cuenta.entity.CuentaEntity;
import com.prueba.banco.core.config.auditing.entity.AuditEntity;
import com.prueba.banco.core.usuario.entity.UsuarioEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "proyecto", name = "clientes")
public class ClienteEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_id_seq")
    @SequenceGenerator(name = "clientes_id_seq", sequenceName = "clientes_id_seq", schema = "proyecto", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CuentaEntity> cuentas;

}
