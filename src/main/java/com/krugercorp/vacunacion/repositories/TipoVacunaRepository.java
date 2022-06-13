package com.krugercorp.vacunacion.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.krugercorp.vacunacion.models.TipoVacunaModel;

@Repository
public interface TipoVacunaRepository extends CrudRepository<TipoVacunaModel, Long>{
    public abstract ArrayList<TipoVacunaModel> findByNombre(String nombre);
}