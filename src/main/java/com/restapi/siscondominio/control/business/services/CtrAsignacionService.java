package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrAsignacionDTO;
import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrPerfilDTO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilVO;
import com.restapi.siscondominio.control.persistence.entities.*;
import com.restapi.siscondominio.control.persistence.repositories.CtrAsignacionRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrPerfilRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrAsignacionService {

    @Autowired
    private CtrAsignacionRepository ctrAsignacionRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    @Autowired
    private CtrPerfilRepository ctrPerfilRepository;



    public void delete(Long id) {
        ctrAsignacionRepository.deleteById(id);
    }

    //Listar Asignaciones
    public List<CtrAsignacionDTO> findAll() {
        return ctrAsignacionRepository.findAll().stream().map(this::toAsignacion).collect(Collectors.toList());
    }

    //Listar las Asignaciones de un perfil

    public List<CtrAsignacionDTO> findByUsuCedula(String cedula) {
        List<CtrAsignacion> asignaciones = ctrAsignacionRepository.findAll();
        asignaciones = asignaciones.stream().filter(asignacion -> asignacion.getUsuCedula().equals(cedula)).collect(Collectors.toList());
        return asignaciones.stream().map(this::toAsignacion).collect(Collectors.toList());
    }


    private CtrAsignacionDTO toAsignacion (CtrAsignacion asignacion){
        CtrAsignacionDTO asignacionDTO = new CtrAsignacionDTO();
        asignacionDTO.setUsuCedula(asignacion.getUsuCedula().getUsuCedula());
        asignacionDTO.setAsgId(asignacion.getAsgId());
        asignacionDTO.setPrfId(asignacion.getPrf().getPrfId());
        return asignacionDTO;
    }

    //Guardar Asignacion
    public CtrAsignacionDTO guardarAsignacion(CtrAsignacionVO asignacionVO) {

        try {
            // Buscar el perfil en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(asignacionVO.getUsuCedula())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + asignacionVO.getUsuCedula()+ " no existe"));
            CtrPerfil perfil = ctrPerfilRepository.findById(asignacionVO.getPrfId())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + asignacionVO.getPrfId()+ " no existe"));
            // Crear una nueva entidad CtrAnuncio
            CtrAsignacion asignacion = new CtrAsignacion();
            BeanUtils.copyProperties(asignacionVO, asignacion);

            asignacion.setUsuCedula(usuario);
            asignacion.setPrf(perfil);

            // Guardar la nueva entidad en la base de datos
            asignacion = ctrAsignacionRepository.save(asignacion);
            return toAsignacion(asignacion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }





}
