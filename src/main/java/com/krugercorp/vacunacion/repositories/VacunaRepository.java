package com.krugercorp.vacunacion.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.krugercorp.vacunacion.models.VacunaModel;

@Repository
public interface VacunaRepository extends CrudRepository<VacunaModel, Long>{
    public abstract ArrayList<VacunaModel> findByEmpleado(Long empleado_id);
}