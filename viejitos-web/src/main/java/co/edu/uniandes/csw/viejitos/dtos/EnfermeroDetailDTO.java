/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import java.util.List;

import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import java.util.ArrayList;

/**
 * Objeto de transferencia de datos con relaciones para un enfermero.
 * Al serializarse como DTO se genera un JSON de la siguiente manera
 * <pre>
 *  {
 *      "id":number,
 *      "nombre":string,
 *      "cv":string,
 *      "login":string,
 *      "contrasenia":string,
 *      "tipo":string,
 *      "calificaciones":[calificaciones],
 *      "servicios":[servicios]
 *  }
 * </pre>
 * 
 * Por ejemplo, un enfermero se representa asi:
 * <pre>
 *  {
 *      "id":2678389,
 *      "nombre":"Juan Espitia",
 *      "cv":"./documents/juanHDV.pdf",
 *      "login":,"js.espitia"
 *      "contrasenia":"a89ess0909",
 *      "tipo":"Enfermero",
 *      "calificaciones":[{"id":209873, "puntaje":4.5,"comentario":"El enfermero fue grosero conmigo, se rehuso a prestarme un servicio adecuado","tipoCalificador":"cliente",
"loginCalificador":"af.ramirez","tipoCalificado":"enfermero","loginCalificado":"ma.marulanda"}]
 *      "servicios":[{ "tipo": 2,"fecha": 03/05/2017,"hora": "9:30","descripción": "Acompañamiento a cita","finalizado": true}]
 *  }
 * </pre>
 * @author js.espitia
 */
public class EnfermeroDetailDTO extends EnfermeroDTO{
  
    private List<CalificacionDTO> calificaciones;
    
    private List<ServicioDTO> servicios;
    
    private List<ClienteDTO> cliente;
    
    private CalendarioSemanalDTO calendar;
    
    public EnfermeroDetailDTO(){
    	super();
    }
    
    public EnfermeroDetailDTO(EnfermeroEntity entidad){
    	super(entidad);
        List<CalificacionEntity> califs = entidad.getCalificaciones();
        List<CalificacionDTO> calDTOS = new ArrayList<>();
        for(CalificacionEntity cal: califs){
            CalificacionDTO actual = new CalificacionDTO(cal);
            calDTOS.add(actual);
        }
        this.calificaciones = calDTOS;
        List<ServicioEntity> servs = entidad.getServicios();
        List<ServicioDTO> srDTOS = new ArrayList<>();
        for(ServicioEntity srv: servs){
            ServicioDTO actual = new ServicioDTO(srv);
            srDTOS.add(actual);
        }
        this.servicios = srDTOS;
        
    }

    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }
    
    @Override
    public EnfermeroEntity toEntity(){
    	EnfermeroEntity entidad = super.toEntity();
    	return entidad;
    }
}
