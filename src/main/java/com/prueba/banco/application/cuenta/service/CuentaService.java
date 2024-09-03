package com.prueba.banco.application.cuenta.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.prueba.banco.application.cliente.service.ClienteService;
import com.prueba.banco.application.cuenta.dto.CrearCuentaDto;
import com.prueba.banco.application.cuenta.dto.RespuestaCuentaDto;
import com.prueba.banco.application.cuenta.entity.CuentaEntity;
import com.prueba.banco.application.cuenta.enums.MonedaEnum;
import com.prueba.banco.application.cuenta.repository.CuentaRepository;
import com.prueba.banco.common.PageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ClienteService clienteService;

    public Long crearCuenta(CrearCuentaDto crearCuentaDto) {

        var cliente = clienteService.obtenerClienteEntityPorId(crearCuentaDto.getIdCliente());

        var cuenta = CuentaEntity.builder()
                .tipoProducto(crearCuentaDto.getTipoProducto())
                .numeroCuenta(crearCuentaDto.getNumeroCuenta())
                .moneda(MonedaEnum.valueOf(crearCuentaDto.getMoneda()))
                .monto(new BigDecimal(crearCuentaDto.getMonto()))
                .fechaApertura(LocalDate.now())
                .sucursal(crearCuentaDto.getSucursal())
                .build();

        cuenta.setCliente(cliente);

        return cuentaRepository.save(cuenta).getId();
    }

    public PageResponse<RespuestaCuentaDto> obtenerCuentasPorCliente(Long idCliente, int page, int size, String sort) {
        var cliente = clienteService.obtenerClienteEntityPorId(idCliente);

        if (cliente.getEstado().equals("INACTIVO")) {
            throw new RuntimeException("El cliente no se encuentra activo");
        }

        Sort.Direction direction = Sort.Direction.ASC;
        String sortBy = sort;

        if (sort.startsWith("-")) {
            direction = Sort.Direction.DESC;
            sortBy = sort.substring(1);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<CuentaEntity> cuentas = cuentaRepository.findByClienteId(idCliente, pageable);

        List<RespuestaCuentaDto> respuestaCuentas = cuentas.stream().map(
                CuentaMapperService::toRespuestaCliente).toList();

        return new PageResponse<>(
                cuentas.getNumber(),
                cuentas.getTotalPages(),
                cuentas.getTotalElements(),
                cuentas.getSize(),
                respuestaCuentas);
    }

    public Long editarCuenta(CrearCuentaDto cuenta, Long id) {
        CuentaEntity cuentaEntity = cuentaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cuenta no encontrada"));

        cuentaEntity.setTipoProducto(cuenta.getTipoProducto());
        cuentaEntity.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaEntity.setMoneda(MonedaEnum.valueOf(cuenta.getMoneda()));
        cuentaEntity.setMonto(new BigDecimal(cuenta.getMonto()));
        cuentaEntity.setSucursal(cuenta.getSucursal());

        return cuentaRepository.save(cuentaEntity).getId();
    }

    public void eliminarCuenta(Long id) {
        CuentaEntity cuenta = cuentaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cuenta no encontrada"));

        cuenta.setEstado("INACTIVO");

        cuentaRepository.save(cuenta);
    }
}
