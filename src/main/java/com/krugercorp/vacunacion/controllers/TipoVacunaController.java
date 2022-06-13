package com.krugercorp.vacunacion.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.vacunacion.models.TipoVacunaModel;
import com.krugercorp.vacunacion.services.TipoVacunaService;
import com.krugercorp.vacunacion.utils.InvalidDataException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/tipovacunas")
public class TipoVacunaController {
    @Autowired
    TipoVacunaService tipovacunaService;

    @GetMapping()
    @ApiOperation("Lista todos los tipo de vacunas.")
    @ApiResponse(code=200, message="OK")
    public ArrayList<TipoVacunaModel> ListarTipoVacunas(){
        return tipovacunaService.ListarTipoVacunas();
    }

    @PostMapping
    @ApiOperation("Inserta o acualiza un tipo de vacuna.")
    @ApiResponse(code=201, message="OK")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoVacunaModel GuardarTipoVacuna(@ApiParam(value="Id del tipo de vacuna.", required =false) @Valid @RequestBody TipoVacunaModel tipovacuna, BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return this.tipovacunaService.GuardarTipoVacuna(tipovacuna);        
    }

    @DeleteMapping(path = "{id}")
    @PostMapping
    @ApiOperation("Elimina un tipo de vacuna.")
    @ApiResponse(code=200, message="Eliminado")
    public String EliminarTipoVacuna(@PathVariable("id") Long id){
        if(this.tipovacunaService.EliminarTipoVacuna(id)){
            return ("Eliminado");
        }else{
            throw new IllegalArgumentException("No se pudo eliminar el tipo de vacuna.");
        }

    }
}