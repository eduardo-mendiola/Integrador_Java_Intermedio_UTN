package com.tpIntegrador.IncidentManager.persistencia.repository;

import com.tpIntegrador.IncidentManager.persistencia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}

