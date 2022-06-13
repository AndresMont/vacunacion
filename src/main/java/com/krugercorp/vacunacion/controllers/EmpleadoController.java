package com.krugercorp.vacunacion.controllers;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.vacunacion.models.EmpleadoModel;
import com.krugercorp.vacunacion.services.EmpleadoService;
import com.krugercorp.vacunacion.utils.InvalidDataException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping()
    @ApiOperation("Lista todos los empleados.")
    @ApiResponse(code=200, message="OK")
    public ArrayList<EmpleadoModel> ListarEmpleados(){
        return empleadoService.ListarEmpleados();
    }

    @GetMapping("/listarporestado")
    @ApiOperation("Lista los empleados según su estado de vacunación.")
    @ApiResponse(code=200, message="OK")
    public ArrayList<EmpleadoModel> ListarEmpleadosPorEstado(@RequestParam("vacunado") boolean vacunado){
        return empleadoService.ListarEmpleadosPorEstado(vacunado);
    }

    @GetMapping("/listarporvacuna")
    @ApiOperation("Lista los empleados según su tipo de vacuna.")
    @ApiResponse(code=200, message="OK")
    public ArrayList<EmpleadoModel> ListarEmpleadosPorTipoVacuna(@RequestParam("id") Long id){
        return empleadoService.ListarEmpleadosPorTipoVacuna(id);
    }

    @GetMapping("/listarporfecha")
    @ApiOperation("Lista los empleados según su fecha de vacunación en el rango solicitado.")
    @ApiResponse(code=200, message="OK")
    public ArrayList<EmpleadoModel> ListarEmpleadosPorFechas(@RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("hasta") Date hasta){
        System.out.println(desde);
        System.out.println(hasta);
        return empleadoService.ListarEmpleadosPorFechas(desde,hasta);   

    }  

    @PostMapping
    @ApiOperation("Inserta o acualiza un empleado.")
    @ApiResponse(code=201, message="OK")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoModel GuardarEmpleado(@ApiParam(value="Id del empleado.", required =false) @Valid @RequestBody EmpleadoModel empleado, BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return this.empleadoService.GuardarEmpleado(empleado);        
    }

    @DeleteMapping(path = "{id}")
    @PostMapping
    @ApiOperation("Elimina un empleado en cascada.")
    @ApiResponse(code=200, message="Eliminado")
    public String EliminarEmpleado(@PathVariable("id") Long id){
        if(this.empleadoService.EliminarEmpleado(id)){
            return ("Eliminado");
        }else{
            throw new IllegalArgumentException("No se pudo eliminar el empleado.");
        }

    }
}