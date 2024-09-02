package com.prueba.banco.core.config.auditing.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.prueba.banco.common.constantes.GlobalEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @CreatedDate
    @Column(name = "_fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "_fecha_modificacion", insertable = false)
    private LocalDateTime fechaModificacion;

    @CreatedBy
    @Column(name = "_creado_por", nullable = false, updatable = false)
    private Long creadoPor;

    @LastModifiedBy
    @Column(name = "_modificado_por", insertable = false)
    private Long modificadoPor;

    @Column(name = "_estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "_transaccion", nullable = false, length = 20)
    private String transaccion;

    @PrePersist
    public void prePersist() {
        if (this.estado == null || this.estado.isEmpty()) {
            this.estado = GlobalEnum.ACTIVO.getValue();
        }
        if (creadoPor == null) {
            creadoPor = 1L;
        }
        this.transaccion = GlobalEnum.CREACION.getValue();
    }

    @PreUpdate
    public void preUpdate() {
        if (this.estado == null) {
            this.estado = GlobalEnum.ACTIVO.getValue();
        }
        this.transaccion = GlobalEnum.ACTUALIZACION.getValue();
    }

}
