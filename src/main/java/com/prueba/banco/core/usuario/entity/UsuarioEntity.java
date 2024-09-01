package com.prueba.banco.core.usuario.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.banco.core.authorization.entity.RolEntity;
import com.prueba.banco.core.authorization.entity.UsuarioRolEntity;
import com.prueba.banco.core.config.auditing.entity.AuditEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "usuarios", name = "usuarios")
public class UsuarioEntity extends AuditEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_seq")
    @SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", schema = "usuarios", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_usuario", length = 50)
    private String username;

    @JsonIgnore
    @Column(name = "contrasena", length = 100)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private PersonaEntity persona;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UsuarioRolEntity> roles;

    public void agregarRol(RolEntity rol) {
        UsuarioRolEntity usuarioRol = UsuarioRolEntity.builder().usuario(this).rol(rol).build();
        roles.add(usuarioRol);
    }

    public void eliminarRol(RolEntity rol) {
        roles.removeIf(usuarioRol -> usuarioRol.getRol().equals(rol));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(UsuarioRolEntity::getRol).map(RolEntity::getAuthorities).flatMap(List::stream)
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
