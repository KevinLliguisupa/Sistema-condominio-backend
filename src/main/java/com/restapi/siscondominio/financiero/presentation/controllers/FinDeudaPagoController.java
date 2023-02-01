package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaPagoDTO;
import com.restapi.siscondominio.financiero.business.services.FinDeudaPagoService;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaPagoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaPagoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinDeudaPagoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/deudapago")
public class FinDeudaPagoController {

    @Autowired
    private FinDeudaPagoService finDeudaPagoService;

    @PostMapping
    public String save(@Valid @RequestBody FinDeudaPagoVO vO) {
        return finDeudaPagoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        finDeudaPagoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinDeudaPagoUpdateVO vO) {
        finDeudaPagoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinDeudaPagoDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finDeudaPagoService.getById(id);
    }

    @GetMapping
    public Page<FinDeudaPagoDTO> query(@Valid FinDeudaPagoQueryVO vO) {
        return finDeudaPagoService.query(vO);
    }
}
