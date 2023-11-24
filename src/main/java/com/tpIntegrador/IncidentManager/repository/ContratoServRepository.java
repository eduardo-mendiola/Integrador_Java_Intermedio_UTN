package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.ContratoServ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoServRepository extends JpaRepository<ContratoServ, Long> {
}
