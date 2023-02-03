package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinIncidenciaRepository extends JpaRepository<FinIncidencia, Long>, JpaSpecificationExecutor<FinIncidencia> {

}