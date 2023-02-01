package com.restapi.siscondominio.seguridad.persistence.repositories;

import com.restapi.siscondominio.seguridad.persistence.entities.SegBitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SegBitacoraRepository extends JpaRepository<SegBitacora, Long>, JpaSpecificationExecutor<SegBitacora> {

}