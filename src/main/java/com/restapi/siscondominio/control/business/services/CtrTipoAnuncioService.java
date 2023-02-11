package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrTipoAnuncioDTO;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import com.restapi.siscondominio.control.persistence.entities.CtrTipoAnuncio;
import com.restapi.siscondominio.control.persistence.repositories.CtrTipoAnuncioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrTipoAnuncioService {

    @Autowired
    private CtrTipoAnuncioRepository ctrTipoAnuncioRepository;

    public Long save(CtrTipoAnuncioVO vO) {
        CtrTipoAnuncio bean = new CtrTipoAnuncio();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrTipoAnuncioRepository.save(bean);
        return bean.getTanId();
    }

    public void delete(Long id) {
        ctrTipoAnuncioRepository.deleteById(id);
    }

    public void update(Long id, CtrTipoAnuncioUpdateVO vO) {
        CtrTipoAnuncio bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrTipoAnuncioRepository.save(bean);
    }

    public CtrTipoAnuncioDTO getById(Long id) {
        CtrTipoAnuncio original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrTipoAnuncioDTO> query(CtrTipoAnuncioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrTipoAnuncioDTO toDTO(CtrTipoAnuncio original) {
        CtrTipoAnuncioDTO bean = new CtrTipoAnuncioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrTipoAnuncio requireOne(Long id) {
        return ctrTipoAnuncioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    //Guardar Tipo Anuncio
    public CtrTipoAnuncio save(CtrTipoAnuncio tipoAnuncio){
        return ctrTipoAnuncioRepository.save(tipoAnuncio);
    }


    private CtrTipoAnuncioDTO toTipoAnuncioDTO(CtrTipoAnuncio tipoAnuncio){
        CtrTipoAnuncioDTO tipoAnuncioDTO = new CtrTipoAnuncioDTO();
        tipoAnuncioDTO.setTanId(tipoAnuncio.getTanId());
        tipoAnuncioDTO.setTanNombre(tipoAnuncio.getTanNombre());
        return tipoAnuncioDTO;
    }
    //Guardar Tipo de Anuncio
    public CtrTipoAnuncioDTO guardarTipoAnuncio(CtrTipoAnuncioVO tipoAnuncioVO) {
        LocalDate fechaActual = LocalDate.now();
        try {

            // Crear una nueva entidad CtrAnuncio
            CtrTipoAnuncio tipoAnuncio = new CtrTipoAnuncio();
            BeanUtils.copyProperties(tipoAnuncioVO, tipoAnuncio);
            tipoAnuncio.setTanNombre(tipoAnuncioVO.getTanNombre());

            // Guardar la nueva entidad en la base de datos
            tipoAnuncio = ctrTipoAnuncioRepository.save(tipoAnuncio);
            return toTipoAnuncioDTO(tipoAnuncio);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    //Listar Tipos de Anuncios
    public List<CtrTipoAnuncioDTO> findAll() {
        return ctrTipoAnuncioRepository.findAll().stream().map(this::toTipoAnuncioDTO).collect(Collectors.toList());
    }
}
