package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.services.FinDeudaService;
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
@RequestMapping("/financiero/cobros/deudas")
public class FinDeudaController {

    @Autowired
    private FinDeudaService finDeudaService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "false") Boolean pagination,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        try {
            if (pagination) {
                return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getAll(size, page));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getAll());
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object> getByUsuario(
            @Valid @NotNull @PathVariable("id") String cedulaUsuario,
            @RequestParam(required = false, defaultValue = "false") Boolean pagination,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        try {
            if (pagination) {
                return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getByUser(cedulaUsuario, size, page));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getByUser(cedulaUsuario));
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("usuario/{id}/pendientes")
    public ResponseEntity<Object> getDeudasByUsuario(
            @Valid @NotNull @PathVariable("id") String cedulaUsuario,
            @RequestParam(required = false, defaultValue = "false") Boolean pagination,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "0") Integer page) {
        try {
            if (pagination) {
                return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getDeudasByInquilino(cedulaUsuario, size, page));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getDeudasByInquilino(cedulaUsuario));
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse("¡Información no encontrada!",
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(finDeudaService.getById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }
}
