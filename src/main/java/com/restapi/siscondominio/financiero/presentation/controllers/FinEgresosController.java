package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinEgresosDineroDTO;
import com.restapi.siscondominio.financiero.business.dto.FinIngresosDineroDTO;
import com.restapi.siscondominio.financiero.business.services.FinEgresosDineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Validated
@RestController
@RequestMapping("/financiero/egresodinero")
public class FinEgresosController {
    @Autowired
    private FinEgresosDineroService finEgresosDineroService;

    @GetMapping
    public List<FinEgresosDineroDTO> findAllEgresos() {
        return finEgresosDineroService.findAllEgresos();

    }
}
