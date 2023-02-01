package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaDTO;
import com.restapi.siscondominio.financiero.business.services.FinDeudaService;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/deuda")
public class FinDeudaController {

    @Autowired
    private FinDeudaService finDeudaService;

    @PostMapping
    public String save(@Valid @RequestBody FinDeudaVO vO) {
        return finDeudaService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finDeudaService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinDeudaUpdateVO vO) {
        finDeudaService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinDeudaDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finDeudaService.getById(id);
    }

    @GetMapping
    public Page<FinDeudaDTO> query(@Valid FinDeudaQueryVO vO) {
        return finDeudaService.query(vO);
    }
}
