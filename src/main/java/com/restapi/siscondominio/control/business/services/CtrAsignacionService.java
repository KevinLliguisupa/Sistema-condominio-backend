package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAsignacionDTO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAsignacion;
import com.restapi.siscondominio.control.persistence.repositories.CtrAsignacionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrAsignacionService {

    @Autowired
    private CtrAsignacionRepository ctrAsignacionRepository;

    public Long save(CtrAsignacionVO vO) {
        CtrAsignacion bean = new CtrAsignacion();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrAsignacionRepository.save(bean);
        return bean.getAsgId();
    }

    public void delete(Long id) {
        ctrAsignacionRepository.deleteById(id);
    }

    public void update(Long id, CtrAsignacionUpdateVO vO) {
        CtrAsignacion bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrAsignacionRepository.save(bean);
    }

    public CtrAsignacionDTO getById(Long id) {
        CtrAsignacion original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrAsignacionDTO> query(CtrAsignacionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrAsignacionDTO toDTO(CtrAsignacion original) {
        CtrAsignacionDTO bean = new CtrAsignacionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrAsignacion requireOne(Long id) {
        return ctrAsignacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
