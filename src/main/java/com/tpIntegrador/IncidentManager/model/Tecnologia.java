package com.tpIntegrador.IncidentManager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tecnologia") // Para cambiar el monbre en la base de datos
@Schema(title = "TECNOLOGÍA_APP", description = "Tecnología, APP o software que forman parte de los servicios contratados " +
        "y de las especialidades de los técnicos.")
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "contratoServ_id")
    private ContratoServ contratoServ;

    @ManyToOne
    @JoinColumn(name = "especialidadIT_id")
    private EspecialidadIT especialidadIT;
}



