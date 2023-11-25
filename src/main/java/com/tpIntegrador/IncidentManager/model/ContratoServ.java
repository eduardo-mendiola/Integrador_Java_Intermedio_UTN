package com.tpIntegrador.IncidentManager.model;

import java.util.Collection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "contratoServ") // Para cambiar el nombre en la base de datos
@Schema(title = "CONTRATO_DE_SERVICIO", description = "Un contrato de servicio consta de una o varias tecnologías que " +
        "recibirán mantenimiento y accesoria técnica.")
public class ContratoServ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToMany
    private Collection<Tecnologia> tecnologias;

    @ManyToMany(mappedBy = "contratos", cascade = CascadeType.ALL)
    //@JoinColumn(name = "cliente_id")
    private Collection<Cliente> clientes;


}
