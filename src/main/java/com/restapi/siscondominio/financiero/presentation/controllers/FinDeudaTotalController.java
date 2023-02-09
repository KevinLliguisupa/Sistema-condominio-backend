package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaTotalDTO;
import com.restapi.siscondominio.financiero.business.dto.FinEgresosIncidenciasDTO;
import com.restapi.siscondominio.financiero.business.services.FinDeudaTotalService;
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
@RequestMapping("/financiero/deudatotal")
public class FinDeudaTotalController {
    @Autowired
    private FinDeudaTotalService finDeudaTotalService;
    @GetMapping
    public FinDeudaTotalDTO calculateDebtTotal(){
        try{
            return  finDeudaTotalService.calculateDebtTotal();

        }catch (Exception e){
            return new FinDeudaTotalDTO();
        }
    }
}
