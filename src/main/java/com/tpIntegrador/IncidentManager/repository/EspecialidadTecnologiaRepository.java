package com.tpIntegrador.IncidentManager.repository;

import com.tpIntegrador.IncidentManager.model.entity.EspecialidadTecnologiaEntity;
import com.tpIntegrador.IncidentManager.model.entity.EspecialidadTecnologiaIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadTecnologiaRepository extends JpaRepository<EspecialidadTecnologiaIdEntity, Long> {
    List<EspecialidadTecnologiaIdEntity> findAll();


}

