package com.tpIntegrador.IncidentManager.model;

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
public class Tecnico {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Clave ficticia auto-incremental")
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String telefono;
    private MedioNoti medioNoti;

    @Schema(description = "Elegir una o más especialidades.")
    @ManyToMany
    private Collection<EspecialidadIT> especialidadIts;

}
