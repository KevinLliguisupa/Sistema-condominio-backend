package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinPagoDTO;
import com.restapi.siscondominio.financiero.business.services.FinPagoService;
import com.restapi.siscondominio.financiero.business.vo.FinPagoVO;
import com.restapi.siscondominio.financiero.presentation.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/financiero/cobros/pagos")
public class FinPagoController {

    @Autowired
    private FinPagoService finPagoService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean pagination,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        try {
            if (pagination) {
                return ResponseEntity.status(HttpStatus.OK).body(finPagoService.getAll(size, page));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(finPagoService.getAll());
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/diferidos/{inqId}")
    public ResponseEntity<Object> save(@Valid @NotNull @PathVariable("inqId") String inqId, @RequestBody FinPagoVO requestBody) {
        try {
            return ResponseHandler.generateResponse("¡Pago creado correctamente!",
                    HttpStatus.CREATED, finPagoService.crearPago(inqId, requestBody));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public FinPagoDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finPagoService.getById(id);
    }
}










