package com.krugercorp.vacunacion.repositories;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.krugercorp.vacunacion.models.EmpleadoModel;


@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoModel, Long>{
    public abstract ArrayList<EmpleadoModel> findByCedula(String cedula);
    public abstract ArrayList<EmpleadoModel> findByUsuario(String usuario);
    public abstract ArrayList<EmpleadoModel> findByVacunado(Boolean vacunado);

    @Query("select e from empleado e "
    + "join e.vacunas v "
    + "join v.tipo t "
    + "where t.id =:id ")
    public abstract ArrayList<EmpleadoModel> findByTipovacuna(@Param("id") Long Id);

    @Query("select e from empleado e "
    + "join e.vacunas v "
    + "where v.fecha_vacunacion between :desde and :hasta ")
    public abstract ArrayList<EmpleadoModel> findByFecha(@Param("desde") Date desde, @Param("hasta") Date hasta);
}
