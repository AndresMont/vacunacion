package com.krugercorp.vacunacion.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.krugercorp.vacunacion.models.TipoVacunaModel;
import com.krugercorp.vacunacion.repositories.TipoVacunaRepository;

@Service
public class TipoVacunaService {
    @Autowired
    TipoVacunaRepository tipoVacunaRepository;

    public ArrayList<TipoVacunaModel> ListarTipoVacunas(){
        return (ArrayList<TipoVacunaModel>) tipoVacunaRepository.findAll();     
    }

    public TipoVacunaModel GuardarTipoVacuna(TipoVacunaModel tipovacuna){
        //Validar duplicidad de nombre
        if (tipoVacunaRepository.findByNombre(tipovacuna.getNombre()).size()>0) {
            throw new DuplicateKeyException("Ya existe un tipo de vacuna con el nombre: " + tipovacuna.getNombre());
        }        
        return tipoVacunaRepository.save(tipovacuna);
    }

    public boolean EliminarTipoVacuna(Long id){
        try {
            tipoVacunaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
