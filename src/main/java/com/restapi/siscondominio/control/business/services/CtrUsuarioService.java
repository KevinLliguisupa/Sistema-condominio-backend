package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.dto.CtrUsuarioInfoDTO;
import com.restapi.siscondominio.control.business.exeption.DuplicatedException;
import com.restapi.siscondominio.control.business.specification.CtrUsuarioSpecification;
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
import java.util.Optional;

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
    //Buscar usuario por Id
    public CtrUsuarioDTO findById(String cedula) {
        return toUsuarioDTO(ctrUsuarioRepository.findById(cedula)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el anuncio con id: " + cedula)));
    }

    public CtrUsuarioInfoDTO update(String id, CtrUsuarioUpdateVO vO) {
        CtrUsuario bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrUsuarioRepository.save(bean);
        return toUserInfoDto(bean);
    }

    //Guardar nuevo Usuario
    public CtrUsuarioDTO guardarUsuario(CtrUsuarioVO usuarioVO) {


        try {
            Optional<CtrUsuario> usuarioExistente = Optional.ofNullable(ctrUsuarioRepository.findByUsuCedula(usuarioVO.getUsuCedula()));
            if (usuarioExistente.isPresent()) {
                throw new RuntimeException("Ya existe un usuario con la misma cédula");
            }
            CtrUsuario usuario = new CtrUsuario();
            BeanUtils.copyProperties(usuarioVO, usuario);

                usuario.setUsuCedula(usuarioVO.getUsuCedula());
                usuario.setUsuApellidos(usuarioVO.getUsuApellidos());
                usuario.setUsuNombres(usuarioVO.getUsuNombres());
                usuario.setUsuCorreo(usuarioVO.getUsuCorreo());
                usuario.setUsuTelefono(usuarioVO.getUsuTelefono());
                usuario.setUsuClave(usuarioVO.getUsuClave());
                usuario.setUsuEstado(true);



            // Guardar la nueva entidad en la base de datos
            usuario = ctrUsuarioRepository.save(usuario);
            return toUsuarioDTO(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Usuario
    public CtrUsuarioDTO actualizarUsuario(CtrUsuarioUpdateVO usuarioVO) {

        try {
            // Buscar el usuario en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(usuarioVO.getUsuCedula())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + usuarioVO.getUsuCedula() + " no existe"));
            BeanUtils.copyProperties(usuarioVO, usuario);

            usuario.setUsuApellidos(usuarioVO.getUsuApellidos());
            usuario.setUsuNombres(usuarioVO.getUsuNombres());
            usuario.setUsuCorreo(usuarioVO.getUsuCorreo());
            usuario.setUsuTelefono(usuarioVO.getUsuTelefono());
            usuario.setUsuClave(usuarioVO.getUsuClave());

            // Guardar la nueva entidad en la base de datos
            usuario = ctrUsuarioRepository.save(usuario);
            return toUsuarioDTO(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        }
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

    private CtrUsuarioDTO toUsuarioDTO (CtrUsuario ctrUsuario){
        CtrUsuarioDTO usuarioDTO = new CtrUsuarioDTO();
        usuarioDTO.setUsuCedula(ctrUsuario.getUsuCedula());
        usuarioDTO.setUsuApellidos(ctrUsuario.getUsuApellidos());
        usuarioDTO.setUsuNombres(ctrUsuario.getUsuNombres());
        usuarioDTO.setUsuCorreo(ctrUsuario.getUsuCorreo());
        usuarioDTO.setUsuTelefono(ctrUsuario.getUsuTelefono());
        usuarioDTO.setUsuClave(ctrUsuario.getUsuClave());
        usuarioDTO.setUsuEstado(ctrUsuario.getUsuEstado());

        return usuarioDTO;

    }

    public CtrUsuarioDTO getById(String id) {
        CtrUsuario original = requireOne(id);
        return toDTO(original);
    }



}
