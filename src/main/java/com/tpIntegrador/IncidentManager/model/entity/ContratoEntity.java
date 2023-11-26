package com.tpIntegrador.IncidentManager.model.entity;

import java.util.Collection;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data //@Data: Anotación que combina las funcionalidades de @ToString, @EqualsAndHashCode, @Getter, y @Setter.

@Entity
@Table(name = "contrato") // Para cambiar el nombre en la base de datos
@Schema(title = "CONTRATO_DE_SERVICIO", description = "Un contrato de servicio consta de una o varias tecnologías que " +
        "recibirán mantenimiento y accesoria técnica.")
public class ContratoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Clave ficticia auto-incremental")
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
        name = "Contrato_tecnologia",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "contrato_id"))
    @Hidden // Anotación para ignorar en Swagger
    private Collection<TecnologiaEntity> tecnologias;

    @ManyToMany(mappedBy = "contratos", cascade = CascadeType.ALL)
    @Hidden // Anotación para ignorar en Swagger
    private Collection<ClienteEntity> clientes;


}
