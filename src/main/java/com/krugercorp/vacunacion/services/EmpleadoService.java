package com.krugercorp.vacunacion.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.krugercorp.vacunacion.models.EmpleadoModel;
import com.krugercorp.vacunacion.models.TipoVacunaModel;
import com.krugercorp.vacunacion.models.VacunaModel;
import com.krugercorp.vacunacion.repositories.EmpleadoRepository;
import com.krugercorp.vacunacion.repositories.TipoVacunaRepository;
import com.krugercorp.vacunacion.repositories.VacunaRepository;


@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    TipoVacunaRepository tipovacunaRepository;
    @Autowired
    VacunaRepository vacunaRepository;    

    public ArrayList<EmpleadoModel> ListarEmpleados(){
        return (ArrayList<EmpleadoModel>) empleadoRepository.findAll();     
    }

    public ArrayList<EmpleadoModel> ListarEmpleadosPorEstado(boolean vacunado){
        return (ArrayList<EmpleadoModel>) empleadoRepository.findByVacunado(vacunado);     
    }

    public ArrayList<EmpleadoModel> ListarEmpleadosPorTipoVacuna(Long id){
        return (ArrayList<EmpleadoModel>) empleadoRepository.findByTipovacuna(id);     
    }

    public ArrayList<EmpleadoModel> ListarEmpleadosPorFechas(Date desde, Date hasta){
        System.out.println(desde);
        System.out.println(hasta);
        return (ArrayList<EmpleadoModel>) empleadoRepository.findByFecha(desde, hasta);     
    }    
    

    public EmpleadoModel GuardarEmpleado(EmpleadoModel empleado){
        
        //Validar si cédula es numérica
        try {
            Integer.parseInt(empleado.getCedula());
        } catch (Exception e) {
             throw new IllegalArgumentException("La cédula debe ser numérica de 10 dígitos.");
        }
        
        //Validar duplicidad de cédula
        int size = empleadoRepository.findByCedula(empleado.getCedula()).size();
        if ((empleado.getId() == null && size>0) || (empleado.getId() != null && size>1)) {
            throw new DuplicateKeyException("Ya existe un empleado con la cédula: " + empleado.getCedula());
        }

        //Validar correo electrónico
        if(!ValidarCorreoElectronico(empleado.getCorreo_electronico())){
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }

        //Validar caracteres especiales
        if(ValidarCaracteresEspeciales(empleado.getNombres()) || ValidarCaracteresEspeciales(empleado.getApellidos())){
            throw new IllegalArgumentException("Los nombres y apellidos no pueden contener caracteres especiales.");
        }

        //Generar usuario y contraseña
        empleado = GenerarUsuarioContrasena(empleado);
        
        //Validación de estado vacunado y lista de vacunas
        if(empleado.isVacunado() && empleado.getVacunas().size()==0){
            throw new IllegalArgumentException("Empleado vacunado, ingrese la lista de vacunas.");
        }
        if(!empleado.isVacunado() && empleado.getVacunas().size()>0){
            empleado.setVacunado(true);
        }
        
        //Comprobar id de tipo de vacuna y establecer empleado
        for (VacunaModel vacuna : empleado.getVacunas()){

            vacuna.setEmpleado(empleado);
            
            Optional<TipoVacunaModel> tipo_vacuna= tipovacunaRepository.findById(vacuna.getTipo().getId());
            if(tipo_vacuna.isPresent()){
                System.out.println(tipo_vacuna.get());
                vacuna.setTipo(tipo_vacuna.get());
            }else{
                throw new IllegalArgumentException("No se puede encontrar el tipo de vacuna con id."+vacuna.getTipo().getId());
            }
        }
        
        return empleadoRepository.save(empleado);
    }

    public boolean EliminarEmpleado(Long id){
        try {
            empleadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EmpleadoModel GenerarUsuarioContrasena(EmpleadoModel empleado){
        
        if(empleado.getId() == null){
            if(empleado.getUsuario()==null){

                //Usuario generado a partir de la primer letra del primer nombre y el primer apellido.
                String strUsuario = empleado.getNombres().trim().substring(0,1).toLowerCase();
                strUsuario += empleado.getApellidos().split(" ")[0].toLowerCase();
                
                //Si el usuario ya existe se aumenta un numero secuencial al mismo
                int i=1;
                String strUsuarioFinal = strUsuario;
                while(empleadoRepository.findByUsuario(strUsuarioFinal).size()>0){
                    i++;
                    strUsuarioFinal = strUsuario + i;
                }

                empleado.setUsuario(strUsuarioFinal);
            }
            if(empleado.getContrasena()==null){

                //Contraseña en base al apellido y 4 dígitos aleatorios.
                String strContrasena = empleado.getApellidos().split(" ")[0].toLowerCase();
                Random random = new Random();
                strContrasena += String.format("%04d", random.nextInt(10000));
                
                //TODO: Enviar al correo electrónico el usuario y contraseña sin encriptar

                empleado.setContrasena(strContrasena);
            }
        }else{
            Optional<EmpleadoModel> emp = empleadoRepository.findById(empleado.getId());
            if(empleado.getUsuario() == null){
                empleado.setUsuario(emp.get().getUsuario());
            }
            if(empleado.getContrasena() == null){
                empleado.setContrasena(emp.get().getContrasena());
            }
        }

        return empleado;
    }

    public boolean ValidarCorreoElectronico(String correo){
        return Pattern.compile("^(.+)@(\\S+)$").matcher(correo).matches();
    }

    public boolean ValidarCaracteresEspeciales(String cadena){
        Pattern p = Pattern.compile("[^a-zñáéíóúÑÁÉÍÓÚÜü ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cadena);
        return m.find();
    }    
    

}
