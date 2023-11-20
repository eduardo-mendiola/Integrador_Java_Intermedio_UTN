package com.tpIntegrador.IncidentManager.controller;

import com.tpIntegrador.IncidentManager.persistencia.model.Cliente;
import com.tpIntegrador.IncidentManager.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Aca creo los metodos para el CRUD, CREATE, READ, UPDATE, Y DELETE
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> crearCliente(@RequestBody Cliente cliente) {
        clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok().build();
    }
}
