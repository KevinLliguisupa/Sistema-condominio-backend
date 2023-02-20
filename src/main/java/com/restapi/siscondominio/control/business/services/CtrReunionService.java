package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.*;
import com.restapi.siscondominio.control.persistence.repositories.CtrLugarRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrReunionRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrReunionService {

    @Autowired
    private CtrReunionRepository ctrReunionRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    @Autowired
    private CtrLugarRepository ctrLugarRepository;

    public Long save(CtrReunionVO vO) {
        CtrReunion bean = new CtrReunion();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrReunionRepository.save(bean);
        return bean.getReuId();
    }

    public void delete(Long id) {
        ctrReunionRepository.deleteById(id);
    }

    public void update(Long id, CtrReunionUpdateVO vO) {
        CtrReunion bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrReunionRepository.save(bean);
    }

    public CtrReunionDTO getById(Long id) {
        CtrReunion original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrReunionDTO> query(CtrReunionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrReunionDTO toDTO(CtrReunion original) {
        CtrReunionDTO bean = new CtrReunionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrReunion requireOne(Long id) {
        return ctrReunionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
    private CtrReunionDTO toReunionDTO(CtrReunion ctrReunion){
        CtrReunionDTO reunionDTO = new CtrReunionDTO();
        reunionDTO.setReuId(ctrReunion.getReuId());
        reunionDTO.setUsuCedula(ctrReunion.getUsuCedula().getUsuCedula());
        reunionDTO.setReuFecha(ctrReunion.getReuFecha());
        reunionDTO.setReuEstado(ctrReunion.getReuEstado());
        reunionDTO.setLugId(toLugarDTO(ctrReunion.getLug()));
        return reunionDTO;
    }
    private CtrLugarDTO toLugarDTO(CtrLugar original) {
        CtrLugarDTO bean = null;
        try {
            bean = new CtrLugarDTO();
            BeanUtils.copyProperties(original, bean);
        } catch (Exception e) {
            System.out.println("Fallo conversion de Entidad a DTO: " + e);
        }
        return bean;
    }

    //Guardar Reunion
    public CtrReunionDTO guardarReunion(CtrReunionVO reunionVO, String cedula, Long IdLugar) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Buscar el usuario en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(cedula)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + cedula + " no existe"));
            // Buscar el tipo de anuncio en la base de datos
            CtrLugar lugar = ctrLugarRepository.findById(IdLugar)
                    .orElseThrow(() -> new IllegalArgumentException("El Lugar con ID " + IdLugar + " no existe"));
            if (reunionVO.getReuFecha().isBefore(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reunión debe ser igual o mayor a la fecha actual");
            }
            // Crear una nueva entidad CtrAnuncio
            CtrReunion reunion = new CtrReunion();
            BeanUtils.copyProperties(reunionVO, reunion);
            reunion.setUsuCedula(usuario);
            reunion.setReuFecha(reunionVO.getReuFecha());
            reunion.setReuEstado(true);
            reunion.setLug(lugar);

            // Guardar la nueva entidad en la base de datos
            reunion = ctrReunionRepository.save(reunion);
            return toReunionDTO(reunion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Reunion
    public CtrReunionDTO actualizarReunion(Long id, CtrReunionUpdateVO reunionVO, Long IdLugar) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Buscar el anuncio en la base de datos
            CtrReunion reunion  = ctrReunionRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La reunión con ID " + id + " no existe"));
            // Buscar el tipo de anuncio en la base de datos
            CtrLugar lugar = ctrLugarRepository.findById(IdLugar)
                    .orElseThrow(() -> new IllegalArgumentException("El lugar con ID " + IdLugar + " no existe"));
            if (reunionVO.getReuFecha().isBefore(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reunión debe ser igual o mayor a la fecha actual");
            }
            // Actualizar la entidad CtrAnuncio
            BeanUtils.copyProperties(reunionVO, reunion);
            reunion.setReuFecha(reunionVO.getReuFecha());
            reunion.setLug(lugar);

            // Guardar la entidad actualizada en la base de datos
            reunion = ctrReunionRepository.save(reunion);
            return toReunionDTO(reunion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Buscar por Id
    public CtrReunionDTO findById(Long id) {
        return toReunionDTO(ctrReunionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la reunión con id: " + id)));
    }
    //Listar todas las reuniones
    public List<CtrReunionDTO> findAll() {
        return ctrReunionRepository.findAll().stream().map(this::toReunionDTO).collect(Collectors.toList());
    }
    //Listar todas las reuniones en orden Dec
    public List<CtrReunionDTO> findAllOrderDec() {
        List<CtrReunion> reuniones = ctrReunionRepository.findAll();
        reuniones = reuniones.stream().filter(reunion -> reunion.getReuEstado().equals(true)).collect(Collectors.toList());
        reuniones.sort(Comparator.comparing(CtrReunion::getReuFecha).reversed());
        return reuniones.stream().map(this::toReunionDTO).collect(Collectors.toList());
    }


    //Actualizar Reunion Logico
    public CtrReunionDTO eliminarReunionLog(Long id) {

        try {
            // Buscar la reunion en la base de datos
            CtrReunion reunion  = ctrReunionRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La reunión con ID " + id + " no existe"));
            reunion.setReuEstado(false);
            // Guardar la entidad actualizada en la base de datos
            reunion = ctrReunionRepository.save(reunion);
            return toReunionDTO(reunion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

}
