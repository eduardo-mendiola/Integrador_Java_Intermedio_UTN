package com.tpIntegrador.IncidentManager.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DificultadEmun {
    FACIL("Problemas básicos, relacionados con el uso"),
    MEDIO("Problemas relacionados con instalación y ejecución de apps."),
    DIFICIL("Problemas relacionados con bloqueos, caida de sistema, redes."),
    EXPERTO("Problemas relacionados con seguridad, ataques y vulnerabilidades.");

    private final String descripcion; // Se puede acceder al valor de la descripción con .getDescripcion()
}
