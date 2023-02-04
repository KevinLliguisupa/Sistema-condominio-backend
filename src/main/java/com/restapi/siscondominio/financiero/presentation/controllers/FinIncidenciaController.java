package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinIncidenciaDTO;
import com.restapi.siscondominio.financiero.business.services.FinIncidenciaService;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaSolucionarVO;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinIncidenciaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/financiero/incidencia")
public class FinIncidenciaController {

    @Autowired
    private FinIncidenciaService finIncidenciaService;

    @PostMapping
    public String save(@Valid @RequestBody FinIncidenciaVO vO) {
        return finIncidenciaService.save(vO).toString();
    }

    //@DeleteMapping("/{id}")
    //public void delete(@Valid @NotNull @PathVariable("id") Long id) {
    //    finIncidenciaService.delete(id);
    //}

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinIncidenciaUpdateVO vO) {
        finIncidenciaService.update(id, vO);
    }

    @PutMapping("/solucion/{id}")
    public void solution(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinIncidenciaSolucionarVO vO) {
        finIncidenciaService.solution(id, vO);
    }

    @GetMapping("/{id}")
    public FinIncidenciaDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finIncidenciaService.getById(id);
    }

    @GetMapping
    public List<FinIncidenciaDTO> query() {
        return finIncidenciaService.query();
    }

    @GetMapping("/solucionadas")
    public List<FinIncidenciaDTO> incidentsFixed() {
        return finIncidenciaService.incidentsFixed();
    }
    @GetMapping("nosolucionadas")
    public List<FinIncidenciaDTO> incidentsNotFixed() {
        return finIncidenciaService.incidentsNotFixed();
    }
}
