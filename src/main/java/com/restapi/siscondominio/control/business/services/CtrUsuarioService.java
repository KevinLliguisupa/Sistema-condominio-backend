package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import com.restapi.siscondominio.control.persistence.entities.CtrTipoAnuncio;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CtrUsuarioService {

    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    //Listar todos los Usuarios activos
    public List<CtrUsuarioDTO> findAllOrderDec() {
        List<CtrUsuario> usuarios = ctrUsuarioRepository.findAll();
        //usuarios = usuarios.stream().filter(usuario -> usuario.getUsuEstado()).collect(Collectors.toList());
        usuarios.sort(Comparator.comparing(CtrUsuario::getUsuCedula).reversed());
        return usuarios.stream().map(this::toUsuarioDTO).collect(Collectors.toList());
    }
    //Buscar usuario por Id
    public CtrUsuarioDTO findById(String cedula) {
        return toUsuarioDTO(ctrUsuarioRepository.findById(cedula)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el anuncio con id: " + cedula)));
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

    //Eliminar en estado logico

    public CtrUsuarioDTO eliminarUsuario(String cedula) {

        try {
            CtrUsuario usuario = ctrUsuarioRepository.findById(cedula)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + cedula + " no existe"));


            usuario.setUsuEstado(false);

            // Guardar la nueva entidad en la base de datos
            usuario = ctrUsuarioRepository.save(usuario);
            return toUsuarioDTO(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Activar Usuario
    public CtrUsuarioDTO activarUsuario(String cedula) {

        try {
            CtrUsuario usuario = ctrUsuarioRepository.findById(cedula)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + cedula + " no existe"));


            usuario.setUsuEstado(true);

            // Guardar la nueva entidad en la base de datos
            usuario = ctrUsuarioRepository.save(usuario);
            return toUsuarioDTO(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }



}
