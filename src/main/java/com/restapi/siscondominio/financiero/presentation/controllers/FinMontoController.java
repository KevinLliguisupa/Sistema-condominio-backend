package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.services.FinMontoService;
import com.restapi.siscondominio.financiero.business.vo.FinMontoVO;
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
@RequestMapping("/financiero/cobros/montos")
public class FinMontoController {

    @Autowired
    private FinMontoService finMontoService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean pagination,
            @RequestParam(required = false, defaultValue = "10")    Integer size,
            @RequestParam(required = false, defaultValue = "0")     Integer page) {
        try {
            if(pagination){
            return ResponseEntity.status(HttpStatus.OK).body(finMontoService.getAll(size, page));
            } else {
            return ResponseEntity.status(HttpStatus.OK).body(finMontoService.getAll());
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(finMontoService.getById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<Object> getActivos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(finMontoService.getActivos());
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activos/{id}")
    public ResponseEntity<Object> getActivosByTipo(@Valid @NotNull @PathVariable("id") Long tdeId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(finMontoService.getActivosByTipo(tdeId));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody FinMontoVO requestBody) {
        try {
            return ResponseHandler.generateResponse("¡Valor creado correctamente!",
                    HttpStatus.CREATED, finMontoService.save(requestBody));
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Error no fue posible crear el recurso!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
