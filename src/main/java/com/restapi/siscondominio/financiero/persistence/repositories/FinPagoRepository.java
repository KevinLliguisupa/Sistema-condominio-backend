package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinPagoRepository extends JpaRepository<FinPago, Long>, JpaSpecificationExecutor<FinPago> {

}