package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@XmlRootElement

public class EnfermeroEntity extends BaseEntity implements Serializable{
    private String cv;

    private String login;

    private String contrasenia;

    private Integer tipo;

    //Relacion de entidades OneToMany
    @OneToMany
    @PodamExclude
    private List<CalificacionEntity> calificaciones;

    //Relacion de entidades OneToMany
    @OneToMany(mappedBy = "enfermero")
    @PodamExclude
    private List<ServicioEntity> servicios;
    
    @OneToOne
    @PodamExclude
    private CalendarioSemanalEntity calendario;
    
    @ManyToMany
    @PodamExclude
    private List<ClienteEntity> clientes;


    public void setCV(String cv){
            this.cv = cv;
    }

    public String getCV(){
            return cv;
    }

    public void setLogin(String login){
            this.login = login;
    }

    public String getLogin(){
            return login;
    }

    public void setContrasenia(String contrasenia){
            this.contrasenia = contrasenia;
    }

    public String getContrasenia(){
            return contrasenia;
    }

    public void setTipo(Integer tipo){
            this.tipo = tipo;
    }

    public Integer getTipo(){
            return tipo;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<ServicioEntity> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioEntity> servicios) {
        this.servicios = servicios;
    }

    public CalendarioSemanalEntity getCalendario() {
        return calendario;
    }

    public void setCalendario(CalendarioSemanalEntity calendario) {
        this.calendario = calendario;
    }

    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }
}
