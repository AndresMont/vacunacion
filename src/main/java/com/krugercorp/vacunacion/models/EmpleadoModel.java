package com.krugercorp.vacunacion.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Date;
import java.util.List;

@Entity(name="empleado")
@Table(name="empleado", uniqueConstraints = {@UniqueConstraint(name = "uq_cedula", columnNames = { "cedula"})
,@UniqueConstraint(name = "uq_usuario", columnNames = { "usuario"})})
public class EmpleadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Column(nullable = false, length = 20)
    @NotNull(message = "La cédula no puede ser nula.")
    @Size(min = 10, max=10, message = "La cédula debe tener 10 dígitos.")
    private String cedula;
    @Column(nullable = false, length = 100)
    @NotNull(message = "Los nombres no pueden ser nulos.")
    private String nombres;
    @Column(nullable = false, length = 100)
    @NotNull(message = "Los apellidos no pueden ser nulos.")
    private String apellidos;
    @Column(nullable = false, length = 100)
    @NotNull(message = "El correo electrónico no puede ser nulo.")
    private String correo_electronico;
    @Column(nullable = false, length = 100)
    private String usuario;
    @Column(nullable = false, length = 1000)
    private String contrasena;
    private Date fecha_nacimiento;
    @Column(length = 1000)
    private String direccion;
    @Column(length = 100)
    private String telefono;
    @Column(nullable = false, columnDefinition="boolean default false")
    private Boolean vacunado = false;
    @Column(nullable = false, columnDefinition="boolean default false")
    private Boolean administrador = false;
    
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="empleado_id", referencedColumnName = "id")
    private List<VacunaModel> vacunas;

    public EmpleadoModel(){

    }

    public EmpleadoModel(String cedula){
        this.cedula = cedula;
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
     * @return String return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return String return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return String return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return String return the correo_electronico
     */
    public String getCorreo_electronico() {
        return correo_electronico;
    }

    /**
     * @param correo_electronico the correo_electronico to set
     */
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    /**
     * @return String return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return String return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return Date return the fecha_nacimiento
     */
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * @param fecha_nacimiento the fecha_nacimiento to set
     */
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * @return String return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return String return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return Boolean return the vacunado
     */
    public Boolean isVacunado() {
        return vacunado;
    }

    /**
     * @param vacunado the vacunado to set
     */
    public void setVacunado(Boolean vacunado) {
        this.vacunado = vacunado;
    }

    /**
     * @return Boolean return the administrador
     */
    public Boolean isAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }   


    /**
     * @return List<VacunaModel> return the vacunas
     */
    public List<VacunaModel> getVacunas() {
        return vacunas;
    }

    /**
     * @param vacunas the vacunas to set
     */
    public void setVacunas(List<VacunaModel> vacunas) {
        this.vacunas = vacunas;
    }

}