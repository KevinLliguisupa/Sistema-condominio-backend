package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import com.restapi.siscondominio.control.persistence.entities.CtrReunion;
import com.restapi.siscondominio.control.persistence.entities.CtrTipoAnuncio;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrAnuncioRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrTipoAnuncioRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrAnuncioService {

    @Autowired
    private CtrAnuncioRepository ctrAnuncioRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    @Autowired
    private CtrTipoAnuncioRepository ctrTipoAnuncioRepository;

    public Long save(CtrAnuncioVO vO) {
        CtrAnuncio bean = new CtrAnuncio();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrAnuncioRepository.save(bean);
        return bean.getAncId();
    }

    public void delete(Long id) {
        ctrAnuncioRepository.deleteById(id);
    }

    public void update(Long id, CtrAnuncioUpdateVO vO) {
        CtrAnuncio bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrAnuncioRepository.save(bean);
    }

    public CtrAnuncioDTO getById(Long id) {
        CtrAnuncio original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrAnuncioDTO> query(CtrAnuncioQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrAnuncioDTO toDTO(CtrAnuncio original) {
        CtrAnuncioDTO bean = new CtrAnuncioDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrAnuncio requireOne(Long id) {
        return ctrAnuncioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    //Guardar Anuncio
    private CtrAnuncioDTO toAnuncioDTO(CtrAnuncio anuncio){
        CtrAnuncioDTO anuncioDTO = new CtrAnuncioDTO();
        anuncioDTO.setAncId(anuncio.getAncId());
        anuncioDTO.setUsuCedula(anuncio.getUsuCedula().getUsuCedula());
        anuncioDTO.setAncTitulo(anuncio.getAncTitulo());
        anuncioDTO.setAncDescripcion(anuncio.getAncDescripcion());
        anuncioDTO.setAncFechaPublicacion(anuncio.getAncFechaPublicacion());
        anuncioDTO.setAncPrioridad(anuncio.getAncPrioridad());
        anuncioDTO.setAncEstado(anuncio.getAncEstado());
        anuncioDTO.setTanId(anuncio.getTan().getTanId());
        return anuncioDTO;
    }
    public CtrAnuncioDTO guardarAnuncio(CtrAnuncioVO anuncioVO, String cedula, Long tan) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Buscar el usuario en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(cedula)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + cedula + " no existe"));
            // Buscar el tipo de anuncio en la base de datos
            CtrTipoAnuncio tipoAnuncio = ctrTipoAnuncioRepository.findById(tan)
                    .orElseThrow(() -> new IllegalArgumentException("El tipo de anuncio con ID " + tan + " no existe"));
            // Crear una nueva entidad CtrAnuncio
            CtrAnuncio anuncio = new CtrAnuncio();
            BeanUtils.copyProperties(anuncioVO, anuncio);
            anuncio.setAncFechaPublicacion(fechaActual);
            anuncio.setAncEstado(true);
            anuncio.setUsuCedula(usuario);
            anuncio.setTan(tipoAnuncio);

            // Guardar la nueva entidad en la base de datos
            anuncio = ctrAnuncioRepository.save(anuncio);
            return toAnuncioDTO(anuncio);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Anuncio
    public CtrAnuncioDTO actualizarAnuncio(Long id, CtrAnuncioUpdateVO anuncioVO, Long tan) {
        try {
            // Buscar el anuncio en la base de datos
            CtrAnuncio anuncio = ctrAnuncioRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El anuncio con ID " + id + " no existe"));
            // Buscar el tipo de anuncio en la base de datos
            CtrTipoAnuncio tipoAnuncio = ctrTipoAnuncioRepository.findById(tan)
                    .orElseThrow(() -> new IllegalArgumentException("El tipo de anuncio con ID " + tan + " no existe"));
            // Actualizar la entidad CtrAnuncio
            BeanUtils.copyProperties(anuncioVO, anuncio);
            anuncio.setAncDescripcion(anuncioVO.getAncDescripcion());
            anuncio.setAncTitulo(anuncioVO.getAncTitulo());
            anuncio.setAncPrioridad(anuncioVO.getAncPrioridad());
            anuncio.setTan(tipoAnuncio);
            // Guardar la entidad actualizada en la base de datos
            anuncio = ctrAnuncioRepository.save(anuncio);
            return toAnuncioDTO(anuncio);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public CtrAnuncioDTO findById(Long id) {
        return toAnuncioDTO(ctrAnuncioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el anuncio con id: " + id)));
    }
    //Listar todos los anuncios
    public List<CtrAnuncioDTO> findAll() {
        return ctrAnuncioRepository.findAll().stream().map(this::toAnuncioDTO).collect(Collectors.toList());
    }

    //Listar todos los anuncios en orden Dec
    public List<CtrAnuncioDTO> findAllOrderDec() {
        List<CtrAnuncio> anuncios = ctrAnuncioRepository.findAll();
        anuncios = anuncios.stream().filter(anuncio -> anuncio.getAncEstado().equals(true)).collect(Collectors.toList());
        anuncios.sort(Comparator.comparing(CtrAnuncio::getAncId).reversed());
        return anuncios.stream().map(this::toAnuncioDTO).collect(Collectors.toList());
    }

    //Eliminar Anuncio Log
    public CtrAnuncioDTO EliminarAnuncioLog(Long id) {
        try {
            // Buscar el anuncio en la base de datos
            CtrAnuncio anuncio = ctrAnuncioRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El anuncio con ID " + id + " no existe"));
            anuncio.setAncEstado(false);
            // Guardar la entidad actualizada en la base de datos
            anuncio = ctrAnuncioRepository.save(anuncio);
            return toAnuncioDTO(anuncio);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }



}
