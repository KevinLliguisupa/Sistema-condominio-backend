package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrTipoAnuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrTipoAnuncioRepository extends JpaRepository<CtrTipoAnuncio, Long>, JpaSpecificationExecutor<CtrTipoAnuncio> {

}