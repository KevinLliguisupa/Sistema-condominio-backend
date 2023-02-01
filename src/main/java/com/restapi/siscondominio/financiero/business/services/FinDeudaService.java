package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaDTO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinDeudaService {

    @Autowired
    private FinDeudaRepository finDeudaRepository;

    public Long save(FinDeudaVO vO) {
        FinDeuda bean = new FinDeuda();
        BeanUtils.copyProperties(vO, bean);
        bean = finDeudaRepository.save(bean);
        return bean.getDeuId();
    }

    public void delete(Long id) {
        finDeudaRepository.deleteById(id);
    }

    public void update(Long id, FinDeudaUpdateVO vO) {
        FinDeuda bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finDeudaRepository.save(bean);
    }

    public FinDeudaDTO getById(Long id) {
        FinDeuda original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinDeudaDTO> query(FinDeudaQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinDeudaDTO toDTO(FinDeuda original) {
        FinDeudaDTO bean = new FinDeudaDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinDeuda requireOne(Long id) {
        return finDeudaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
