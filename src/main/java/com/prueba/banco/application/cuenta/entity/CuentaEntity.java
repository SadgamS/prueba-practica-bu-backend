package com.prueba.banco.application.cuenta.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.prueba.banco.application.cliente.entity.ClienteEntity;
import com.prueba.banco.application.cuenta.enums.MonedaEnum;
import com.prueba.banco.core.config.auditing.entity.AuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "proyecto", name = "cuentas")
public class CuentaEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuentas_id_seq")
    @SequenceGenerator(name = "cuentas_id_seq", sequenceName = "cuentas_id_seq", schema = "proyecto", allocationSize = 1)
    private Long id;

    @Column(name = "tipo_producto", length = 50)
    private String tipoProducto;

    @Column(name = "numero_cuenta", length = 100)
    private String numeroCuenta;

    @Column(name = "moneda", length = 5)
    @Enumerated(EnumType.STRING)
    private MonedaEnum moneda;

    @Column(name = "monto",columnDefinition = "DECIMAL", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(name = "sucursal", length = 50)
    private String sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private ClienteEntity cliente;

}
