package com.tpIntegrador.IncidentManager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.CollationElementIterator;
import java.util.Collection;

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

    @ManyToMany(mappedBy = "tecnologias", cascade = CascadeType.ALL)
    //@JoinColumn(name = "contratoServ_id")
    private Collection<ContratoServ> contratoServ;

    @ManyToMany(mappedBy = "tecnologias", cascade = CascadeType.ALL)
    //@JoinColumn(name = "especialidadIT_id")
    private Collection<EspecialidadIT> especialidadIT;
}



