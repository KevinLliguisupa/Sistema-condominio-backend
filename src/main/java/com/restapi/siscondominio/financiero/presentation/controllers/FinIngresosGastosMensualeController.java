package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinIngresosGastosMensualesDTO;
import com.restapi.siscondominio.financiero.business.dto.FinTipoServicioDTO;
import com.restapi.siscondominio.financiero.business.services.FinIngresosGastosMensualesService;
import com.restapi.siscondominio.financiero.business.services.FinTipoServicioService;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero")
public class FinIngresosGastosMensualeController {

    @Autowired
    private FinIngresosGastosMensualesService finIngresosGastosMensualesService;

    @GetMapping("/ingresos")
    public List<FinIngresosGastosMensualesDTO> findAllInputs() {
        return  finIngresosGastosMensualesService.findAllInputs();
    }
    @GetMapping("/gastosincidencias")
    public List<FinIngresosGastosMensualesDTO> findAllOutputsIncidents() {
        return  finIngresosGastosMensualesService.findAllOutputsIncidents();
    }
    @GetMapping("/gastosservicios")
    public List<FinIngresosGastosMensualesDTO> findAllOutputsServices() {
        return  finIngresosGastosMensualesService.findAllOutputsServices();
    }
    @GetMapping("/ingresosvalor")
    public BigDecimal InputsValueTotal() {
        return  finIngresosGastosMensualesService.InputsValueTotal();
    }
    @GetMapping("/gastosincidenciasvalor")
    public BigDecimal OutputsIncidentsValueTotal() {
        return  finIngresosGastosMensualesService.OutputsIncidentsValueTotal();
    }
    @GetMapping("/gastosserviciosvalor")
    public BigDecimal OutputsServicesValueTotal() {
        return  finIngresosGastosMensualesService.OutputsServicesValueTotal();
    }
    @GetMapping("/saldo")
    public BigDecimal BalanceValueTotal() {
        return  finIngresosGastosMensualesService.BalanceValueTotal();
    }

}


