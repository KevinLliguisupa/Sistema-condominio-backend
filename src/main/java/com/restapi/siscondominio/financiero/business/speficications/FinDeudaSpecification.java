package com.restapi.siscondominio.financiero.business.speficications;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import org.springframework.data.jpa.domain.Specification;

public class FinDeudaSpecification {
    public static Specification<FinDeuda> hasUser(String cedulaUsuario) {
        CtrUsuario usuBusqueda = new CtrUsuario();
        usuBusqueda.setUsuCedula(cedulaUsuario);
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("usuCedula"), usuBusqueda));
    }
}
