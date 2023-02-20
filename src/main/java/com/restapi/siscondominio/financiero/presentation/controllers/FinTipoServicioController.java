package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinTipoServicioDTO;
import com.restapi.siscondominio.financiero.business.services.FinTipoServicioService;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero/tiposervicio")
public class FinTipoServicioController {

    @Autowired
    private FinTipoServicioService finTipoServicioService;

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody FinTipoServicioVO vO) {
        try{
            String response=finTipoServicioService.save(vO).toString();
            if(response!=null){
                return new ResponseEntity<String>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            String response=null;
            return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinTipoServicioUpdateVO vO) {

        try{
            finTipoServicioService.update(id, vO);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<FinTipoServicioDTO> getById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            FinTipoServicioDTO finTipoServicioDTO= finTipoServicioService.getById(id);
            return new ResponseEntity<FinTipoServicioDTO>(finTipoServicioDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<FinTipoServicioDTO>(new FinTipoServicioDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public List<FinTipoServicioDTO> query(@Valid FinTipoServicioQueryVO vO) {
        return finTipoServicioService.query();
    }

}

