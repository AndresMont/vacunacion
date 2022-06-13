package com.krugercorp.vacunacion.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="tipo")
@Table(name="tipo_vacuna")
public class TipoVacunaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    @NotNull(message = "El nombre no puede ser nulo.")
    private String nombre;  

    @JsonIgnore
	@OneToMany(targetEntity = VacunaModel.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="tipo_id", referencedColumnName = "id")
    private List<VacunaModel> vacuna;    

    public TipoVacunaModel(){
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
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return List<VacunaModel> return the vacuna
     */
    public List<VacunaModel> getVacuna() {
        return vacuna;
    }

    /**
     * @param vacuna the vacuna to set
     */
    public void setVacuna(List<VacunaModel> vacuna) {
        this.vacuna = vacuna;
    }

}