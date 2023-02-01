package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinGastoDTO;
import com.restapi.siscondominio.financiero.business.services.FinGastoService;
import com.restapi.siscondominio.financiero.business.vo.FinGastoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/gasto")
public class FinGastoController {

    @Autowired
    private FinGastoService finGastoService;

    @PostMapping
    public String save(@Valid @RequestBody FinGastoVO vO) {
        return finGastoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finGastoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinGastoUpdateVO vO) {
        finGastoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinGastoDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finGastoService.getById(id);
    }

    @GetMapping
    public Page<FinGastoDTO> query(@Valid FinGastoQueryVO vO) {
        return finGastoService.query(vO);
    }
}
