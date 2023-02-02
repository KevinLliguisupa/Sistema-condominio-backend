package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CtrUsuarioService {

    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    public String save(CtrUsuarioVO vO) {
        CtrUsuario bean = new CtrUsuario();
        BeanUtils.copyProperties(vO, bean);
        bean.setUsuEstado(true);
        bean = ctrUsuarioRepository.save(bean);
        return bean.getUsuCedula();
    }

    public void delete(String id) {
        ctrUsuarioRepository.deleteById(id);
    }

    public void update(String id, CtrUsuarioUpdateVO vO) {
        CtrUsuario bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrUsuarioRepository.save(bean);
    }

    public CtrUsuarioDTO getById(String id) {
        CtrUsuario original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrUsuarioDTO> query(CtrUsuarioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrUsuarioDTO toDTO(CtrUsuario original) {
        CtrUsuarioDTO bean = new CtrUsuarioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrUsuario requireOne(String id) {
        return ctrUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
