package com.prueba.banco.core.config.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRest<T> {
    private boolean correcto;
    private String notificacion;
    private int codigo;
    private T datos;

}
