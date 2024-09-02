package com.prueba.banco.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private int pagina;
    private int totalPaginas;
    private long totalElementos;
    private int cantidadElementos;
    private List<T> contenido;

}
