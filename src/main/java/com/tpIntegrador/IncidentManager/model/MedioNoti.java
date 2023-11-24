package com.tpIntegrador.IncidentManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MedioNoti {
    EMAIL("email"),
    WHATSAPP("mensaje de texto de Whatsapp"),
    TEL("llama telefonica para aviso"),
    SISTEMA("aviso por sistema");

    private final String descripcion; // Se puede acceder al valor de la descripción con .getDescripcion()
}
