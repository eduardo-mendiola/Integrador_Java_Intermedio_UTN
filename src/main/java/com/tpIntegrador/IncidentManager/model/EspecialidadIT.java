package com.tpIntegrador.IncidentManager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "especialidadIT") // Para cambiar el monbre en la base de datos
@Schema(title = "ESPECIALIDAD_IT", description = "Tipo de especialidad que reúne una o mas tecnologías")
public class EspecialidadIT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany
    private Collection<Tecnologia> tecnologias;

    @ManyToMany(mappedBy = "especialidadIts",cascade = CascadeType.ALL)
    //@JoinColumn(name = "tecnico_id")
    private Collection<Tecnico> tecnicos;
}


