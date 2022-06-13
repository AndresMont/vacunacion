package com.krugercorp.vacunacion.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="vacuna")
@Table(name="vacuna" , uniqueConstraints = {@UniqueConstraint(name = "uq_empleado_numero_dosis", columnNames = { "empleado_id", "numero_dosis" })})
public class VacunaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name="fk_empleado_vacuna"))
    private EmpleadoModel empleado;
    
    //@JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name="tipo_id", foreignKey = @ForeignKey(name = "fk_tipo_vacuna_vacuna"))
    private TipoVacunaModel tipo;

    @Column( nullable = false)
    private Date fecha_vacunacion;
    @Column( nullable = false)
    private int numero_dosis;

    public VacunaModel(){
        
    }
    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Date return the fecha_vacunacion
     */
    public Date getFecha_vacunacion() {
        return fecha_vacunacion;
    }

    /**
     * @param fecha_vacunacion the fecha_vacunacion to set
     */
    public void setFecha_vacunacion(Date fecha_vacunacion) {
        this.fecha_vacunacion = fecha_vacunacion;
    }

    /**
     * @return int return the numero_dosis
     */
    public int getNumero_dosis() {
        return numero_dosis;
    }

    /**
     * @param numero_dosis the numero_dosis to set
     */
    public void setNumero_dosis(int numero_dosis) {
        this.numero_dosis = numero_dosis;
    }

    /**
     * @return EmpleadoModel return the empleado
     */
    public EmpleadoModel getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadoModel empleado) {
        this.empleado = empleado;
    }

    /**
     * @return TipoVacunaModel return the tipo
     */
    public TipoVacunaModel getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoVacunaModel tipo) {
        this.tipo = tipo;
    }

}