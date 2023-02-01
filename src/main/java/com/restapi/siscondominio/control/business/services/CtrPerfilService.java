package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrPerfilDTO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilVO;
import com.restapi.siscondominio.control.persistence.entities.CtrPerfil;
import com.restapi.siscondominio.control.persistence.repositories.CtrPerfilRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrPerfilService {

    @Autowired
    private CtrPerfilRepository ctrPerfilRepository;

    public Long save(CtrPerfilVO vO) {
        CtrPerfil bean = new CtrPerfil();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrPerfilRepository.save(bean);
        return bean.getPrfId();
    }

    public void delete(Long id) {
        ctrPerfilRepository.deleteById(id);
    }

    public void update(Long id, CtrPerfilUpdateVO vO) {
        CtrPerfil bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrPerfilRepository.save(bean);
    }

    public CtrPerfilDTO getById(Long id) {
        CtrPerfil original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrPerfilDTO> query(CtrPerfilQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrPerfilDTO toDTO(CtrPerfil original) {
        CtrPerfilDTO bean = new CtrPerfilDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrPerfil requireOne(Long id) {
        return ctrPerfilRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
