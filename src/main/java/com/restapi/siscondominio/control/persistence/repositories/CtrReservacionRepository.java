package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrReservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrReservacionRepository extends JpaRepository<CtrReservacion, Long>, JpaSpecificationExecutor<CtrReservacion> {

}