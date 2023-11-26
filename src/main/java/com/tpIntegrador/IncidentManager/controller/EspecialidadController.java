package com.tpIntegrador.IncidentManager.controller;


import com.tpIntegrador.IncidentManager.model.entity.EspecialidadEntity;
import com.tpIntegrador.IncidentManager.repository.EspecialidadRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EspecialidadController {

    private final Logger log = LoggerFactory.getLogger(EspecialidadController.class);

    // Atributos
    private EspecialidadRepository especialidadRepository;
    // Construct

    public EspecialidadController(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }


    // CRUD sobre la entidad Cliente

    /**
     * Buscar todos los técnicos que hay en la base de datos (ArrayList de clientes)
     * http://localhost:8080/api/clientes
     * @return
    */
    @GetMapping("/api/especialidades") // el nombre de la entidad en plural y api porque es una api rest.
    @Operation(summary = "Consultar todas las especialidades", description = "Busca todas las especialidades de la base de" +
            " datos.")
    public List<EspecialidadEntity> findAll(){
        // Recuperar y devolver los clientes de la base de datos.
        return especialidadRepository.findAll();
    }

    /**
     * http://localhost:8080/api/clientes/1 ...etc
     * Request
     * Response
     * @param id
     * @return
     */

    // Buscar un solo cliente en la base de datos según su id.
    @GetMapping("/api/especialidades/{id}") // {id} es un parámetro variable que generara por ejemplo /api/clientes/2.
    @Operation(summary = "Buscar por id", description = "Busca un cliente por clave primaria id Long")
    public ResponseEntity<EspecialidadEntity> findOneById(@Parameter(description = "Clave primaria tipo Long") @PathVariable Long id) {
        //@PathVariable vincula id con {id}

        Optional<EspecialidadEntity>espOpt = especialidadRepository.findById(id); //
        // @PathVariable
        // envuelve el id y el null
        // Opción 1

        if(espOpt.isPresent()) {
            // Con ResponseEntity obtenemos un 404 si no se encuentra al cliente.
            return ResponseEntity.ok(espOpt.get()); // devuelve el cliente.
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo cliente en la base de datos.

    /**
     * Crear un nuevo cliente en la base de datos.
     * Método POST, no colisiona con findAll, porque son diferentes métodos HTTP: GET vs. POST.
     */
    @PostMapping("/api/especialidades") // Como son métodos diferentes no colisionan las url.
    @Operation(summary = "Crear un cliente", description = "Con este método podemos dar de alta a un cliente en la base" +
            " de datos.")
    public ResponseEntity<EspecialidadEntity> create(@RequestBody EspecialidadEntity especialidad,
                                                     @RequestHeader HttpHeaders headers){
        // También podría devolver un void o lo que se necesite
        System.out.println(headers.get("User-Agent")); // No siempre es necesario las cabeceras.
        // Guardar el cliente recibido por parámetro en la base de datos.
        if (especialidad.getId() != null){ // Quiere decir que existe el id, y por lo tanto no es una creación.
            log.warn("trying to create a customer with id");
            System.out.println("trying to create a customer with id");
            return ResponseEntity.badRequest().build();
        }
        EspecialidadEntity result = especialidadRepository.save(especialidad);
        return ResponseEntity.ok(result); // El cliente devuelto tiene una clave primaria.
    }


    /**
     * Actualizar los datos de un cliente en la base de datos.
     */
    @PutMapping("/api/especialidades")
    @Operation(summary = "Modificar datos de cliente", description = "Modificar los datos de un cliente en la base." +
            "de datos según clave primaria id Long")
    public ResponseEntity<EspecialidadEntity> update(@RequestBody EspecialidadEntity especialidad) {
        if(especialidad.getId() == null){ // si no tiene id quiere decir que si es una creación.
            log.warn("Trying to update a non existent customer");
            return ResponseEntity.badRequest().build();
        }
        if(!especialidadRepository.existsById(especialidad.getId())) {
            log.warn("Trying to update a non existent customer");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización.
        EspecialidadEntity result = especialidadRepository.save(especialidad);
        return ResponseEntity.ok(result); // El cliente devuelto tiene una clave primaria.
    }

    // Borrar un cliente de la base de datos por id.
    @Operation(summary = "ATENCIÓN: eliminar un cliente", description = "Con este método podemos eliminar a un " +
            "cliente según su clave primaria id Long")
    @DeleteMapping("/api/especialidadITs/{id}")
    public ResponseEntity<EspecialidadEntity> delete(@PathVariable Long id) {
        if (!especialidadRepository.existsById(id)) {
            log.warn("Trying to delete a non existent customer");
            return ResponseEntity.notFound().build();
        }
        especialidadRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todas las especialidades de la base de datos

    @Operation(summary = "ATENCIÓN: eliminar TODOS los clientes", description = "Con este método eliminamos todos los " +
            "clientes de la base de datos")
    @Hidden
    @DeleteMapping("/api/especialidades")
    public ResponseEntity<EspecialidadEntity> deleteAll() {
        log.info("REST Request for delete all customers");
        especialidadRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
