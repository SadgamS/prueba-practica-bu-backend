package com.prueba.banco.core.usuario.entity;

import com.prueba.banco.core.config.auditing.entity.AuditEntity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "usuarios", name = "personas")
public class PersonaEntity extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personas_id_seq")
    @SequenceGenerator(name = "personas_id_seq", sequenceName = "personas_id_seq", schema = "usuarios", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombres", length = 100)
    private String nombres;

    @Column(name = "apellido_paterno", length = 100, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno;

    @Column(name = "tipo_documento", length = 15, nullable = false)
    private String tipoDocumento;

    @Column(name = "documento_identidad", length = 15, nullable = false)
    private String documentoIdentidad;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero", length = 1,  nullable = false)
    private String genero;

}
