package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinPagoServiciosDTO;
import com.restapi.siscondominio.financiero.business.services.FinPagoServiciosService;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero/pagoservicios")
public class FinPagoServiciosController {

    @Autowired
    private FinPagoServiciosService finPagoServiciosService;

    @PostMapping
    public String save(@Valid @RequestBody FinPagoServiciosVO vO) {
        return finPagoServiciosService.save(vO).toString();
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinPagoServiciosUpdateVO vO) {
        finPagoServiciosService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FinPagoServiciosDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finPagoServiciosService.getById(id);
    }

    @GetMapping
    public List<FinPagoServiciosDTO> query() {
        return finPagoServiciosService.query();
    }
}
