package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.entity.TecnologiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository<TecnologiaEntity, Long> {
}
