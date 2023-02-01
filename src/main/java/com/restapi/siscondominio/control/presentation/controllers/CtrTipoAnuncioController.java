package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrTipoAnuncioDTO;
import com.restapi.siscondominio.control.business.services.CtrTipoAnuncioService;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/tipoanuncio")
public class CtrTipoAnuncioController {

    @Autowired
    private CtrTipoAnuncioService ctrTipoAnuncioService;

    @PostMapping
    public String save(@Valid @RequestBody CtrTipoAnuncioVO vO) {
        return ctrTipoAnuncioService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrTipoAnuncioService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrTipoAnuncioUpdateVO vO) {
        ctrTipoAnuncioService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrTipoAnuncioDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrTipoAnuncioService.getById(id);
    }

    @GetMapping
    public Page<CtrTipoAnuncioDTO> query(@Valid CtrTipoAnuncioQueryVO vO) {
        return ctrTipoAnuncioService.query(vO);
    }
}
