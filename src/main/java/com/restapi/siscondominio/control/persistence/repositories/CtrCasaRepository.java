package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrCasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrCasaRepository extends JpaRepository<CtrCasa, String>, JpaSpecificationExecutor<CtrCasa> {

}