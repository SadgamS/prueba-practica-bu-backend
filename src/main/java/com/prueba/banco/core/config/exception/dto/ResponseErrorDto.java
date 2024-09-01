package com.prueba.banco.core.config.exception.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseErrorDto {

    private boolean correcto;

    private String notificacion;

    private int codigo;

    private List<String> errores;

}
