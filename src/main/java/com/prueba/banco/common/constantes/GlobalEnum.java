package com.prueba.banco.common.constantes;

public enum GlobalEnum {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    CREACION("CREACION"),
    ACTUALIZACION("ACTUALIZACION");

    private final String value;

    GlobalEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
