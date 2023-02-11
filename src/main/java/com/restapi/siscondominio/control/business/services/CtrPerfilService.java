package com.restapi.siscondominio.control.business.services;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrModuloDTO;
import com.restapi.siscondominio.control.business.dto.CtrPerfilDTO;
import com.restapi.siscondominio.control.business.vo.CtrModuloVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilVO;
import com.restapi.siscondominio.control.persistence.entities.CtrModulo;
import com.restapi.siscondominio.control.persistence.entities.CtrPerfil;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrModuloRepository;
import com.restapi.siscondominio.control.persistence.repositories.CtrPerfilRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CtrPerfilService {

    @Autowired
    private CtrPerfilRepository ctrPerfilRepository;
    @Autowired
    private CtrModuloRepository ctrModuloRepository;



    public void delete(Long id) {
        ctrPerfilRepository.deleteById(id);
    }

    public List<CtrPerfilDTO> findAll() {
        return ctrPerfilRepository.findAll().stream().map(this::toPerfilDTO).collect(Collectors.toList());
    }

    private CtrPerfilDTO toPerfilDTO (CtrPerfil ctrPerfil){
        CtrPerfilDTO ctrPerfilDTO = new CtrPerfilDTO();

        ctrPerfilDTO.setPrfId(ctrPerfil.getPrfId());
        ctrPerfilDTO.setPrfNombre(ctrPerfil.getPrfNombre());
        ctrPerfilDTO.setPrfRutaAcceso(ctrPerfil.getPrfRutaAcceso());
        ctrPerfilDTO.setModId(ctrPerfil.getMod().getModId());
        return ctrPerfilDTO;

    }

    //Guardar Perfil
    public CtrPerfilDTO guardarPerfil(CtrPerfilVO perfilVO) {

        try {
            // Buscar el perfil en la base de datos
            CtrModulo modulo = ctrModuloRepository.findById(perfilVO.getModId())
                    .orElseThrow(() -> new IllegalArgumentException("El módulo con ID " + perfilVO.getModId()+ " no existe"));
            // Crear una nueva entidad CtrAnuncio
            CtrPerfil perfil = new CtrPerfil();
            BeanUtils.copyProperties(perfilVO, perfil);

            perfil.setPrfNombre(perfilVO.getPrfNombre());
            perfil.setPrfRutaAcceso(perfilVO.getPrfRutaAcceso());
            perfil.setMod(modulo);

            // Guardar la nueva entidad en la base de datos
            perfil = ctrPerfilRepository.save(perfil);
            return toPerfilDTO(perfil);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Actualizar Perfil
    public CtrPerfilDTO actualizarPerfil(CtrPerfilUpdateVO perfilVO) {

        try {
            // Buscar el perfil en la base de datos
            CtrPerfil perfil = ctrPerfilRepository.findById(perfilVO.getPrfId())
                    .orElseThrow(() -> new IllegalArgumentException("El módulo con ID " + perfilVO.getPrfId()+ " no existe"));
            // Crear una nueva entidad CtrAnuncio

            BeanUtils.copyProperties(perfilVO, perfil);

            perfil.setPrfNombre(perfilVO.getPrfNombre());
            perfil.setPrfRutaAcceso(perfilVO.getPrfRutaAcceso());


            // Guardar la nueva entidad en la base de datos
            perfil = ctrPerfilRepository.save(perfil);
            return toPerfilDTO(perfil);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //Buscar por Id
    public CtrPerfilDTO findById(Long id) {
        return toPerfilDTO(ctrPerfilRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el perfil con id: " + id)));
    }
}
