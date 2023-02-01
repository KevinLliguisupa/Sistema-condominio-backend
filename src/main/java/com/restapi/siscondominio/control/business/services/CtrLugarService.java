package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.vo.CtrLugarQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import com.restapi.siscondominio.control.persistence.repositories.CtrLugarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrLugarService {

    @Autowired
    private CtrLugarRepository ctrLugarRepository;

    public Long save(CtrLugarVO vO) {
        CtrLugar bean = new CtrLugar();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrLugarRepository.save(bean);
        return bean.getLugId();
    }

    public void delete(Long id) {
        ctrLugarRepository.deleteById(id);
    }

    public void update(Long id, CtrLugarUpdateVO vO) {
        CtrLugar bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrLugarRepository.save(bean);
    }

    public CtrLugarDTO getById(Long id) {
        CtrLugar original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrLugarDTO> query(CtrLugarQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrLugarDTO toDTO(CtrLugar original) {
        CtrLugarDTO bean = new CtrLugarDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrLugar requireOne(Long id) {
        return ctrLugarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
