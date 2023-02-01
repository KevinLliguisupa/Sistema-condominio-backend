package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrAsignacionRepository extends JpaRepository<CtrAsignacion, Long>, JpaSpecificationExecutor<CtrAsignacion> {

}