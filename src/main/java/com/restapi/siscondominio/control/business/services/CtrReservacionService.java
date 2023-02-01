package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrReservacionDTO;
import com.restapi.siscondominio.control.business.vo.CtrReservacionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrReservacionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrReservacionVO;
import com.restapi.siscondominio.control.persistence.entities.CtrReservacion;
import com.restapi.siscondominio.control.persistence.repositories.CtrReservacionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrReservacionService {

    @Autowired
    private CtrReservacionRepository ctrReservacionRepository;

    public Long save(CtrReservacionVO vO) {
        CtrReservacion bean = new CtrReservacion();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrReservacionRepository.save(bean);
        return bean.getResId();
    }

    public void delete(Long id) {
        ctrReservacionRepository.deleteById(id);
    }

    public void update(Long id, CtrReservacionUpdateVO vO) {
        CtrReservacion bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrReservacionRepository.save(bean);
    }

    public CtrReservacionDTO getById(Long id) {
        CtrReservacion original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrReservacionDTO> query(CtrReservacionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrReservacionDTO toDTO(CtrReservacion original) {
        CtrReservacionDTO bean = new CtrReservacionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrReservacion requireOne(Long id) {
        return ctrReservacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
