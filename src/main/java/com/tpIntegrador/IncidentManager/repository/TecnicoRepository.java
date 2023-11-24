package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}
