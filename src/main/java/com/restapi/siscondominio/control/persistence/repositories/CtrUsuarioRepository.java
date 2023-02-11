package com.restapi.siscondominio.control.persistence.repositories;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CtrUsuarioRepository extends JpaRepository<CtrUsuario, String>, JpaSpecificationExecutor<CtrUsuario> {
    Optional<CtrUsuario> findOneByusuCedula(String usuCedula);



}