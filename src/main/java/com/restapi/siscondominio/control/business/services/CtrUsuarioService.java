package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.dto.CtrUsuarioInfoDTO;
import com.restapi.siscondominio.control.business.exeption.DuplicatedException;
import com.restapi.siscondominio.control.business.specification.CtrUsuarioSpecification;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrCasaRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.control.presentation.utils.encryptionUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CtrUsuarioService {

    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    @Autowired
    private CtrCasaRepository ctrCasaRepository;

    public CtrUsuarioInfoDTO save(CtrUsuarioVO vO) {
        CtrUsuario existingUser = requireOneByEmail(vO.getUsuCorreo());
        System.out.println(existingUser);
        if (existingUser != null) {
            throw new DuplicatedException("User email already used");
        }
        vO.setUsuClave(encryptionUtility.encryptPassword(vO.getUsuClave()));
        CtrUsuario bean = new CtrUsuario();
        BeanUtils.copyProperties(vO, bean);
        bean.setUsuEstado(true);
        bean = ctrUsuarioRepository.save(bean);
        return toUserInfoDto(bean);
    }

    public Page<CtrUsuarioInfoDTO>  getCtrUsuarios(Integer page, Integer size, Boolean enablePagination){
        Sort sorter = Sort.by("usuApellidos");

        if (enablePagination) {
            return toPageUserInfoDto(ctrUsuarioRepository.findAll(PageRequest.of(page, size, sorter)));
        }
        List<CtrUsuarioInfoDTO> usersList = toListUserInfoDto(ctrUsuarioRepository.findAll(sorter));
        return toPage(usersList);
    }

    public Page<CtrUsuarioInfoDTO> toPageUserInfoDto(Page<CtrUsuario> original) {
        return original.map(this::toUserInfoDto);
    }
    private CtrUsuarioInfoDTO toUserInfoDto(CtrUsuario original) {
        CtrUsuarioInfoDTO bean = new CtrUsuarioInfoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    public List<CtrUsuarioInfoDTO> toListUserInfoDto(List<CtrUsuario> original) {
        List<CtrUsuarioInfoDTO> converted = new ArrayList<>();
        for (CtrUsuario user : original) {
            if(user.getUsuEstado()){
                converted.add(toUserInfoDto(user));
            }
        }
        return converted;
    }


    public Page<CtrUsuarioInfoDTO> toPage(List<CtrUsuarioInfoDTO> list) {
        Pageable pageable = PageRequest.of(0, list.size());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if (start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    public CtrUsuarioInfoDTO changeState(String id) {
        CtrUsuario bean = requireOne(id);
        bean.setUsuEstado(!bean.getUsuEstado());
        ctrUsuarioRepository.save(bean);
        return toUserInfoDto(bean);
    }

    public void delete(String id) {
        ctrUsuarioRepository.deleteById(id);
    }

    public CtrUsuarioInfoDTO update(String id, CtrUsuarioUpdateVO vO) {
        CtrUsuario bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrUsuarioRepository.save(bean);
        return toUserInfoDto(bean);
    }

    public CtrUsuarioDTO getById(String id) {
        CtrUsuario original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrUsuarioDTO> query(CtrUsuarioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    protected CtrUsuarioDTO toDTO(CtrUsuario original) {
        CtrUsuarioDTO bean = new CtrUsuarioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrUsuario requireOneByEmail(String email) {
        Specification<CtrUsuario> specification = Specification.where(CtrUsuarioSpecification.hasEmail(email));
        return ctrUsuarioRepository.findOne(specification)
                .orElse(null);
    }
    protected CtrUsuario requireOne(String id) {
        return ctrUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User id not found: " + id));
    }
}
