package com.tpIntegrador.IncidentManager.model.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data //@Data: Anotación que combina las funcionalidades de @ToString, @EqualsAndHashCode, @Getter, y @Setter.

@Entity
@Table(name = "tecnologia") // Para cambiar el monbre en la base de datos
@Schema(title = "TECNOLOGÍA_APP", description = "Tecnología, APP o software que forman parte de los servicios contratados " +
        "y de las especialidades de los técnicos.")
public class TecnologiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "tecnologias", cascade = CascadeType.ALL)
    @Hidden // Anotación para ignorar en Swagger
    private List<EspecialidadEntity> especialidades;

    @ManyToMany(mappedBy = "tecnologias", cascade = CascadeType.ALL)
    @Hidden // Anotación para ignorar en Swagger
    private List<ContratoEntity> contrato;
}



