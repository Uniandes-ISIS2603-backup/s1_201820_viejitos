/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;

/**
 * Objeto de transferencia de datos para una calificacion con sus relaciones. Al
 * serializarse como DTO se genera un JSON de la siguiente manera
 * <pre>
 *  {
 *      "id":number,
 *      "puntaje":double,
 *      "comentario":string,
 *      "tipoCalificador":string,
 *      "loginCalificador":string,
 *      "tipoCalificado":string,
 *      "loginCalificado":string,
 *      "servicio":id
 *  }
 * </pre>
 *
 * Por ejemplo, una calificacion se representa asi:
 * <pre>
 *  {
 *      "id":209873,
 *      "puntaje":4.5,
 *      "comentario":"El enfermero fue grosero conmigo, se rehuso a prestarme un servicio adecuado",
 *      "tipoCalificador":"cliente",
 *      "loginCalificador":"af.ramirez",
 *      "tipoCalificado":"enfermero",
 *      "loginCalificado":"ma.marulanda"
 *      "servicio":[{ "tipo": 2,"fecha": 03/05/2017,"hora": "9:30","descripción": "Acompañamiento a cita","finalizado": true}]
 *  }
 * </pre>
 *
 * @author js.espitia
 */
public class CalificacionDetailDTO extends CalificacionDTO {

    private ServicioDTO servicio;

    public CalificacionDetailDTO() {
        super();
    }

    public CalificacionDetailDTO(CalificacionEntity entidad) {
        super(entidad);
        if (entidad != null) {
            this.servicio = new ServicioDTO(entidad.getServicio());
        }
    }

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    @Override
    public CalificacionEntity toEntity() {
        CalificacionEntity entidad = super.toEntity();
        if (servicio != null) {
            entidad.setServicio(servicio.toEntity());
        }
        return entidad;
    }

}
