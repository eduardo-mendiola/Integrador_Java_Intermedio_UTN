package com.tpIntegrador.IncidentManager.model.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data //@Data: Anotación que combina las funcionalidades de @ToString, @EqualsAndHashCode, @Getter, y @Setter.


@Entity
@Table(name = "TipoProblema")
@Schema(title = "TIPO DE PROBLEMA", description = "Contiene la información necesaria para establecer determinados tipos de" +
        " problemas")
public class TipoProblemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Clave ficticia auto-incremental")
    private Long id;

    private String titulo;
    private String descripcion;
    private double tiempoEstimadoResolucion;

    @ManyToMany
    @JoinTable(
        name = "Problema_Especialidad",
        joinColumns = @JoinColumn(name = "tipoProblema_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    @Hidden // Anotación para ignorar en Swagger
    private Collection<EspecialidadEntity> especialidades;



}
