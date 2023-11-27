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
@Table(name = "especialidad") // Para cambiar el monbre en la base de datos
@Schema(title = "ESPECIALIDAD", description = "Tipo de especialidad que reúne una o mas tecnologías")
public class EspecialidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Clave ficticia auto-incremental")
    private Long id;

    @Column(name = "nombre")
    private String nombre;


    @ManyToMany
    @JoinTable(
        name = "Especialidad_tecnologia",
        joinColumns = @JoinColumn(name = "especialidad_id"),
        inverseJoinColumns = @JoinColumn(name = "tecnologia_id"))
    //@Hidden // Anotación para ignorar en Swagger
    private List<TecnologiaEntity> tecnologias;

    @ManyToMany(mappedBy = "especialidades",cascade = CascadeType.ALL)
    //@Hidden // Anotación para ignorar en Swagger
    private List<TecnicoEntity> tecnicos;

    @ManyToMany(mappedBy = "especialidades",cascade = CascadeType.ALL)
    //@Hidden // Anotación para ignorar en Swagger
    private List<TipoProblemaEntity> tiposProblemas;

}


