package com.restapi.siscondominio.financiero.business.speficications;

import com.restapi.siscondominio.financiero.persistence.entities.FinRecibo;
import com.restapi.siscondominio.financiero.persistence.entities.FinReciboCabecera;
import org.springframework.data.jpa.domain.Specification;

public class FinReciboSpecification {
    public static Specification<FinRecibo> hasPagoId(Long pagoId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("pagId"), pagoId));
    }

    public static Specification<FinReciboCabecera> hasUser(String cedulaUsuario) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usuCedula"), cedulaUsuario));
    }
}
