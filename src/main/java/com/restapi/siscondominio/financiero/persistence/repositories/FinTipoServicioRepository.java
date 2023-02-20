package com.restapi.siscondominio.financiero.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoServicio;
public interface FinTipoServicioRepository extends JpaRepository<FinTipoServicio, Long>, JpaSpecificationExecutor<FinTipoServicio> {

}