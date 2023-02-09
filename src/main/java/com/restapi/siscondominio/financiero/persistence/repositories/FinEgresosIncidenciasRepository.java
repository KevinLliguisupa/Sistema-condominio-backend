package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinEgresosIncidencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinEgresosIncidenciasRepository extends JpaRepository<FinEgresosIncidencias, Void>, JpaSpecificationExecutor<FinEgresosIncidencias> {

}