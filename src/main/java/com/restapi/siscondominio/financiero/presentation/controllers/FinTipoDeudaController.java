package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinTipoDeudaDTO;
import com.restapi.siscondominio.financiero.business.services.FinTipoDeudaService;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/tipodeuda")
public class FinTipoDeudaController {

    @Autowired
    private FinTipoDeudaService finTipoDeudaService;

    @PostMapping
    public String save(@Valid @RequestBody FinTipoDeudaVO vO) {
        return finTipoDeudaService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finTipoDeudaService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinTipoDeudaUpdateVO vO) {
        finTipoDeudaService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinTipoDeudaDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finTipoDeudaService.getById(id);
    }

    @GetMapping
    public Page<FinTipoDeudaDTO> query(@Valid FinTipoDeudaQueryVO vO) {
        return finTipoDeudaService.query(vO);
    }
}
