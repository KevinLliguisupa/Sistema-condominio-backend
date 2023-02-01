package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinTipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinTipoServicioRepository extends JpaRepository<FinTipoServicio, Long>, JpaSpecificationExecutor<FinTipoServicio> {

}