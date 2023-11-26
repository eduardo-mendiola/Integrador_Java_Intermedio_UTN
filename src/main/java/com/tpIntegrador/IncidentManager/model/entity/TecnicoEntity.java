package com.tpIntegrador.IncidentManager.model.entity;

import com.tpIntegrador.IncidentManager.model.enums.MedioNotiEnum;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

// Lombok
@NoArgsConstructor
@AllArgsConstructor
//@Data //@Data: Anotación que combina las funcionalidades de @ToString, @EqualsAndHashCode, @Getter, y @Setter.
@Getter
@Setter


@Entity
@Table(name = "tecnico") // Para cambiar el monbre en la base de datos
@Schema(title = "TÉCNICO", description = "Técnico especializado que recibirá el informe de incidente para dar resolución")
public class TecnicoEntity {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Clave ficticia auto-incremental")
    private Long idTecnico;

    @Column(name = "nombre")
    private String nombreTecnico;
    @Column(name = "apellido")
    private String apellidoTecnico;
    @Column(name = "dni")
    private String dniTecnico;
    @Column(name = "email")
    private String emailTecnico;
    @Column(name = "telefono")
    private String telefonoTecnico;
    @Column(name = "medioNoti")
    private MedioNotiEnum medioNotiTecnico;

    @Schema(description = "Elegir una o más especialidades.")
    @ManyToMany
    @JoinTable(
        name = "tecnico_especialidad",
        joinColumns = @JoinColumn(name = "tecnico_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    @Hidden
    private Collection<EspecialidadEntity> especialidades;

}