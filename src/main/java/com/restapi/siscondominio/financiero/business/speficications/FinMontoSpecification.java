package com.restapi.siscondominio.financiero.business.speficications;

import com.restapi.siscondominio.financiero.persistence.entities.FinMonto;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoDeuda;
import org.springframework.data.jpa.domain.Specification;

public class FinMontoSpecification {

    //SELECT WHERE mon_fecha_fin IS NULL
    public static Specification<FinMonto> isActive() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("monFechaFin")));
    }

    //SELECT WHERE tde_id=? AND mon_fecha_fin IS NULL
    public static Specification<FinMonto> hasTipo(Long tdeId) {
        FinTipoDeuda tipoDeuda = new FinTipoDeuda();
        tipoDeuda.setTdeId(tdeId);

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipoDeuda"), tipoDeuda));

    }

}
