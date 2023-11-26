package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}

