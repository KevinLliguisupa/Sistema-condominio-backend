package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaDTO;
import com.restapi.siscondominio.financiero.business.dto.FinDeudaInfoDTO;
import com.restapi.siscondominio.financiero.business.speficications.FinDeudaSpecification;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinDeudaService extends Servicio<FinDeuda, FinDeudaDTO>{

    @Autowired
    private FinDeudaRepository finDeudaRepository;
    @Autowired
    private FinMontoService finMontoService;
    @Autowired
    private FinDeudaPagoService finDeudaPagoService;

    public Page<FinDeudaDTO> getAll(@NotNull Integer size, Integer page) {
        Sort byFecha = Sort.by("deuFechaCorte").descending();
        Sort sorter = Sort.by("deuCancelado").and(byFecha);
        return toPageDTO(finDeudaRepository.findAll(PageRequest.of(page, size, sorter)));
    }

    public List<FinDeudaDTO> getAll() {
        Sort byFecha = Sort.by("deuFechaCorte").descending();
        Sort sorter = Sort.by("deuCancelado").and(byFecha);
        return toListDTO(finDeudaRepository.findAll(sorter));
    }

    public Page<FinDeudaDTO> getByUser(@NotNull String cedulaUsuario, Integer size, Integer page) {
        Specification<FinDeuda> hasUser = Specification.where(FinDeudaSpecification.hasUser(cedulaUsuario));
        Sort byFecha = Sort.by("deuFechaCorte").descending();
        Sort sorter = Sort.by("deuCancelado").and(byFecha);

        return toPageDTO(finDeudaRepository.findAll(hasUser, PageRequest.of(page, size, sorter)));
    }

    public List<FinDeudaDTO>  getByUser(String cedulaUsuario) {
        Specification<FinDeuda> hasUser = Specification.where(FinDeudaSpecification.hasUser(cedulaUsuario));
        Sort byFecha = Sort.by("deuFechaCorte").descending();
        Sort sorter = Sort.by("deuCancelado").and(byFecha);

        return toListDTO(finDeudaRepository.findAll(hasUser, sorter));
    }

    public FinDeudaInfoDTO getById(Long id) {
        FinDeuda original = requireOne(id);
        return toInfoDTO(original);
    }

    public FinDeudaDTO toDTO(FinDeuda original) {
        FinDeudaDTO bean = new FinDeudaDTO();
        BeanUtils.copyProperties(original, bean);
        bean.setMonto(finMontoService.getById(original.getMonto().getMonId()));
        bean.setUsuCedula(original.getUsuCedula().getUsuCedula());
        return bean;
    }

    public FinDeudaInfoDTO toInfoDTO(FinDeuda original) {
        FinDeudaInfoDTO bean = new FinDeudaInfoDTO();
        BeanUtils.copyProperties(original, bean);
        bean.setMonto(finMontoService.getById(original.getMonto().getMonId()));
        bean.setUsuCedula(original.getUsuCedula().getUsuCedula());
        bean.setPagos(finDeudaPagoService.toListDTO(original.getFinDeudaPagos()));
        return bean;
    }

    private FinDeuda requireOne(Long id) {
        return finDeudaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
