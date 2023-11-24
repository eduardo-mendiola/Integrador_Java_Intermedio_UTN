package com.tpIntegrador.IncidentManager.model;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "contratoServ") // Para cambiar el monbre en la base de datos
@Schema(title = "CONTRATO_DE_SERVICIO", description = "Un contrato de servicio consta de una o varias tecnologías que " +
        "recibirán mantenimiento y accesoria técnica.")
public class ContratoServ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "contratoServ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tecnologia> tecnologia;


}
