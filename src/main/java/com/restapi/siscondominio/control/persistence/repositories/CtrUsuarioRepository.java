package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CtrUsuarioRepository extends JpaRepository<CtrUsuario, String>, JpaSpecificationExecutor<CtrUsuario> {
    CtrUsuario findByUsuCedula(String usuCedula);
}