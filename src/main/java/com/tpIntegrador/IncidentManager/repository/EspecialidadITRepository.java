package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.EspecialidadIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadITRepository extends JpaRepository<EspecialidadIT, Long> {
}
