package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinGastoDTO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinGasto;
import com.restapi.siscondominio.financiero.persistence.repositories.FinGastoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinGastoService {

    @Autowired
    private FinGastoRepository finGastoRepository;

    public Long save(FinGastoVO vO) {
        FinGasto bean = new FinGasto();
        BeanUtils.copyProperties(vO, bean);
        bean = finGastoRepository.save(bean);
        return bean.getGasId();
    }

    public void delete(Long id) {
        finGastoRepository.deleteById(id);
    }

    public void update(Long id, FinGastoUpdateVO vO) {
        FinGasto bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finGastoRepository.save(bean);
    }

    public FinGastoDTO getById(Long id) {
        FinGasto original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinGastoDTO> query(FinGastoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinGastoDTO toDTO(FinGasto original) {
        FinGastoDTO bean = new FinGastoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinGasto requireOne(Long id) {
        return finGastoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
