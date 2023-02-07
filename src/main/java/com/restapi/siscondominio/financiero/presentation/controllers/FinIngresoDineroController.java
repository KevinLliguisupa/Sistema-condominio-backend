package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinIngresosDineroDTO;
import com.restapi.siscondominio.financiero.business.dto.FinTipoServicioDTO;
import com.restapi.siscondominio.financiero.business.services.FinIngresosDineroService;
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
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero/ingresodinero")
public class FinIngresoDineroController {

    @Autowired
    private FinIngresosDineroService finIngresosDineroService;


    @GetMapping
    public List<FinIngresosDineroDTO> findAllIngresos() {
            return finIngresosDineroService.findAllIngresos();

    }

}

