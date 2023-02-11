package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CtrReunionRepository extends JpaRepository<CtrReunion, Long>, JpaSpecificationExecutor<CtrReunion> {
}