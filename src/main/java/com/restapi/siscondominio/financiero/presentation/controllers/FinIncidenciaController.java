package com.restapi.siscondominio.financiero.presentation.controllers;

import com.restapi.siscondominio.financiero.business.dto.FinIncidenciaDTO;
import com.restapi.siscondominio.financiero.business.services.FinIncidenciaService;
import com.restapi.siscondominio.financiero.business.vo.*;
import com.restapi.siscondominio.financiero.persistence.documents.evidenciaIncidenciaSolucionadaDocuments;
import com.restapi.siscondominio.financiero.persistence.documents.evidenciaIncidenciasNoSolucionadasDocuments;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinIncidenciaUpdateVO vO) {
        finIncidenciaService.updateIncidentNoSolution(id, vO);
    }

    @PutMapping("/solucion/{id}")
    public void solution(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody FinIncidenciaSolucionarVO vO) {
        finIncidenciaService.solution(id, vO);
    }
    @PutMapping("/solucionada/{id}")
    public void solution(@Valid @NotNull @PathVariable("id") Long id,
                         @Valid @RequestBody FinIncidenciaSolucionadaUpdateVO vO) {
        finIncidenciaService.updateIndicenSolution(id, vO);
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

    @GetMapping("evidenciasnosolucionadas")
    public List<evidenciaIncidenciasNoSolucionadasDocuments>getAllEvidenciasNoSolucionadas () {
        return this.finIncidenciaService.getAllEvidenciasNoSolucionadas();
    }

    @GetMapping("evidenciassolucionadas")
    public List<evidenciaIncidenciaSolucionadaDocuments>getAllEvidenciasSolucionadas () {
        return this.finIncidenciaService.getAllEvidenciasSolucionadas();
    }


}
