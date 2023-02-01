package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.vo.CtrReunionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrReunionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrReunionVO;
import com.restapi.siscondominio.control.persistence.entities.CtrReunion;
import com.restapi.siscondominio.control.persistence.repositories.CtrReunionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrReunionService {

    @Autowired
    private CtrReunionRepository ctrReunionRepository;

    public Long save(CtrReunionVO vO) {
        CtrReunion bean = new CtrReunion();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrReunionRepository.save(bean);
        return bean.getReuId();
    }

    public void delete(Long id) {
        ctrReunionRepository.deleteById(id);
    }

    public void update(Long id, CtrReunionUpdateVO vO) {
        CtrReunion bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrReunionRepository.save(bean);
    }

    public CtrReunionDTO getById(Long id) {
        CtrReunion original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrReunionDTO> query(CtrReunionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrReunionDTO toDTO(CtrReunion original) {
        CtrReunionDTO bean = new CtrReunionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrReunion requireOne(Long id) {
        return ctrReunionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
