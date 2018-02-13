/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 * Objeto de transferencia de datos para una calificacion con sus relaciones.
 * Al serializarse como DTO se genera un JSON de la siguiente manera
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
 *      "servicio":3222221
 *  }
 * </pre>
 * @author js.espitia
 */
public class CalificacionDetailDTO extends CalificacionDTO{
    
    private ServicioDTO servicio;
    
    public CalificacionDetailDTO(){
    
    }

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }
    
}
