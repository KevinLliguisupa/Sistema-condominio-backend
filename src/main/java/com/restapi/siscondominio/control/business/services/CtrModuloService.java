package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrModuloDTO;
import com.restapi.siscondominio.control.business.vo.CtrModuloQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloVO;
import com.restapi.siscondominio.control.persistence.entities.CtrModulo;
import com.restapi.siscondominio.control.persistence.repositories.CtrModuloRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrModuloService {

    @Autowired
    private CtrModuloRepository ctrModuloRepository;

    public Long save(CtrModuloVO vO) {
        CtrModulo bean = new CtrModulo();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrModuloRepository.save(bean);
        return bean.getModId();
    }

    public void delete(Long id) {
        ctrModuloRepository.deleteById(id);
    }

    public void update(Long id, CtrModuloUpdateVO vO) {
        CtrModulo bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrModuloRepository.save(bean);
    }

    public CtrModuloDTO getById(Long id) {
        CtrModulo original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrModuloDTO> query(CtrModuloQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrModuloDTO toDTO(CtrModulo original) {
        CtrModuloDTO bean = new CtrModuloDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrModulo requireOne(Long id) {
        return ctrModuloRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
