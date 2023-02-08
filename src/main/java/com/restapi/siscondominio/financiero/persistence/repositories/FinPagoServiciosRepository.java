package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinPagoServicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinPagoServiciosRepository extends JpaRepository<FinPagoServicios, Long>, JpaSpecificationExecutor<FinPagoServicios> {

}