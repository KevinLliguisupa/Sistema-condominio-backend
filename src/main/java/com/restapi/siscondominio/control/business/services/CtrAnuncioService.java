package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import com.restapi.siscondominio.control.persistence.repositories.CtrAnuncioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrAnuncioService {

    @Autowired
    private CtrAnuncioRepository ctrAnuncioRepository;

    public Long save(CtrAnuncioVO vO) {
        CtrAnuncio bean = new CtrAnuncio();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrAnuncioRepository.save(bean);
        return bean.getAncId();
    }

    public void delete(Long id) {
        ctrAnuncioRepository.deleteById(id);
    }

    public void update(Long id, CtrAnuncioUpdateVO vO) {
        CtrAnuncio bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrAnuncioRepository.save(bean);
    }

    public CtrAnuncioDTO getById(Long id) {
        CtrAnuncio original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrAnuncioDTO> query(CtrAnuncioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrAnuncioDTO toDTO(CtrAnuncio original) {
        CtrAnuncioDTO bean = new CtrAnuncioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrAnuncio requireOne(Long id) {
        return ctrAnuncioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
