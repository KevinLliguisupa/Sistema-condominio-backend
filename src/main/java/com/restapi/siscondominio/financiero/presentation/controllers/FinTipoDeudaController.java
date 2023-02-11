package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.services.FinTipoDeudaService;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaVO;
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
@RequestMapping("/financiero/cobros/tipos-deuda")
public class FinTipoDeudaController {

    @Autowired
    private FinTipoDeudaService finTipoDeudaService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean pagination,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        try {
            if(pagination){
            return ResponseEntity.status(HttpStatus.OK).body(finTipoDeudaService.getAll(size, page));
            } else {
            return ResponseEntity.status(HttpStatus.OK).body(finTipoDeudaService.getAll());
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(finTipoDeudaService.getById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody FinTipoDeudaVO body) {
        try {
            return ResponseHandler.generateResponse("¡Parametro creado correctamente!",
                    HttpStatus.CREATED, finTipoDeudaService.save(body));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Error no fue posible crear el recurso!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinTipoDeudaVO body) {
        try {
            return ResponseHandler.generateResponse("¡Parametro actualizado correctamente!",
                    HttpStatus.CREATED, finTipoDeudaService.update(id, body));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Error no fue posible actualizar el recurso!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
