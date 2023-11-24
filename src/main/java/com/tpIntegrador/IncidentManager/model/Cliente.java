package com.tpIntegrador.IncidentManager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

// Lombok
@NoArgsConstructor
@AllArgsConstructor
//@Data //@Data: Anotación que combina las funcionalidades de @ToString, @EqualsAndHashCode, @Getter, y @Setter.
@Getter
@Setter


@Entity
@Table(name = "cliente") // Para cambiar el monbre en la base de datos
@Schema(title = "CLIENTE", description = "Cliente que tiene un contrato con la empresa y reporta un incidente.")
public class Cliente {

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
    @Schema(description = "Elegir una o más contratos.")
    private ArrayList<ContratoServ> contratos;
}