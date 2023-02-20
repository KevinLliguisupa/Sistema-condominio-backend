package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrReservacionDTO;
import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.*;
import com.restapi.siscondominio.control.persistence.repositories.CtrLugarRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrReservacionRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CtrReservacionService {

    @Autowired
    private CtrReservacionRepository ctrReservacionRepository;
    @Autowired
    private CtrLugarRepository ctrLugarRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    public Long save(CtrReservacionVO vO) {
        CtrReservacion bean = new CtrReservacion();
        BeanUtils.copyProperties(vO, bean);
        bean = ctrReservacionRepository.save(bean);
        return bean.getResId();
    }

    public void delete(Long id) {
        ctrReservacionRepository.deleteById(id);
    }

    public void update(Long id, CtrReservacionUpdateVO vO) {
        CtrReservacion bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ctrReservacionRepository.save(bean);
    }

    public CtrReservacionDTO getById(Long id) {
        CtrReservacion original = requireOne(id);
        return toDTO(original);
    }

    public Page<CtrReservacionDTO> query(CtrReservacionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CtrReservacionDTO toDTO(CtrReservacion original) {
        CtrReservacionDTO bean = new CtrReservacionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CtrReservacion requireOne(Long id) {
        return ctrReservacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    private CtrReservacionDTO toReservacionDTO(CtrReservacion reservacion) {
        CtrReservacionDTO reservacionDTO = new CtrReservacionDTO();
        reservacionDTO.setResId(reservacion.getResId());
        reservacionDTO.setLugId(toLugarDTO(reservacion.getLug()));
        reservacionDTO.setUsuCedula(reservacion.getUsuCedula().getUsuCedula());
        reservacionDTO.setResFecha(reservacion.getResFecha());
        reservacionDTO.setResHoraInicio(reservacion.getResHoraInicio());
        reservacionDTO.setResHoraFin(reservacion.getResHoraFin());
        reservacionDTO.setResAprobado(reservacion.getResAprobado());
        reservacionDTO.setResActiva(reservacion.getResActiva());
        return reservacionDTO;
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

    //Guardar Reservaciones
    public CtrReservacionDTO guardarReservacion(CtrReservacionVO reservacionVO, String cedula, Long idLugar) {
        LocalDate fechaActual = LocalDate.now();
        try {

            // Buscar el usuario en la base de datos
            CtrUsuario usuario = ctrUsuarioRepository.findById(cedula)
                    .orElseThrow(() -> new IllegalArgumentException("El usuario con cédula " + cedula + " no existe"));
            // Buscar el lugar en la base de datos
            CtrLugar lugar = ctrLugarRepository.findById(idLugar)
                    .orElseThrow(() -> new IllegalArgumentException("El lugar con ID " + idLugar+ " no existe"));
            if (reservacionVO.getResFecha().isBefore(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reservación debe ser igual o mayor a la fecha actual");
            }
            List<CtrReservacion> reservacionesExistentes = ctrReservacionRepository.findByLugAndResFecha(lugar, reservacionVO.getResFecha());
            for (CtrReservacion reservacionExistente : reservacionesExistentes) {
                if (!(reservacionExistente.getResHoraFin().isBefore(reservacionVO.getResHoraInicio()) ||
                        reservacionExistente.getResHoraInicio().isAfter(reservacionVO.getResHoraFin()))) {
                    throw new IllegalArgumentException("El lugar ya se encuntra reservado en ese horario");
                }
            }
            Optional<CtrReservacion> reservacionPrevias = ctrReservacionRepository.findByLugAndResFechaAndResHoraInicioAndResHoraFin(lugar, reservacionVO.getResFecha(), reservacionVO.getResHoraInicio(), reservacionVO.getResHoraFin());
            if (reservacionPrevias.isPresent()) {
                throw new IllegalArgumentException("El lugar ya se encutra reservado");
            }



            // Crear una nueva entidad CtrAnuncio
            CtrReservacion reservacion = new CtrReservacion();
            BeanUtils.copyProperties(reservacionVO, reservacion);
            reservacion.setLug(lugar);
            reservacion.setUsuCedula(usuario);
            reservacion.setResFecha(reservacionVO.getResFecha());
            reservacion.setResHoraInicio(reservacionVO.getResHoraInicio());
            reservacion.setResHoraFin(reservacionVO.getResHoraFin());
            reservacion.setResAprobado(false);
            reservacion.setResActiva(true);

            // Guardar la nueva entidad en la base de datos
            reservacion = ctrReservacionRepository.save(reservacion);
            return toReservacionDTO(reservacion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    //Actulizar Reservaciones
    public CtrReservacionDTO actualizarReservacion(Long id, CtrReservacionUpdateVO reservacionVO, Long IdLugar) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Buscar el anuncio en la base de datos
            CtrReservacion reservacion  = ctrReservacionRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La reservación con ID " + id + " no existe"));
            // Buscar el tipo de anuncio en la base de datos
            CtrLugar lugar = ctrLugarRepository.findById(IdLugar)
                    .orElseThrow(() -> new IllegalArgumentException("El lugar con ID " + IdLugar + " no existe"));
            if (reservacionVO.getResFecha().isBefore(fechaActual)) {
                throw new IllegalArgumentException("La fecha de reunión debe ser igual o mayor a la fecha actual");
            }
            List<CtrReservacion> reservacionesExistentes = ctrReservacionRepository.findByLugAndResFecha(lugar, reservacionVO.getResFecha());
            for (CtrReservacion reservacionExistente : reservacionesExistentes) {
                if (!(reservacionExistente.getResHoraFin().isBefore(reservacionVO.getResHoraInicio()) ||
                        reservacionExistente.getResHoraInicio().isAfter(reservacionVO.getResHoraFin()))) {
                    throw new IllegalArgumentException("El lugar ya se encuntra reservado en ese horario");
                }
            }
            Optional<CtrReservacion> reservacionPrevias = ctrReservacionRepository.findByLugAndResFechaAndResHoraInicioAndResHoraFin(lugar, reservacionVO.getResFecha(), reservacionVO.getResHoraInicio(), reservacionVO.getResHoraFin());
            if (reservacionPrevias.isPresent()) {
                throw new IllegalArgumentException("El lugar ya se encutra reservado");
            }
            // Actualizar la entidad CtrAnuncio
            BeanUtils.copyProperties(reservacionVO, reservacion);
            reservacion.setResFecha(reservacionVO.getResFecha());
            reservacion.setResHoraInicio(reservacionVO.getResHoraInicio());
            reservacion.setResHoraFin(reservacionVO.getResHoraFin());
            reservacion.setLug(lugar);


            // Guardar la entidad actualizada en la base de datos
            reservacion = ctrReservacionRepository.save(reservacion);
            return toReservacionDTO(reservacion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    //Aprobación de reservaciones
    public CtrReservacionDTO actualizarReserEstado(Long id) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Buscar el anuncio en la base de datos
            CtrReservacion reservacion  = ctrReservacionRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La reservación con ID " + id + " no existe"));

            //Actulizar el estado
            reservacion.setResAprobado(true);

            // Guardar la entidad actualizada en la base de datos
            reservacion = ctrReservacionRepository.save(reservacion);
            return toReservacionDTO(reservacion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
    //Eliminar  reservacion Log
    public CtrReservacionDTO EliminarReserEstado(Long id) {
        LocalDate fechaActual = LocalDate.now();
        try {
            // Buscar el anuncio en la base de datos
            CtrReservacion reservacion  = ctrReservacionRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La reservación con ID " + id + " no existe"));

            //Actulizar el estado
            reservacion.setResActiva(false);

            // Guardar la entidad actualizada en la base de datos
            reservacion = ctrReservacionRepository.save(reservacion);
            return toReservacionDTO(reservacion);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Buscar por Id
    public CtrReservacionDTO findById(Long id) {
        return toReservacionDTO(ctrReservacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la reservación con id: " + id)));
    }
    //Listar todas las reuniones
    public List<CtrReservacionDTO> findAll() {
        return ctrReservacionRepository.findAll().stream().map(this::toReservacionDTO).collect(Collectors.toList());
    }

    //Listar las reuniones mas recientes a la fecha actual
    public List<CtrReservacionDTO> listarReservacionesFechaReciente() {
        LocalDate fechaActual = LocalDate.now();
        List<CtrReservacion> reservacions = ctrReservacionRepository.findAll();
        reservacions = reservacions.stream().filter(reservacion -> reservacion.getResActiva().equals(true)).collect(Collectors.toList());
        reservacions.sort(Comparator.comparing(CtrReservacion::getResFecha).reversed());
        return reservacions.stream().map(this::toReservacionDTO).collect(Collectors.toList());
    }




}
