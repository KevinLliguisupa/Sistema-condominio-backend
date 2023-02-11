package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaPagoDTO;
import com.restapi.siscondominio.financiero.business.dto.FinPagoDTO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaPagoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaPagoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaPagoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeudaPago;
import com.restapi.siscondominio.financiero.persistence.entities.FinPago;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaPagoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinDeudaPagoService extends Servicio<FinDeudaPago, FinDeudaPagoDTO>{

    @Autowired
    private FinDeudaPagoRepository finDeudaPagoRepository;

    public Long save(FinDeudaPagoVO vO) {
        FinDeudaPago bean = new FinDeudaPago();
        BeanUtils.copyProperties(vO, bean);
        bean = finDeudaPagoRepository.save(bean);
        return bean.getDepId();
    }

    public void delete(Long id) {
        finDeudaPagoRepository.deleteById(id);
    }

    public void update(Long id, FinDeudaPagoUpdateVO vO) {
        FinDeudaPago bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finDeudaPagoRepository.save(bean);
    }

    public FinDeudaPagoDTO getById(Long id) {
        FinDeudaPago original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinDeudaPagoDTO> query(FinDeudaPagoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

//    public FinDeudaPagoDTO toDTO(FinDeudaPago original) {
//        FinDeudaPagoDTO bean = new FinDeudaPagoDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }

    public FinDeudaPagoDTO toDTO(FinDeudaPago original) {
        FinDeudaPagoDTO bean = new FinDeudaPagoDTO();
        BeanUtils.copyProperties(original, bean);
        bean.setPago(toPagoDTO(original.getPag()));
        return bean;
    }

    public FinPagoDTO toPagoDTO(FinPago original) {
        FinPagoDTO bean = new FinPagoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinDeudaPago requireOne(Long id) {
        return finDeudaPagoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
