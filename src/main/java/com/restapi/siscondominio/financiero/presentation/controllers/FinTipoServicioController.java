package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinTipoServicioDTO;
import com.restapi.siscondominio.financiero.business.services.FinTipoServicioService;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/tiposervicio")
public class FinTipoServicioController {

    @Autowired
    private FinTipoServicioService finTipoServicioService;

    @PostMapping
    public String save(@Valid @RequestBody FinTipoServicioVO vO) {
        return finTipoServicioService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finTipoServicioService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinTipoServicioUpdateVO vO) {
        finTipoServicioService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinTipoServicioDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finTipoServicioService.getById(id);
    }

    @GetMapping
    public Page<FinTipoServicioDTO> query(@Valid FinTipoServicioQueryVO vO) {
        return finTipoServicioService.query(vO);
    }
}
