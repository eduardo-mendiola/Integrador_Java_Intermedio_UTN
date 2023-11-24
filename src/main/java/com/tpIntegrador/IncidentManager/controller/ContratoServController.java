package com.tpIntegrador.IncidentManager.controller;



import com.tpIntegrador.IncidentManager.model.ContratoServ;
import com.tpIntegrador.IncidentManager.repository.ContratoServRepository;
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
public class ContratoServController {

    private final Logger log = LoggerFactory.getLogger(ContratoServController.class);
    // Atributos
    private ContratoServRepository contratoServRepository;
    // Construct
    public ContratoServController(ContratoServRepository contratoServRepositoryy) {
        this.contratoServRepository = contratoServRepository;
    }


    // CRUD sobre la entidad Tecnología.

    /**
     * Buscar todos las tecnologias que hay en la base de datos (ArrayList de tecnologias)
     * http://localhost:8080/api/tecnologias
     * @return
    */
    @GetMapping("/api/contratoServs") // el nombre de la entidad en plural y api porque es una api rest.
    public List<ContratoServ> findAll(){
        // Recuperar y devolver las tecnologías de la base de datos.
        return contratoServRepository.findAll();
    }

    /**
     * http://localhost:8080/api/tecnologia/1 ...etc
     * Request
     * Response
     * @param id
     * @return
     */

    // Buscar un solo técnico en la base de datos según su id.
    @GetMapping("/api/contratoServs/{id}") // {id} es un parámetro variable que generara por ejemplo /api/tecnologia/2.
    @Operation(summary = "Buscar por id", description = "Busca una tecnología por clave primaria id Long")
    public ResponseEntity<ContratoServ> findOneById(@Parameter(description = "Clave primaria tipo Long") @PathVariable Long id) {
        //@PathVariable vincula id con {id}

        Optional<ContratoServ> contOpt = contratoServRepository.findById(id); //
        // @PathVariable
        // envuelve el id y el null
        // Opción 1

        if(contOpt.isPresent()) {
            // Con ResponseEntity obtenemos un 404 si no se encuentra la tecnología.
            return ResponseEntity.ok(contOpt.get()); // devuelve la tecnología.
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva tecnología en la base de datos

    /**
     * Crear una nueva tecnología en la base de datos.
     * Método POST, no colisiona con findAll, porque son diferentes métodos HTTP: GET vs. POST.
     * @param tecnologia
     * @param headers
     * @return
     */
    @PostMapping("/api/contratoServs") // Como son métodos diferentes no colisionan las url.
    @Operation(summary = "Crear una tecnología", description = "Con este método podemos dar de alta a un técnico en la " +
            "base de datos.")
    public ResponseEntity<ContratoServ> create(@RequestBody ContratoServ contratoServ, @RequestHeader HttpHeaders headers){
        // También podría devolver un void o lo que se necesite.
        System.out.println(headers.get("User-Agent")); // No siempre es necesario las cabeceras.
        // Guardar el técnico recibido por parámetro en la base de datos.
        if (contratoServ.getId() != null){ // Quiere decir que existe el id, y por lo tanto no es una creación.
            log.warn("trying to create a technical with id");
            System.out.println("trying to create a technical with id");
            return ResponseEntity.badRequest().build();
        }
        ContratoServ result = contratoServRepository.save(contratoServ);
        return ResponseEntity.ok(result); // La tecnología devuelta tiene una clave primaria.
    }


    /**
     * Actualizar los datos de una tecnología en la base de datos.
     */
    @PutMapping("/api/contratoServs")
    @Operation(summary = "Modificar datos de una tecnología", description = "Modificar los datos de una tecnología en la " +
            "base de datos según clave primaria id Long")
    public ResponseEntity<ContratoServ> update(@RequestBody ContratoServ contratoServ) {
        if(contratoServ.getId() == null){ // si no tiene id quiere decir que si es una creación.
            log.warn("Trying to update a nonexistent technology.");
            return ResponseEntity.badRequest().build();
        }
        if(!contratoServRepository.existsById(contratoServ.getId())) {
            log.warn("Trying to update a nonexistent technology.");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización.
        ContratoServ result = contratoServRepository.save(contratoServ);
        return ResponseEntity.ok(result); // La tecnología devuelta tiene una clave primaria.
    }

    // Borrar una tecnología de la base de datos por id.
    @Operation(summary = "ATENCIÓN: eliminar una tecnología", description = "Con este método podemos eliminar a una " +
            "técnologia según su clave primaria id Long")
    @DeleteMapping("/api/contratoServs/{id}")
    public ResponseEntity<ContratoServ> delete(@PathVariable Long id) {
        if (!contratoServRepository.existsById(id)) {
            log.warn("Trying to delete a non existent technology.");
            return ResponseEntity.notFound().build();
        }
        contratoServRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todos las tecnologías de la base de datos.

    @Operation(summary = "ATENCIÓN: eliminar TODOS las tecnologías", description = "Con este método eliminamos todos los " +
            "técnicos de la base de datos")
    @Hidden
    @DeleteMapping("/api/contratoServs")
    public ResponseEntity<ContratoServ> deleteAll() {
        log.info("REST Request for delete all technicals");
        contratoServRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
