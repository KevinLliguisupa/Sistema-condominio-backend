package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinMontoDTO;
import com.restapi.siscondominio.financiero.business.vo.FinMontoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinMontoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinMontoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinMonto;
import com.restapi.siscondominio.financiero.persistence.repositories.FinMontoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinMontoService {

    @Autowired
    private FinMontoRepository finMontoRepository;

    public Long save(FinMontoVO vO) {
        FinMonto bean = new FinMonto();
        BeanUtils.copyProperties(vO, bean);
        bean = finMontoRepository.save(bean);
        return bean.getMonId();
    }

    public void delete(Long id) {
        finMontoRepository.deleteById(id);
    }

    public void update(Long id, FinMontoUpdateVO vO) {
        FinMonto bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finMontoRepository.save(bean);
    }

    public FinMontoDTO getById(Long id) {
        FinMonto original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinMontoDTO> query(FinMontoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinMontoDTO toDTO(FinMonto original) {
        FinMontoDTO bean = new FinMontoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinMonto requireOne(Long id) {
        return finMontoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
