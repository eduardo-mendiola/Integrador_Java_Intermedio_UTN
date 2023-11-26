package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<ContratoEntity, Long> {
}
