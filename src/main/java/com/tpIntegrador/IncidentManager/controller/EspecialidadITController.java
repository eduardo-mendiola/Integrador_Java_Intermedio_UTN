package com.tpIntegrador.IncidentManager.controller;


import com.tpIntegrador.IncidentManager.model.EspecialidadIT;
import com.tpIntegrador.IncidentManager.repository.EspecialidadITRepository;
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
public class EspecialidadITController {

    private final Logger log = LoggerFactory.getLogger(EspecialidadITController.class);

    // Atributos
    private EspecialidadITRepository especialidadITRepository;
    // Construct

    public EspecialidadITController(EspecialidadITRepository especialidadITRepository) {
        this.especialidadITRepository = especialidadITRepository;
    }


    // CRUD sobre la entidad Cliente

    /**
     * Buscar todos los técnicos que hay en la base de datos (ArrayList de clientes)
     * http://localhost:8080/api/clientes
     * @return
    */
    @GetMapping("/api/especialidadITs") // el nombre de la entidad en plural y api porque es una api rest.
    public List<EspecialidadIT> findAll(){
        // Recuperar y devolver los clientes de la base de datos.
        return especialidadITRepository.findAll();
    }

    /**
     * http://localhost:8080/api/clientes/1 ...etc
     * Request
     * Response
     * @param id
     * @return
     */

    // Buscar un solo cliente en la base de datos según su id.
    @GetMapping("/api/especialidadITs/{id}") // {id} es un parámetro variable que generara por ejemplo /api/clientes/2.
    @Operation(summary = "Buscar por id", description = "Busca un cliente por clave primaria id Long")
    public ResponseEntity<EspecialidadIT> findOneById(@Parameter(description = "Clave primaria tipo Long") @PathVariable Long id) {
        //@PathVariable vincula id con {id}

        Optional<EspecialidadIT>espOpt = especialidadITRepository.findById(id); //
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
     * @param cliente
     * @param headers
     * @return
     */
    @PostMapping("/api/especialidadITs") // Como son métodos diferentes no colisionan las url.
    @Operation(summary = "Crear un cliente", description = "Con este método podemos dar de alta a un cliente en la base" +
            " de datos.")
    public ResponseEntity<EspecialidadIT> create(@RequestBody EspecialidadIT especialidadIT,
                                                 @RequestHeader HttpHeaders headers){
        // También podría devolver un void o lo que se necesite
        System.out.println(headers.get("User-Agent")); // No siempre es necesario las cabeceras.
        // Guardar el cliente recibido por parámetro en la base de datos.
        if (especialidadIT.getId() != null){ // Quiere decir que existe el id, y por lo tanto no es una creación.
            log.warn("trying to create a customer with id");
            System.out.println("trying to create a customer with id");
            return ResponseEntity.badRequest().build();
        }
        EspecialidadIT result = especialidadITRepository.save(especialidadIT);
        return ResponseEntity.ok(result); // El cliente devuelto tiene una clave primaria.
    }


    /**
     * Actualizar los datos de un cliente en la base de datos.
     */
    @PutMapping("/api/especialidadITs")
    @Operation(summary = "Modificar datos de cliente", description = "Modificar los datos de un cliente en la base." +
            "de datos según clave primaria id Long")
    public ResponseEntity<EspecialidadIT> update(@RequestBody EspecialidadIT especialidadIT) {
        if(especialidadIT.getId() == null){ // si no tiene id quiere decir que si es una creación.
            log.warn("Trying to update a non existent customer");
            return ResponseEntity.badRequest().build();
        }
        if(!especialidadITRepository.existsById(especialidadIT.getId())) {
            log.warn("Trying to update a non existent customer");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización.
        EspecialidadIT result = especialidadITRepository.save(especialidadIT);
        return ResponseEntity.ok(result); // El cliente devuelto tiene una clave primaria.
    }

    // Borrar un cliente de la base de datos por id.
    @Operation(summary = "ATENCIÓN: eliminar un cliente", description = "Con este método podemos eliminar a un " +
            "cliente según su clave primaria id Long")
    @DeleteMapping("/api/especialidadITs/{id}")
    public ResponseEntity<EspecialidadIT> delete(@PathVariable Long id) {
        if (!especialidadITRepository.existsById(id)) {
            log.warn("Trying to delete a non existent customer");
            return ResponseEntity.notFound().build();
        }
        especialidadITRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todos los cliente de la base de datos

    @Operation(summary = "ATENCIÓN: eliminar TODOS los clientes", description = "Con este método eliminamos todos los " +
            "clientes de la base de datos")
    @Hidden
    @DeleteMapping("/api/especialidadITs")
    public ResponseEntity<EspecialidadIT> deleteAll() {
        log.info("REST Request for delete all customers");
        especialidadITRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
