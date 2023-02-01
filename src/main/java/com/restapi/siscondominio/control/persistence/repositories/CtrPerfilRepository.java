package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrPerfilRepository extends JpaRepository<CtrPerfil, Long>, JpaSpecificationExecutor<CtrPerfil> {

}