package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinPagoDTO;
import com.restapi.siscondominio.financiero.business.services.FinPagoService;
import com.restapi.siscondominio.financiero.business.vo.FinPagoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/pago")
public class FinPagoController {

    @Autowired
    private FinPagoService finPagoService;

    @PostMapping
    public String save(@Valid @RequestBody FinPagoVO vO) {
        return finPagoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finPagoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinPagoUpdateVO vO) {
        finPagoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinPagoDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finPagoService.getById(id);
    }

    @GetMapping
    public Page<FinPagoDTO> query(@Valid FinPagoQueryVO vO) {
        return finPagoService.query(vO);
    }
}
