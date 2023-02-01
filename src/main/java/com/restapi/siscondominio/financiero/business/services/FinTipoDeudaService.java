package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinTipoDeudaDTO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoDeuda;
import com.restapi.siscondominio.financiero.persistence.repositories.FinTipoDeudaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinTipoDeudaService {

    @Autowired
    private FinTipoDeudaRepository finTipoDeudaRepository;

    public Long save(FinTipoDeudaVO vO) {
        FinTipoDeuda bean = new FinTipoDeuda();
        BeanUtils.copyProperties(vO, bean);
        bean = finTipoDeudaRepository.save(bean);
        return bean.getTdeId();
    }

    public void delete(Long id) {
        finTipoDeudaRepository.deleteById(id);
    }

    public void update(Long id, FinTipoDeudaUpdateVO vO) {
        FinTipoDeuda bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finTipoDeudaRepository.save(bean);
    }

    public FinTipoDeudaDTO getById(Long id) {
        FinTipoDeuda original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinTipoDeudaDTO> query(FinTipoDeudaQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinTipoDeudaDTO toDTO(FinTipoDeuda original) {
        FinTipoDeudaDTO bean = new FinTipoDeudaDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinTipoDeuda requireOne(Long id) {
        return finTipoDeudaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
