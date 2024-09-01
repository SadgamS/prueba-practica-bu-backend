package com.prueba.banco.core.config.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.prueba.banco.core.usuario.entity.UsuarioEntity;

public class ApplicationAuditAware implements AuditorAware<Long> {

    @Override
    public @NonNull Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();

        return Optional.ofNullable(usuario.getId());
    }

}
