package com.restapi.siscondominio.control.business.specification;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import org.springframework.data.jpa.domain.Specification;

public class CtrUsuarioSpecification {

    public static Specification<CtrUsuario> hasState(Boolean state) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usuEstado"), state));
    }

    public static Specification<CtrUsuario> hasEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usuCorreo"), email));
    }

}
