package com.tpIntegrador.IncidentManager.model.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;


// Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data //@Data: Anotación que combina las funcionalidades de @ToString, @EqualsAndHashCode, @Getter, y @Setter.


@Entity
@Table(name = "cliente") // Para cambiar el monbre en la base de datos
@Schema(title = "CLIENTE", description = "Cliente que tiene un contrato con la empresa y reporta un incidente.")
public class ClienteEntity {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Clave ficticia auto-incremental")
    private Long idCliente;

    @Column(name = "razonSocial")
    private String razonSocialCliente;
    @Column(name = "nombre")
    private String nombreCliente;
    @Column(name = "apellido")
    private String apellidoCliente;
    @Column(name = "dniCuit")
    private String dniCuitCliente;
    @Column(name = "email")
    private String emailCliente;
    @Column(name = "telefono")
    private String telefonoCliente;

    @Schema(description = "Elegir una o más contratos.")
    @ManyToMany
    @JoinTable(
        name = "Cliente_Contrato",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "contrato_id"))
    @Hidden // Anotación para ignorar en Swagger
    private Collection<ContratoEntity> contratos;

}