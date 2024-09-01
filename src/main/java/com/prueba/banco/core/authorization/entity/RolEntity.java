package com.prueba.banco.core.authorization.entity;

import com.prueba.banco.common.constantes.RoleEnum;
import com.prueba.banco.core.config.auditing.entity.AuditEntity;

import java.util.Set;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
// @EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "usuarios", name = "roles")
public class RolEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", schema = "usuarios", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", length = 50)
    @Enumerated(EnumType.STRING)
    private RoleEnum nombre;

    @OneToMany(mappedBy = "rol")
    private Set<UsuarioRolEntity> usuariosRoles;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + nombre.name()));
        return authorities;
    }

}
