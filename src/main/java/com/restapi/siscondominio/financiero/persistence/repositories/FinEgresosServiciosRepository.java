package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinEgresosServicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinEgresosServiciosRepository extends JpaRepository<FinEgresosServicios, Void>, JpaSpecificationExecutor<FinEgresosServicios> {

}