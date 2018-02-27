package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class EnfermeroEntity extends BaseEntity implements Serializable{
    private String cv;

    private String login;

    private String contrasenia;

    private Integer tipo;

    //Relacion de entidades OneToMany
    @OneToMany
    private List<CalificacionEntity> calificaciones;

    //Relacion de entidades OneToMany
    @OneToMany(mappedBy = "enfermero")
    private List<ServicioEntity> servicios;
    
    @OneToOne
    private CalendarioSemanalEntity calendario;


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
	
}
