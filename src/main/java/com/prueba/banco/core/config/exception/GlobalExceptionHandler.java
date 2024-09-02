package com.prueba.banco.core.config.exception;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prueba.banco.core.config.exception.dto.ResponseErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleException(Exception ex) {
        
        if (ex instanceof BadCredentialsException) {
            return buildResponseErrorDto(HttpStatus.UNAUTHORIZED, new RuntimeException("Credenciales incorrectas"),
            "Usuario o contraseña incorrectos");
        }

        if (ex instanceof AccessDeniedException) {
            return buildResponseErrorDto(HttpStatus.FORBIDDEN, new RuntimeException("Acceso denegado"),
                    "No tiene permisos para acceder a este recurso");
        }
        
        if (ex instanceof MethodArgumentNotValidException) {
            List<String> errores = new java.util.ArrayList<>();
            ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error -> {
                errores.add(error.getField() + ": " + error.getDefaultMessage());
            });
            return buildResponseErrorDto(HttpStatus.BAD_REQUEST, new RuntimeException("Se tiene errores de validación"),
                    errores);
        }

        return buildResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex, (String) null);
    }

    private ResponseEntity<ResponseErrorDto> buildResponseErrorDto(HttpStatus httpStatus, Exception exc,
            String error) {

        ResponseErrorDto responseErrorDto = new ResponseErrorDto();

        responseErrorDto.setCodigo(httpStatus.value());
        responseErrorDto.setCorrecto(false);
        responseErrorDto.setNotificacion(exc.getMessage());
        responseErrorDto.setErrores(
                error != null ? List.of(error) : null);

        return new ResponseEntity<>(responseErrorDto, httpStatus);
    }

    private ResponseEntity<ResponseErrorDto> buildResponseErrorDto(HttpStatus httpStatus, Exception exc,
            List<String> error) {

        ResponseErrorDto responseErrorDto = new ResponseErrorDto();

        responseErrorDto.setCodigo(httpStatus.value());
        responseErrorDto.setCorrecto(false);
        responseErrorDto.setNotificacion(exc.getMessage());
        responseErrorDto.setErrores(error);

        return new ResponseEntity<>(responseErrorDto, httpStatus);
    }
}
