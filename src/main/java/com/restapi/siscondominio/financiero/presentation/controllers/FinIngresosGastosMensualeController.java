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
    @GetMapping("/gastos")
    public List<FinIngresosGastosMensualesDTO> findAllOutputs() {
        return  finIngresosGastosMensualesService.findAllOutputs();
    }
    @GetMapping("/ingresosvalor")
    public BigDecimal InputsValueTotal() {
        return  finIngresosGastosMensualesService.InputsValueTotal();
    }
    @GetMapping("/gastosvalor")
    public BigDecimal OutputsValueTotal() {
        return  finIngresosGastosMensualesService.OutputsValueTotal();
    }
    @GetMapping("/saldo")
    public BigDecimal BalanceValueTotal() {
        return  finIngresosGastosMensualesService.BalanceValueTotal();
    }

}


