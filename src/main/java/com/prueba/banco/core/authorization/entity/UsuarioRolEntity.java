package com.prueba.banco.core.authorization.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prueba.banco.core.config.auditing.entity.AuditEntity;
import com.prueba.banco.core.usuario.entity.UsuarioEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "usuarios", name = "usuarios_roles")
public class UsuarioRolEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_roles_id_seq")
    @SequenceGenerator(name = "usuarios_roles_id_seq", sequenceName = "usuarios_roles_id_seq", schema = "usuarios", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    private RolEntity rol;
}
