package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinPagoDTO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinPago;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinPagoService {

    @Autowired
    private FinPagoRepository finPagoRepository;

    public Long save(FinPagoVO vO) {
        FinPago bean = new FinPago();
        BeanUtils.copyProperties(vO, bean);
        bean = finPagoRepository.save(bean);
        return bean.getPagId();
    }

    public void delete(Long id) {
        finPagoRepository.deleteById(id);
    }

    public void update(Long id, FinPagoUpdateVO vO) {
        FinPago bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finPagoRepository.save(bean);
    }

    public FinPagoDTO getById(Long id) {
        FinPago original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinPagoDTO> query(FinPagoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinPagoDTO toDTO(FinPago original) {
        FinPagoDTO bean = new FinPagoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinPago requireOne(Long id) {
        return finPagoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
