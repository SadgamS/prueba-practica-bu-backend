package com.prueba.banco.application.cuenta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.banco.application.cuenta.dto.CrearCuentaDto;
import com.prueba.banco.application.cuenta.dto.RespuestaCuentaDto;
import com.prueba.banco.application.cuenta.service.CuentaService;
import com.prueba.banco.common.PageResponse;
import com.prueba.banco.core.config.response.ResponseRest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/cuenta")
@RequiredArgsConstructor
public class CuentaController {
    private final CuentaService cuentaService;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ResponseRest<PageResponse<RespuestaCuentaDto>>> obtenerCuentasPorCliente(
            @PathVariable("idCliente") Long idCliente,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {

        ResponseRest<PageResponse<RespuestaCuentaDto>> response = new ResponseRest<>();

        var cuentas = cuentaService.obtenerCuentasPorCliente(idCliente, page, size, sort);

        response.setCodigo(200);
        response.setNotificacion("Datos obtenidos correctamente");
        response.setCorrecto(true);
        response.setDatos(cuentas);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseRest<String>> crearCuenta(@Valid @RequestBody CrearCuentaDto crearCuentaDto) {
        ResponseRest<String> response = new ResponseRest<>();

        var idCuenta = cuentaService.crearCuenta(crearCuentaDto);

        response.setCodigo(200);
        response.setNotificacion("Cuenta creada correctamente");
        response.setCorrecto(true);
        response.setDatos("El id de la cuenta creada es: " + idCuenta);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseRest<String>> editarCuenta(
            @Valid @RequestBody CrearCuentaDto cuenta, @PathVariable("id") Long id) {

        Long idCuenta = cuentaService.editarCuenta(cuenta, id);

        ResponseRest<String> response = new ResponseRest<>();

        response.setCodigo(200);
        response.setNotificacion("Cliente editado correctamente");
        response.setCorrecto(true);
        response.setDatos("Cliente editado con id: " + idCuenta);

        return ResponseEntity.ok(response);

    }

    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<ResponseRest<String>> eliminarCuenta(
            @PathVariable("id") Long id) {
        ResponseRest<String> response = new ResponseRest<>();

        cuentaService.eliminarCuenta(id);

        response.setCodigo(200);
        response.setNotificacion("Cliente eliminado correctamente");
        response.setCorrecto(true);
        response.setDatos("Cliente eliminado");

        return ResponseEntity.ok(response);
    }

}
