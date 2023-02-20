package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.exeption.DuplicatedException;
import com.restapi.siscondominio.control.business.services.CtrUsuarioService;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioVO;
import com.restapi.siscondominio.control.presentation.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**

 * esta clase contiene los controladores de usuario
 * @author: dilanGhost33
 * @version: 2023
 * @see: uses dto and vo of user
 */

@Validated
@RestController
@RequestMapping("/control/usuario")
public class CtrUsuarioController {


    @Autowired
    private CtrUsuarioService ctrUsuarioService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrUsuarioVO vO) {
        try {
            System.out.println(vO.getUsuCedula());
            return ResponseEntity.status(HttpStatus.CREATED).body(ctrUsuarioService.save(vO));
        } catch (DuplicatedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/all")
    public ResponseEntity<Object> getUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination,
            @RequestParam(required = false) Boolean usrState) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ctrUsuarioService.getCtrUsuarios(page, size, enablePagination));
        }catch (Exception e){
            return  ResponseHandler.generateResponse("Informacion no entontrada"+e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //@DeleteMapping("/{id}")
    //public void delete(@Valid @NotNull @PathVariable("id") String id) {
    //  ctrUsuarioService.delete(id);
    //}

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody CtrUsuarioUpdateVO vO) {
        try {

            return ResponseHandler.generateResponse("Parametros actualizado",
                    HttpStatus.CREATED, ctrUsuarioService.update(id, vO)) ;
        }catch (Exception e){
            return ResponseHandler.generateResponse("¡Error no fue posible actualizar el recurso!",
                    HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@Valid @NotNull @PathVariable("id") String id) {
        try{

            return ResponseHandler.generateResponse("Encontrado  "+id,
                    HttpStatus.CREATED,ctrUsuarioService.getById(id));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/state/{id}")
    public ResponseEntity<Object> changeState(@Valid @NotNull @PathVariable("id") String id) {
        try{
            ;
            return ResponseHandler.generateResponse("Parametro actualizado",
                    HttpStatus.CREATED,ctrUsuarioService.changeState(id)) ;
        }catch (Exception e){
            return ResponseHandler.generateResponse("¡Error no fue posible actualizar el recurso!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }










}
