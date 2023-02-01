package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrCasaDTO;
import com.restapi.siscondominio.control.business.vo.CtrCasaQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrCasaUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrCasaVO;
import com.restapi.siscondominio.control.persistence.entities.CtrCasa;
import com.restapi.siscondominio.control.persistence.repositories.CtrCasaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrCasaService {

    @Autowired
    private CtrCasaRepository ctrCasaRepository;

    public String save(CtrCasaVO vO) {
        CtrCasa bean = new CtrCasa();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrCasaRepository.save(bean);
        return bean.getCasId();
    }

    public void delete(String id) {
        ctrCasaRepository.deleteById(id);
    }

    public void update(String id, CtrCasaUpdateVO vO) {
        CtrCasa bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrCasaRepository.save(bean);
    }

    public CtrCasaDTO getById(String id) {
        CtrCasa original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrCasaDTO> query(CtrCasaQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrCasaDTO toDTO(CtrCasa original) {
        CtrCasaDTO bean = new CtrCasaDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrCasa requireOne(String id) {
        return ctrCasaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
