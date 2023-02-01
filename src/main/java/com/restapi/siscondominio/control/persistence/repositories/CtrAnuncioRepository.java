package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrAnuncioRepository extends JpaRepository<CtrAnuncio, Long>, JpaSpecificationExecutor<CtrAnuncio> {

}