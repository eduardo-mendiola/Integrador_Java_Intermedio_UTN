package com.tpIntegrador.IncidentManager.model.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "especialidad_tecnologia")
@Schema(title = "ESPECIALIDADyTECNOLOGIA", description = "Tabla intermedia que vincula una espacialidad con diferentes " +
        "tecnologías")
public class EspecialidadTecnologiaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Clave ficticia auto-incremental")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private EspecialidadEntity especialidad;

    @ManyToOne
    @JoinColumn(name = "tecnologia_id")
    private TecnologiaEntity tecnologia;

}

