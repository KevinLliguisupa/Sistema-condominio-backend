package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinIngresosGastosMensuale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoServicio;
public interface FinIngresosGastosMensualesRepository extends JpaRepository<FinIngresosGastosMensuale, String>, JpaSpecificationExecutor<FinTipoServicio> {

}