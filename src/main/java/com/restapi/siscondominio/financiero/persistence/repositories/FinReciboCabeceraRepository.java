package com.restapi.siscondominio.financiero.persistence.repositories;

import com.restapi.siscondominio.financiero.persistence.entities.FinReciboCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinReciboCabeceraRepository extends JpaRepository<FinReciboCabecera, Void>, JpaSpecificationExecutor<FinReciboCabecera> {

}