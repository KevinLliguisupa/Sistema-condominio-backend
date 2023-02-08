package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinEgresoDineroServiciosDTO;
import com.restapi.siscondominio.financiero.business.services.FinEgresoDineroServiciosService;
import com.restapi.siscondominio.financiero.business.services.FinPagoServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero/egresosserviciosdinero")
public class FinEgresoDineroServiciosController {
    @Autowired
    private FinEgresoDineroServiciosService finEgresoDineroServiciosService;

    @GetMapping
    public List<FinEgresoDineroServiciosDTO> findAllOutpustServices(){
        return finEgresoDineroServiciosService.findAllOutpustServices();
    }
}
