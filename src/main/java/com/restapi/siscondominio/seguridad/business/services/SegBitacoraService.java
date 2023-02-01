package com.restapi.siscondominio.seguridad.business.services;

import com.restapi.siscondominio.seguridad.business.dto.SegBitacoraDTO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraQueryVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraUpdateVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraVO;
import com.restapi.siscondominio.seguridad.persistence.entities.SegBitacora;
import com.restapi.siscondominio.seguridad.persistence.repositories.SegBitacoraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SegBitacoraService {

    @Autowired
    private SegBitacoraRepository segBitacoraRepository;

    public Long save(SegBitacoraVO vO) {
        SegBitacora bean = new SegBitacora();
        BeanUtils.copyProperties(vO, bean);
        bean = segBitacoraRepository.save(bean);
        return bean.getBitId();
    }

    public void delete(Long id) {
        segBitacoraRepository.deleteById(id);
    }

    public void update(Long id, SegBitacoraUpdateVO vO) {
        SegBitacora bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        segBitacoraRepository.save(bean);
    }

    public SegBitacoraDTO getById(Long id) {
        SegBitacora original = requireOne(id);
        return toDTO(original);
    }

    public Page<SegBitacoraDTO> query(SegBitacoraQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private SegBitacoraDTO toDTO(SegBitacora original) {
        SegBitacoraDTO bean = new SegBitacoraDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private SegBitacora requireOne(Long id) {
        return segBitacoraRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
