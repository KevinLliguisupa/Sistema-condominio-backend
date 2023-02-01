package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinTipoDeuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinTipoDeudaRepository extends JpaRepository<FinTipoDeuda, Long>, JpaSpecificationExecutor<FinTipoDeuda> {

}