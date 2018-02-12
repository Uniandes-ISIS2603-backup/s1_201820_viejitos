/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre: string,
 *      "login": string,
 *      "contrasena": string,
 *      "estado": number,
 *      "tipo": number,
 *      "calificacion": {
 *          "idCalificacion": number,
 *          "puntaje": number,
 *          "comentario": string,
 *          "loginCalificado": string,
 *          "tipoCalificado": string,
 *          "loginCalificador": string,
 *          "tipoCalificador": string },
 *      "calificaciones": [ {
 *          "idCalificacion": number,
 *          "puntaje": number,
 *          "comentario": string,
 *          "loginCalificado": string,
 *          "tipoCalificado": string,
 *          "loginCalificador": string,
 *          "tipoCalificador": string }`]
 *      "cita": 
 * 
 *   }
 * </pre>
 * Por ejemplo una entidad de Cliente se representa asi:<br>
 * <p>
 * <pre>
 *
 *   {
 *      "id": 12345,
 *      "nombre: "John Doe",
 *      "login": "johndoe23",
 *      "contrasena": "jd124",
 *      "estado": 1,
 *      "tipo": 1
 *   }
 *
 * </pre>
 * @author jj.silva
 */
public class ClienteDetailDTO extends ClienteDTO
{
    private List<ServicioDTO> servicios;
    private List<QuejaDTO> quejas;
    private CalificacionDTO calificacion;
    private List<CalificacionDTO> calificaciones;
    private CitaDTO cita;
    private MedicoDTO medico;
    private EnfermeroDTO enfermero;
    private HistoriaClinicaDTO historiaC;
    
    public ClienteDetailDTO()
    {
        
    }

    /**
     * @return the servicios
     */
    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the quejas
     */
    public List<QuejaDTO> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas the quejas to set
     */
    public void setQuejas(List<QuejaDTO> quejas) {
        this.quejas = quejas;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the enfermero
     */
    public EnfermeroDTO getEnfermero() {
        return enfermero;
    }

    /**
     * @param enfermero the enfermero to set
     */
    public void setEnfermero(EnfermeroDTO enfermero) {
        this.enfermero = enfermero;
    }

    /**
     * @return the historiaC
     */
    public HistoriaClinicaDTO getHistoriaC() {
        return historiaC;
    }

    /**
     * @param historiaC the historiaC to set
     */
    public void setHistoriaC(HistoriaClinicaDTO historiaC) {
        this.historiaC = historiaC;
    }
    
    
    
    
}
