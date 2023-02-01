package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrModulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrModuloRepository extends JpaRepository<CtrModulo, Long>, JpaSpecificationExecutor<CtrModulo> {

}