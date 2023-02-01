package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinTipoServicioDTO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoServicio;
import com.restapi.siscondominio.financiero.persistence.repositories.FinTipoServicioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinTipoServicioService {

    @Autowired
    private FinTipoServicioRepository finTipoServicioRepository;

    public Long save(FinTipoServicioVO vO) {
        FinTipoServicio bean = new FinTipoServicio();
        BeanUtils.copyProperties(vO, bean);
        bean = finTipoServicioRepository.save(bean);
        return bean.getTseId();
    }

    public void delete(Long id) {
        finTipoServicioRepository.deleteById(id);
    }

    public void update(Long id, FinTipoServicioUpdateVO vO) {
        FinTipoServicio bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finTipoServicioRepository.save(bean);
    }

    public FinTipoServicioDTO getById(Long id) {
        FinTipoServicio original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinTipoServicioDTO> query(FinTipoServicioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinTipoServicioDTO toDTO(FinTipoServicio original) {
        FinTipoServicioDTO bean = new FinTipoServicioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinTipoServicio requireOne(Long id) {
        return finTipoServicioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
