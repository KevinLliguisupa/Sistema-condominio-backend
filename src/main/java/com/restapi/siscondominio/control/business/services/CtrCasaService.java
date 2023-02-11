package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrCasaDTO;
import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.vo.CtrCasaQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrCasaUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrCasaVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import com.restapi.siscondominio.control.persistence.entities.CtrCasa;
import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrCasaRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrCasaService {

    @Autowired
    private CtrCasaRepository ctrCasaRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    //Listar Casa
    public List<CtrCasaDTO> findAll() {
        return ctrCasaRepository.findAll().stream().map(this::toCasaDTO).collect(Collectors.toList());
    }

    private CtrCasaDTO toCasaDTO(CtrCasa ctrCasa){
        CtrCasaDTO ctrCasaDTO = new CtrCasaDTO();
        ctrCasaDTO.setCasId(ctrCasa.getCasId());
        ctrCasaDTO.setUsuCedula(ctrCasa.getUsuCedula().getUsuCedula());
        ctrCasaDTO.setCasOcupado(ctrCasa.getCasOcupado());
        return ctrCasaDTO;
    }

    //Guardar Asiganacion de casa
    public CtrCasaDTO guardarCasa(CtrCasaVO casaVO) {

        try {
            // Buscar el usuario en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(casaVO.getUsuCedula())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + casaVO.getUsuCedula() + " no existe"));
            // Crear una nueva entidad CtrAnuncio
            CtrCasa casa = new CtrCasa();
            BeanUtils.copyProperties(casaVO, casa);
            casa.setCasId(casaVO.getCasId());
            casa.setUsuCedula(usuario);
            casa.setCasOcupado(true);

            // Guardar la nueva entidad en la base de datos
            casa = ctrCasaRepository.save(casa);
            return toCasaDTO(casa);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Casa
    public CtrCasaDTO actualizarCasa(CtrCasaUpdateVO casaVO) {

        try {
            // Buscar el usuario en la base de datos
            CtrCasa casa = ctrCasaRepository.findById(casaVO.getCasId())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + casaVO.getCasId() + " no existe"));
            // Buscar el usuario en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(casaVO.getUsuCedula())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + casaVO.getUsuCedula() + " no existe"));
            // Crear una nueva entidad CtrAnuncio

            BeanUtils.copyProperties(casaVO, casa);
            casa.setUsuCedula(usuario);

            // Guardar la nueva entidad en la base de datos
            casa = ctrCasaRepository.save(casa);
            return toCasaDTO(casa);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    public CtrCasaDTO eliminarCasa(String id) {

        try {
            // Buscar el usuario en la base de datos
            CtrCasa casa = ctrCasaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + id + " no existe"));
            // Crear una nueva entidad CtrAnuncio

            casa.setCasOcupado(false);

            // Guardar la nueva entidad en la base de datos
            casa = ctrCasaRepository.save(casa);
            return toCasaDTO(casa);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    //Activar Asignacion
    public CtrCasaDTO activarCasa(String id) {

        try {
            // Buscar el usuario en la base de datos
            CtrCasa casa = ctrCasaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + id + " no existe"));
            // Crear una nueva entidad CtrAnuncio

            casa.setCasOcupado(true);

            // Guardar la nueva entidad en la base de datos
            casa = ctrCasaRepository.save(casa);
            return toCasaDTO(casa);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }


}
