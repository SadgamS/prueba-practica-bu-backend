package com.prueba.banco.application.cliente.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.banco.application.cliente.dto.CrearClienteDto;
import com.prueba.banco.application.cliente.dto.RespuestaClienteDto;
import com.prueba.banco.application.cliente.service.ClienteService;
import com.prueba.banco.common.PageResponse;
import com.prueba.banco.core.config.response.ResponseRest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ResponseRest<PageResponse<RespuestaClienteDto>>> obtenerClientes(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "id", required = false) String sort) {

        ResponseRest<PageResponse<RespuestaClienteDto>> response = new ResponseRest<>();

        var clientes = clienteService.obtenerClientes(page, size, sort);

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

    @PostMapping
    public ResponseEntity<ResponseRest<String>> crearClienteString(
            @Valid @RequestBody CrearClienteDto cliente) {

        Long id = clienteService.crearCliente(cliente);

        ResponseRest<String> response = new ResponseRest<>();

        response.setCodigo(200);
        response.setNotificacion("Cliente creado correctamente");
        response.setCorrecto(true);
        response.setDatos("Cliente creado con id: " + id);

        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseRest<String>> editarCliente(
            @Valid @RequestBody CrearClienteDto cliente, @PathVariable("id") Long id) {

        Long idCliente = clienteService.editarCliente(cliente, id);

        ResponseRest<String> response = new ResponseRest<>();

        response.setCodigo(200);
        response.setNotificacion("Cliente editado correctamente");
        response.setCorrecto(true);
        response.setDatos("Cliente editado con id: " + idCliente);

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
