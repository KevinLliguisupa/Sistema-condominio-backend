package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinIncidenciaDTO;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinIncidencia;
import com.restapi.siscondominio.financiero.persistence.repositories.FinIncidenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinIncidenciaService {

    @Autowired
    private FinIncidenciaRepository finIncidenciaRepository;

    public Long save(FinIncidenciaVO vO) {
        FinIncidencia bean = new FinIncidencia();
        BeanUtils.copyProperties(vO, bean);
        bean = finIncidenciaRepository.save(bean);
        return bean.getIncId();
    }

    public void delete(Long id) {
        finIncidenciaRepository.deleteById(id);
    }

    public void update(Long id, FinIncidenciaUpdateVO vO) {
        FinIncidencia bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finIncidenciaRepository.save(bean);
    }

    public FinIncidenciaDTO getById(Long id) {
        FinIncidencia original = requireOne(id);
        return toDTO(original);
    }

    public Page<FinIncidenciaDTO> query(FinIncidenciaQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FinIncidenciaDTO toDTO(FinIncidencia original) {
        FinIncidenciaDTO bean = new FinIncidenciaDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinIncidencia requireOne(Long id) {
        return finIncidenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
