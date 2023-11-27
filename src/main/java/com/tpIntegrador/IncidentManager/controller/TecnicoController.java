package com.tpIntegrador.IncidentManager.controller;

import com.tpIntegrador.IncidentManager.model.entity.TecnicoEntity;
import com.tpIntegrador.IncidentManager.repository.TecnicoRepository;
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
public class TecnicoController {

    private final Logger log = LoggerFactory.getLogger(TecnicoController.class);
    // Atributos
    private TecnicoRepository tecnicoRepository;
    // Construct
    public TecnicoController(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }


    // CRUD sobre la entidad Técnico.

    /**
     * Buscar todos los técnicos que hay en la base de datos (ArrayList de técnicos)
     * http://localhost:8080/api/tecnicos
     * @return
    */
    @GetMapping("/api/tecnicos") // el nombre de la entidad en plural y api porque es una api rest.
    @Operation(summary = "Consultar todos los técnicos", description = "Busca todos los técnicos de la base de datos.")
    public List<TecnicoEntity> findAll(){
        // Recuperar y devolver los técnicos de la base de datos.
        return tecnicoRepository.findAll();
    }

    /**
     * http://localhost:8080/api/tecnicos/1 ...etc
     * Request
     * Response
     * @param id
     * @return
     */

    // Buscar un solo técnico en la base de datos según su id.
    @GetMapping("/api/tecnicos/{id}") // {id} es un parametro variable que generara por ejemplo /api/tecnicos/2.
    @Operation(summary = "Buscar por id", description = "Busca un técnico por clave primaria id Long")
    public ResponseEntity<TecnicoEntity> findOneById(@Parameter(description = "Clave primaria tipo Long") @PathVariable Long id) {
        //@PathVariable vincula id con {id}

        Optional<TecnicoEntity> tecOpt = tecnicoRepository.findById(id); //
        // @PathVariable
        // envuelve el id y el null
        // Opción 1

        if(tecOpt.isPresent()) {
            // Con ResponseEntity obtenemos un 404 si no se encuentra el técnico.
            return ResponseEntity.ok(tecOpt.get()); // devuelve el técnico.
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo técnico en la base de datos

    /**
     * Crear un nuevo técnico en la base de datos.
     * Método POST, no colisiona con findAll, porque son diferentes métodos HTTP: GET vs. POST.
     */
    @PostMapping("/api/tecnicos") // Como son métodos diferentes no colisionan las url.
    @Operation(summary = "Crear un técnico", description = "Con este método podemos dar de alta a un técnico en la base" +
            " de datos.")
    public ResponseEntity<TecnicoEntity> create(@RequestBody TecnicoEntity tecnico, @RequestHeader HttpHeaders headers){
        // También podría devolver un void o lo que se necesite.
        System.out.println(headers.get("User-Agent")); // No siempre es necesario las cabeceras.
        // Guardar el técnico recibido por parámetro en la base de datos.
        if (tecnico.getIdTecnico() != null){ // Quiere decir que existe el id, y por lo tanto no es una creación.
            log.warn("trying to create a technical with id");
            System.out.println("trying to create a technical with id");
            return ResponseEntity.badRequest().build();
        }
        TecnicoEntity result = tecnicoRepository.save(tecnico);
        return ResponseEntity.ok(result); // El técnico devuelto tiene una clave primaria.
    }


    /**
     * Actualizar los datos de un técnico en la base de datos.
     */
    @PutMapping("/api/tecnicos")
    @Operation(summary = "Modificar datos de técnico", description = "Modificar los datos de un técnico en la base." +
            "de datos según clave primaria id Long")
    public ResponseEntity<TecnicoEntity> update(@RequestBody TecnicoEntity tecnico) {
        if(tecnico.getIdTecnico() == null){ // si no tiene id quiere decir que si es una creación.
            log.warn("Trying to update a non existent technical");
            return ResponseEntity.badRequest().build();
        }
        if(!tecnicoRepository.existsById(tecnico.getIdTecnico())) {
            log.warn("Trying to update a non existent technical");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización.
        TecnicoEntity result = tecnicoRepository.save(tecnico);
        return ResponseEntity.ok(result); // El técnico devuelto tiene una clave primaria.
    }


    // Borrar un técnico en la base de datos por id.
    @Operation(summary = "ATENCIÓN: eliminar un técnico", description = "Con este método podemos eliminar a un " +
            "técnico según su clave primaria id Long")
    @DeleteMapping("/api/tecnicos/{id}")
    public ResponseEntity<TecnicoEntity> delete(@PathVariable Long id) {
        if (!tecnicoRepository.existsById(id)) {
            log.warn("Trying to delete a non existent technical");
            return ResponseEntity.notFound().build();
        }
        tecnicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todos los técnicos de la base de datos.

    @Operation(summary = "ATENCIÓN: eliminar TODOS los técnicos", description = "Con este método eliminamos todos los " +
            "técnicos de la base de datos")
    @Hidden
    @DeleteMapping("/api/tecnicos")
    public ResponseEntity<TecnicoEntity> deleteAll() {
        log.info("REST Request for delete all technicals");
        tecnicoRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
