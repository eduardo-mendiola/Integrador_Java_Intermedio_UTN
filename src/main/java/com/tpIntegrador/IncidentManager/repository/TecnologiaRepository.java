package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
}
