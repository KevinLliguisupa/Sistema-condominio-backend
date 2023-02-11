package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CtrAsignacionRepository extends JpaRepository<CtrAsignacion, Long>, JpaSpecificationExecutor<CtrAsignacion> {
    List<CtrAsignacion> findByUsuCedula(String cedula);
}