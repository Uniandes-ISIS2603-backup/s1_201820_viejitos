/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;

/**
 * CalendarioSemanalDTO Objeto de transferencia de datos de calendarioSemanal. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "ultimaModificacion": Date
 *   }
 * </pre>
 * Por ejemplo un calendario semanal se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *     "id": 001101,
 *      "ultimaModificacion": 01/07/2018 08:11:13pm
 *           
 * 
 *   }
 *
 * </pre>

  @author lf.naranjo11
 */
public class CalendarioSemanalDTO {
    private Integer id;
    private Date ultimaModficacion;
    
    
/**
     * @return la fecha de ultima modificacion
     */
    private Date getUltimaModificacion()
            {
                return ultimaModficacion;
            }
    /**
     * @param pUltimaModificacion la nueva fecha de modifcacion
     */
    private void setUltimaModificacion(Date pUltimaModificacion)
    {
                ultimaModficacion=pUltimaModificacion;
           }
    
     /**
     * @return la  id de franja horaria
     */
    private int getId()
            {
                return id;
            }
    
    
     /**
     * @param pId la nueva hora de finalizacion de la franja
     */
    private void setid(Integer pId)
            {
                id=pId;
            }
    
    
     /**
     * Constructor por defecto
     */
    public CalendarioSemanalDTO()
    { 
    }
     
     
    
}
