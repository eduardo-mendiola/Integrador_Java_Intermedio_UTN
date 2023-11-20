package com.tpIntegrador.IncidentManager.service;

import com.tpIntegrador.IncidentManager.persistencia.model.Cliente;
import com.tpIntegrador.IncidentManager.persistencia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId).orElse(null);
    }

    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    public void actualizarCliente(Long id, Cliente cliente) {
    }

    public void crearCliente(Cliente cliente) {
    }
}
