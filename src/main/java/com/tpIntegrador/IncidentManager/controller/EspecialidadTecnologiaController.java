package com.tpIntegrador.IncidentManager.controller;


import com.tpIntegrador.IncidentManager.model.entity.EspecialidadTecnologiaEntity;
import com.tpIntegrador.IncidentManager.model.entity.EspecialidadTecnologiaIdEntity;
import com.tpIntegrador.IncidentManager.repository.EspecialidadTecnologiaRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EspecialidadTecnologiaController {

    private final Logger log = LoggerFactory.getLogger(EspecialidadTecnologiaController.class);
    // Atributos
    private EspecialidadTecnologiaRepository especialidadTecnologiaRepository;
    // Construct
    public EspecialidadTecnologiaController(EspecialidadTecnologiaRepository especialidadTecnologiaRepository) {
        this.especialidadTecnologiaRepository = especialidadTecnologiaRepository;
    }


    // CRUD sobre la entidad Técnico.

    @GetMapping("/api/especialidadTecnologias") // el nombre de la entidad en plural y api porque es una api rest.
    @Operation(summary = "Consultar todos los técnicos", description = "Busca todos los técnicos de la base de datos.")
    public List<EspecialidadTecnologiaIdEntity> findAll(){
        // Recuperar y devolver los técnicos de la base de datos.
        return especialidadTecnologiaRepository.findAll();
    }

    // Buscar un solo técnico en la base de datos según su id.
    @GetMapping("/api/especialidadTecnologias/{id}") // {id} es un parametro variable que generara por ejemplo
    // /api/tecnicos/2.
    @Operation(summary = "Buscar por id", description = "Busca un técnico por clave primaria id Long")
    public ResponseEntity<EspecialidadTecnologiaIdEntity> findOneById(@Parameter(description = "Clave primaria tipo Long") @PathVariable Long id) {
        //@PathVariable vincula id con {id}

        Optional<EspecialidadTecnologiaIdEntity> esptecOpt = especialidadTecnologiaRepository.findById(id); //


        if(esptecOpt.isPresent()) {
            // Con ResponseEntity obtenemos un 404 si no se encuentra el técnico.
            return ResponseEntity.ok(esptecOpt.get()); // devuelve el técnico.
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo técnico en la base de datos

    @PostMapping("/api/especialidadTecnologias") // Como son métodos diferentes no colisionan las url.
    @Operation(summary = "Crear un técnico", description = "Con este método podemos dar de alta a un técnico en la base" +
            " de datos.")
    public ResponseEntity<EspecialidadTecnologiaIdEntity> create(@RequestBody EspecialidadTecnologiaIdEntity especialidadTecnologiaId,
                                                                @RequestHeader HttpHeaders headers){
        // También podría devolver un void o lo que se necesite.
        System.out.println(headers.get("User-Agent")); // No siempre es necesario las cabeceras.
        // Guardar el técnico recibido por parámetro en la base de datos.
        if (especialidadTecnologiaId.getEspecialidadId() != null){ // Quiere decir que existe el id, y por lo tanto no es una creación.
            log.warn("trying to create a technical with id");
            System.out.println("trying to create a technical with id");
            return ResponseEntity.badRequest().build();
        }
        EspecialidadTecnologiaIdEntity result = especialidadTecnologiaRepository.save(especialidadTecnologiaId);
        return ResponseEntity.ok(result); // El técnico devuelto tiene una clave primaria.
    }


    /**
     * Actualizar los datos de un técnico en la base de datos.
     */
    @PutMapping("/api/especialidadTecnologias")
    @Operation(summary = "Modificar datos de técnico", description = "Modificar los datos de un técnico en la base." +
            "de datos según clave primaria id Long")
    public ResponseEntity<EspecialidadTecnologiaIdEntity> update(@RequestBody EspecialidadTecnologiaIdEntity especialidadTecnologia) {
        if(especialidadTecnologia.getEspecialidadId() == null){ // si no tiene id quiere decir que si es una creación.
            log.warn("Trying to update a non existent technical");
            return ResponseEntity.badRequest().build();
        }
        if(!especialidadTecnologiaRepository.existsById(especialidadTecnologia.getEspecialidadId())) {
            log.warn("Trying to update a non existent technical");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización.
        EspecialidadTecnologiaIdEntity result = especialidadTecnologiaRepository.save(especialidadTecnologia);
        return ResponseEntity.ok(result); // El técnico devuelto tiene una clave primaria.
    }


    // Borrar un técnico en la base de datos por id.
    @Operation(summary = "ATENCIÓN: eliminar un técnico", description = "Con este método podemos eliminar a un " +
            "técnico según su clave primaria id Long")
    @DeleteMapping("/api/especialidadTecnologias/{id}")
    public ResponseEntity<EspecialidadTecnologiaIdEntity> delete(@PathVariable Long id) {
        if (!especialidadTecnologiaRepository.existsById(id)) {
            log.warn("Trying to delete a non existent technical");
            return ResponseEntity.notFound().build();
        }
        especialidadTecnologiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Borrar todos los técnicos de la base de datos.

    @Operation(summary = "ATENCIÓN: eliminar TODOS los técnicos", description = "Con este método eliminamos todos los " +
            "técnicos de la base de datos")
    @Hidden
    @DeleteMapping("/api/especialidadTecnologias")
    public ResponseEntity<EspecialidadTecnologiaIdEntity> deleteAll() {
        log.info("REST Request for delete all technicals");
        especialidadTecnologiaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
