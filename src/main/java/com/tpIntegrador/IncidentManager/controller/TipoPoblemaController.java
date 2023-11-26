package com.tpIntegrador.IncidentManager.controller;


import com.tpIntegrador.IncidentManager.model.entity.TipoProblemaEntity;
import com.tpIntegrador.IncidentManager.repository.TipoProblemaRepository;
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
public class TipoPoblemaController {

    private final Logger log = LoggerFactory.getLogger(TipoPoblemaController.class);
    // Atributos
    private TipoProblemaRepository tipoProblemaRepository;
    // Construct
    public TipoPoblemaController(TipoProblemaRepository tipoProblemaRepository) {
        this.tipoProblemaRepository = tipoProblemaRepository;
    }


    // CRUD sobre la entidad TipoProblema.

    /**
     * Buscar todos los tipos de problemas que hay en la base de datos (ArrayList de tiposProblemas)
     * http://localhost:8080/api/tiposProblemas
    */
    @GetMapping("/api/tipoProblemas") // el nombre de la entidad en plural y api porque es una api rest.
    @Operation(summary = "Consultar todos los tipos de problemas", description = "Busca todos los tipos de problemas" +
            " de la base de datos.")
    public List<TipoProblemaEntity> findAll(){
        // Recuperar y devolver las tecnologías de la base de datos.
        return tipoProblemaRepository.findAll();
    }

    /**
     * http://localhost:8080/api/tipoProblema/1 ...etc
     * Request
     * Response
     */

    // Buscar un solo técnico en la base de datos según su id.
    @GetMapping("/api/tipoProblemas/{id}") // {id} es un parámetro variable que generara por ejemplo /api/tecnologia/2.
    @Operation(summary = "Buscar por id", description = "Busca un problema por clave primaria id Long")
    public ResponseEntity<TipoProblemaEntity> findOneById(@Parameter(description = "Clave primaria tipo Long") @PathVariable Long id) {
        //@PathVariable vincula id con {id}

        Optional<TipoProblemaEntity> problemaAppOpt = tipoProblemaRepository.findById(id); //
        // @PathVariable
        // envuelve el id y el null
        // Opción 1

        if(problemaAppOpt.isPresent()) {
            // Con ResponseEntity obtenemos un 404 si no se encuentra la tecnología.
            return ResponseEntity.ok(problemaAppOpt.get()); // devuelve la tecnología.
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva tecnología en la base de datos

    /**
     * Crear una nueva tecnología en la base de datos.
     * Método POST, no colisiona con findAll, porque son diferentes métodos HTTP: GET vs. POST.
     */
    @PostMapping("/api/tipoProblemas") // Como son métodos diferentes no colisionan las url.
    @Operation(summary = "Crear tipo de problema", description = "Con este método podemos dar de alta a un tipo de " +
            "problema nuevo base de datos.")
    public ResponseEntity<TipoProblemaEntity> create(@RequestBody TipoProblemaEntity tipoProblema,
                                                @RequestHeader HttpHeaders headers){
        // También podría devolver un void o lo que se necesite.
        System.out.println(headers.get("User-Agent")); // No siempre es necesario las cabeceras.
        // Guardar el tipo de problema recibido por parámetro en la base de datos.
        if (tipoProblema.getId() != null){ // Quiere decir que existe el id, y por lo tanto no es una creación.
            log.warn("Trying to create a problem type with ID.");
            System.out.println("Trying to create a problem type with ID.");
            return ResponseEntity.badRequest().build();
        }
        TipoProblemaEntity result = tipoProblemaRepository.save(tipoProblema);
        return ResponseEntity.ok(result); // El tipo de problema devuelto tiene una clave primaria.
    }


    /**
     * Actualizar los datos de una tecnología en la base de datos.
     */
    @PutMapping("/api/tipoProblemas")
    @Operation(summary = "Modificar datos de un tipo de problema", description = "Modificar los datos de un tipo de " +
            "problema en la base de datos según clave primaria id Long")
    public ResponseEntity<TipoProblemaEntity> update(@RequestBody TipoProblemaEntity tipoProblema) {
        if(tipoProblema.getId() == null){ // si no tiene id quiere decir que si es una creación.
            log.warn("Trying to update a non existent problem type.");
            return ResponseEntity.badRequest().build();
        }
        if(!tipoProblemaRepository.existsById(tipoProblema.getId())) {
            log.warn("Trying to update a non existent problem type.");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización.
        TipoProblemaEntity result = tipoProblemaRepository.save(tipoProblema);
        return ResponseEntity.ok(result); // El tipo de problema devuelto tiene una clave primaria.
    }

    // Borrar una tecnología de la base de datos por id.
    @Operation(summary = "ATENCIÓN: eliminar un tipo de problema", description = "Con este método podemos eliminar a un " +
            "tipo de problema según su clave primaria id Long")
    @DeleteMapping("/api/tipoProblemas/{id}")
    public ResponseEntity<TipoProblemaEntity> delete(@PathVariable Long id) {
        if (!tipoProblemaRepository.existsById(id)) {
            log.warn("Trying to delete a non existent problem type.");
            return ResponseEntity.notFound().build();
        }
        tipoProblemaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todas las tecnologías de la base de datos.

    @Operation(summary = "ATENCIÓN: eliminar TODOS las tecnologías", description = "Con este método eliminamos todos los " +
            "técnicos de la base de datos")
    @Hidden
    @DeleteMapping("/api/tipoProblemas")
    public ResponseEntity<TipoProblemaEntity> deleteAll() {
        log.info("REST Request for delete all problem type");
        tipoProblemaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
