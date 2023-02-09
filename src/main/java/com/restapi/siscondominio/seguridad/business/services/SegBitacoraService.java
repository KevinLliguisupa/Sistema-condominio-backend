package com.restapi.siscondominio.seguridad.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.vo.CtrLugarUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.seguridad.business.dto.SegBitacoraDTO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraQueryVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraUpdateVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraVO;
import com.restapi.siscondominio.seguridad.persistence.entities.SegBitacora;
import com.restapi.siscondominio.seguridad.persistence.repositories.SegBitacoraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SegBitacoraService {

    @Autowired
    private SegBitacoraRepository segBitacoraRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    public Long save(SegBitacoraVO vO) {
        SegBitacora bean = new SegBitacora();
        BeanUtils.copyProperties(vO, bean);
        bean = segBitacoraRepository.save(bean);
        return bean.getBitId();
    }

    public void delete(Long id) {
        segBitacoraRepository.deleteById(id);
    }

    public void update(Long id, SegBitacoraUpdateVO vO) {
        SegBitacora bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        segBitacoraRepository.save(bean);
    }

    public SegBitacoraDTO getById(Long id) {
        SegBitacora original = requireOne(id);
        return toDTO(original);
    }

    public Page<SegBitacoraDTO> query(SegBitacoraQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private SegBitacoraDTO toDTO(SegBitacora original) {
        SegBitacoraDTO bean = new SegBitacoraDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private SegBitacora requireOne(Long id) {
        return segBitacoraRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    private SegBitacoraDTO toSegBitacoraDTO(SegBitacora segBitacora){
        SegBitacoraDTO bitacoraDTO = new SegBitacoraDTO();
        bitacoraDTO.setBitId(segBitacora.getBitId());
        bitacoraDTO.setBitCedGuardia(segBitacora.getBitCedGuardia());
        bitacoraDTO.setBitFecha(segBitacora.getBitFecha());
        bitacoraDTO.setBitEvento(segBitacora.getBitEvento());
        bitacoraDTO.setBitCedIngreso(segBitacora.getBitCedIngreso());
        bitacoraDTO.setBitDescripcion(segBitacora.getBitDescripcion());
        bitacoraDTO.setBitIngreso(segBitacora.getBitIngreso());
        return bitacoraDTO;
    }

    //Guardar una Bitacora
    public SegBitacoraDTO guardarBitacora(SegBitacoraVO segBitacoraVO, String cedula) {
        LocalDate fechaActual = LocalDate.now();
        try {

            // Crear una nueva entidad CtrAnuncio
            SegBitacora bitacora = new SegBitacora();
            BeanUtils.copyProperties(segBitacoraVO, bitacora);

            List<CtrUsuario> usuariosList =ctrUsuarioRepository.findAll();
            HashMap<String,String> mapUsuarios= new HashMap<>();
            for(CtrUsuario usuario: usuariosList ){
                mapUsuarios.put(usuario.getUsuCedula(),usuario.getUsuCedula());
            }


            if ( !mapUsuarios.containsKey(cedula)) {

                bitacora.setBitCedGuardia(segBitacoraVO.getBitCedGuardia());
                bitacora.setBitFecha(fechaActual);
                bitacora.setBitEvento(segBitacoraVO.getBitEvento());
                bitacora.setBitCedIngreso(segBitacoraVO.getBitCedIngreso());
                bitacora.setBitDescripcion("Invitado, "+segBitacoraVO.getBitDescripcion());
                bitacora.setBitIngreso(true);
            } else {
                bitacora.setBitCedGuardia(segBitacoraVO.getBitCedGuardia());
                bitacora.setBitFecha(fechaActual);
                bitacora.setBitEvento(segBitacoraVO.getBitEvento());
                bitacora.setBitCedIngreso(segBitacoraVO.getBitCedIngreso());
                bitacora.setBitDescripcion("Inquilino, "+segBitacoraVO.getBitDescripcion());
                bitacora.setBitIngreso(true);
            }




            // Guardar la nueva entidad en la base de datos
            bitacora = segBitacoraRepository.save(bitacora);
            return toSegBitacoraDTO(bitacora);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Bitacora
    public SegBitacoraDTO actualizarBitacora(Long id, SegBitacoraUpdateVO bitacoraUpdateVO, String cedula) {
        try {
            // Buscar el lugar en la base de datos
            SegBitacora bitacora  = segBitacoraRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La bitacora con ID " + id + " no existe"));
            // Actualizar la entidad SegBitacora
            BeanUtils.copyProperties(bitacoraUpdateVO, bitacora);
            //bitacora.setBitDescripcion();
            List<CtrUsuario> usuariosList =ctrUsuarioRepository.findAll();
            HashMap<String,String> mapUsuarios= new HashMap<>();
            for(CtrUsuario usuario: usuariosList ){
                mapUsuarios.put(usuario.getUsuCedula(),usuario.getUsuCedula());
            }


            if ( !mapUsuarios.containsKey(cedula)) {

                bitacora.setBitEvento(bitacoraUpdateVO.getBitEvento());
                bitacora.setBitCedIngreso(bitacoraUpdateVO.getBitCedIngreso());
                bitacora.setBitDescripcion("Invitado, "+bitacoraUpdateVO.getBitDescripcion());
                bitacora.setBitIngreso(true);
            } else {
                bitacora.setBitCedGuardia(bitacoraUpdateVO.getBitCedGuardia());
                bitacora.setBitEvento(bitacoraUpdateVO.getBitEvento());
                bitacora.setBitCedIngreso(bitacoraUpdateVO.getBitCedIngreso());
                bitacora.setBitDescripcion("Inquilino, "+bitacoraUpdateVO.getBitDescripcion());
                bitacora.setBitIngreso(true);
            }
            // Guardar la nueva entidad en la base de datos
            bitacora = segBitacoraRepository.save(bitacora);

            return toSegBitacoraDTO(bitacora);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Eliminar Bitacora Log
    public SegBitacoraDTO EliminarBitacoraLog(Long id) {
        try {
            // Buscar el anuncio en la base de datos
            SegBitacora bitacora = segBitacoraRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("La bitacora con ID " + id + " no existe"));
            bitacora.setBitIngreso(false);
            // Guardar la entidad actualizada en la base de datos
            bitacora = segBitacoraRepository.save(bitacora);
            return toSegBitacoraDTO(bitacora);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Listar todos los anuncios
    public List<SegBitacoraDTO> findAll() {
        return segBitacoraRepository.findAll().stream().map(this::toSegBitacoraDTO).collect(Collectors.toList());
    }

    //Listar todos los anuncios en orden Dec
    public List<SegBitacoraDTO> findAllOrderDec() {
        List<SegBitacora> segBitacoras = segBitacoraRepository.findAll();
        segBitacoras = segBitacoras.stream().filter(bitacora -> bitacora.getBitIngreso().equals(true)).collect(Collectors.toList());
        segBitacoras.sort(Comparator.comparing(SegBitacora::getBitId).reversed());
        return segBitacoras.stream().map(this::toSegBitacoraDTO).collect(Collectors.toList());
    }

    //Buscar por ID
    public SegBitacoraDTO findById(Long id) {
        return toSegBitacoraDTO(segBitacoraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la bitacora con id: " + id)));
    }
}
