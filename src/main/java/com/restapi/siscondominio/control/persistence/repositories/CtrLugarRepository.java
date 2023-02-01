package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrLugarRepository extends JpaRepository<CtrLugar, Long>, JpaSpecificationExecutor<CtrLugar> {

}