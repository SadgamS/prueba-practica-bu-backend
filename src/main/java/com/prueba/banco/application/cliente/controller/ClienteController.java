package com.prueba.banco.application.cliente.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.banco.application.cliente.dto.RespuestaClienteDto;
import com.prueba.banco.application.cliente.service.ClienteService;
import com.prueba.banco.common.PageResponse;
import com.prueba.banco.core.config.response.ResponseRest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ResponseRest<PageResponse<RespuestaClienteDto>>> obtenerClientes(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        ResponseRest<PageResponse<RespuestaClienteDto>> response = new ResponseRest<>();

        var clientes = clienteService.obtenerClientes(page, size);

        response.setCodigo(200);
        response.setNotificacion("Datos obtenidos correctamente");
        response.setCorrecto(true);
        response.setDatos(clientes);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRest<RespuestaClienteDto>> getMethodName(
            @PathVariable("id") Long id) {
        ResponseRest<RespuestaClienteDto> response = new ResponseRest<>();

        var cliente = clienteService.obtenerClientePorId(id);

        response.setCodigo(200);
        response.setNotificacion("Datos obtenidos correctamente");
        response.setCorrecto(true);
        response.setDatos(cliente);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<ResponseRest<String>> eliminarCliente(
            @PathVariable("id") Long id) {
        ResponseRest<String> response = new ResponseRest<>();

        clienteService.eliminarCliente(id);

        response.setCodigo(200);
        response.setNotificacion("Cliente eliminado correctamente");
        response.setCorrecto(true);
        response.setDatos("Cliente eliminado");

        return ResponseEntity.ok(response);
    }

}
