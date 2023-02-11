package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import com.restapi.siscondominio.control.persistence.entities.CtrReunion;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrLugarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrLugarService {

    @Autowired
    private CtrLugarRepository ctrLugarRepository;

    public Long save(CtrLugarVO vO) {
        CtrLugar bean = new CtrLugar();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrLugarRepository.save(bean);
        return bean.getLugId();
    }

    public void delete(Long id) {
        ctrLugarRepository.deleteById(id);
    }

    public void update(Long id, CtrLugarUpdateVO vO) {
        CtrLugar bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrLugarRepository.save(bean);
    }

    public CtrLugarDTO getById(Long id) {
        CtrLugar original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrLugarDTO> query(CtrLugarQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrLugarDTO toDTO(CtrLugar original) {
        CtrLugarDTO bean = new CtrLugarDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrLugar requireOne(Long id) {
        return ctrLugarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    private CtrLugarDTO toLugarDTO(CtrLugar ctrLugar){
        CtrLugarDTO lugarDTO = new CtrLugarDTO();
        lugarDTO.setLugId(ctrLugar.getLugId());
        lugarDTO.setLugNombre(ctrLugar.getLugNombre());
        lugarDTO.setLugDisponible(ctrLugar.getLugDisponible());
        return lugarDTO;
    }

    //Guardar Lugar
    public CtrLugarDTO guardarLugares(CtrLugarVO lugarVO) {

        try {

            // Crear una nueva entidad CtrAnuncio
            CtrLugar lugar = new CtrLugar();
            BeanUtils.copyProperties(lugarVO, lugar);
            lugar.setLugNombre(lugarVO.getLugNombre());
            lugar.setLugDisponible(true);

            // Guardar la nueva entidad en la base de datos
            lugar = ctrLugarRepository.save(lugar);
            return toLugarDTO(lugar);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Lugar
    public CtrLugarDTO actualizarLugar(Long id, CtrLugarUpdateVO lugarUpdateVO) {
        try {
            // Buscar el lugar en la base de datos
            CtrLugar ctrLugar  = ctrLugarRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El lugar con ID " + id + " no existe"));
            // Actualizar la entidad CtrLugar
            BeanUtils.copyProperties(lugarUpdateVO, ctrLugar);
            ctrLugar.setLugNombre(lugarUpdateVO.getLugNombre());

            // Guardar la entidad actualizada en la base de datos
            ctrLugar = ctrLugarRepository.save(ctrLugar);
            return toLugarDTO(ctrLugar);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Buscar por Id
    public CtrLugarDTO findById(Long id) {
        return toLugarDTO(ctrLugarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el Lugar con id: " + id)));
    }
    //Listar todas las reuniones
    public List<CtrLugarDTO> findAll() {
        return ctrLugarRepository.findAll().stream().map(this::toLugarDTO).collect(Collectors.toList());
    }
}
