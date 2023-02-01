package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinMontoDTO;
import com.restapi.siscondominio.financiero.business.services.FinMontoService;
import com.restapi.siscondominio.financiero.business.vo.FinMontoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinMontoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinMontoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/monto")
public class FinMontoController {

    @Autowired
    private FinMontoService finMontoService;

    @PostMapping
    public String save(@Valid @RequestBody FinMontoVO vO) {
        return finMontoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finMontoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinMontoUpdateVO vO) {
        finMontoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinMontoDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finMontoService.getById(id);
    }

    @GetMapping
    public Page<FinMontoDTO> query(@Valid FinMontoQueryVO vO) {
        return finMontoService.query(vO);
    }
}
