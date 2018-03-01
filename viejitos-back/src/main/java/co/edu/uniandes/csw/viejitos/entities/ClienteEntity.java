/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.eclipse.persistence.jpa.config.Cascade;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jj.silva
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
    
    private Integer estado;
    
    private String nombre;
    
    private Integer tipo;
    
    private String login;
    
    private String contrasena;
    
    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PodamExclude
    private List<ServicioEntity> servicios;
    
    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.LAZY)
    @PodamExclude
    private List<QuejaEntity> quejas;
    
    @OneToOne(fetch = FetchType.EAGER)
    @PodamExclude
    private CalificacionEntity calificacion;
    
    @OneToMany(fetch = FetchType.LAZY)
    @PodamExclude
    private List<CalificacionEntity> calificaciones;
    
    @OneToOne(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    @PodamExclude
    private CitaEntity cita;
    
    @ManyToMany(mappedBy = "clientes", fetch = FetchType.LAZY)
    @PodamExclude
    private MedicoEntity medico;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @PodamExclude
    private EnfermeroEntity enfermero;
    
    @OneToOne(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    @PodamExclude
    private HistoriaClinicaEntity historiaC;

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the nombre
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
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the contrasena
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
     * @return the servicios
     */
    public List<ServicioEntity> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<ServicioEntity> servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the quejas
     */
    public List<QuejaEntity> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas the quejas to set
     */
    public void setQuejas(List<QuejaEntity> quejas) {
        this.quejas = quejas;
    }

    /**
     * @return the calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionEntity calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the cita
     */
    public CitaEntity getCita() {
        return cita;
    }

    /**
     * @param cita the cita to set
     */
    public void setCita(CitaEntity cita) {
        this.cita = cita;
    }

    /**
     * @return the medico
     */
    public MedicoEntity getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    /**
     * @return the enfermero
     */
    public EnfermeroEntity getEnfermero() {
        return enfermero;
    }

    /**
     * @param enfermero the enfermero to set
     */
    public void setEnfermero(EnfermeroEntity enfermero) {
        this.enfermero = enfermero;
    }

    /**
     * @return the historiaC
     */
    public HistoriaClinicaEntity getHistoriaC() {
        return historiaC;
    }

    /**
     * @param historiaC the historiaC to set
     */
    public void setHistoriaC(HistoriaClinicaEntity historiaC) {
        this.historiaC = historiaC;
    }
}
