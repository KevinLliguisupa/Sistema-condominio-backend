package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinEgresosIncidenciasDTO;
import com.restapi.siscondominio.financiero.business.dto.FinEgresosServiciosDTO;
import com.restapi.siscondominio.financiero.business.services.FinEgresosServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero/reporteegresosservicios")
public class FinEgresosServiciosController {
    @Autowired
    private FinEgresosServiciosService finEgresosServiciosService;

    @GetMapping("/{anio}/{mes}")
    public List<FinEgresosServiciosDTO> query(
            @Valid @NotNull @PathVariable("anio") Integer anio,
            @Valid @NotNull @PathVariable("mes") Integer mes
    ){
        try{
            return  finEgresosServiciosService.findOutputsServices(anio,mes);

        }catch (Exception e){
            return new ArrayList<FinEgresosServiciosDTO>();
        }
    }
}
