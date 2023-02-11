package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrCasaDTO;
import com.restapi.siscondominio.control.business.dto.CtrModuloDTO;
import com.restapi.siscondominio.control.business.vo.CtrCasaVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import com.restapi.siscondominio.control.persistence.entities.CtrCasa;
import com.restapi.siscondominio.control.persistence.entities.CtrModulo;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrModuloRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrModuloService {

    @Autowired
    private CtrModuloRepository ctrModuloRepository;

    //Listar modulos
    public List<CtrModuloDTO> findAll() {
        return ctrModuloRepository.findAll().stream().map(this::toModuloDTO).collect(Collectors.toList());
    }

    private CtrModuloDTO toModuloDTO (CtrModulo ctrModulo){
        CtrModuloDTO ctrModuloDTO = new CtrModuloDTO();
        ctrModuloDTO.setModId(ctrModulo.getModId());
        ctrModuloDTO.setModNombre(ctrModulo.getModNombre());
        ctrModuloDTO.setModIcono(ctrModulo.getModIcono());
        return ctrModuloDTO;
    }

    //Guardar Modulo
    public CtrModuloDTO guardarModulo(CtrModuloVO moduloVO) {

        try {
            // Buscar el usuario en la base de datos

            CtrModulo modulo = new CtrModulo();
            BeanUtils.copyProperties(moduloVO, modulo);
            modulo.setModNombre(moduloVO.getModNombre());
            modulo.setModIcono(moduloVO.getModIcono());

            // Guardar la nueva entidad en la base de datos
            modulo = ctrModuloRepository.save(modulo);
            return toModuloDTO(modulo);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actulizar Modulo
    public CtrModuloDTO actulizarModulo(CtrModuloUpdateVO moduloVO) {

        try {
            // Buscar el modulo en la base de datos
            CtrModulo modulo = ctrModuloRepository.findById(moduloVO.getModId())
                    .orElseThrow(() -> new IllegalArgumentException("El modulo con ID " + moduloVO.getModId() + " no existe"));

            BeanUtils.copyProperties(moduloVO, modulo);
            modulo.setModNombre(moduloVO.getModNombre());
            modulo.setModIcono(moduloVO.getModIcono());

            // Guardar la nueva entidad en la base de datos
            modulo = ctrModuloRepository.save(modulo);
            return toModuloDTO(modulo);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void eliminarModule(Long id){
        ctrModuloRepository.deleteById(id);
    }

}
