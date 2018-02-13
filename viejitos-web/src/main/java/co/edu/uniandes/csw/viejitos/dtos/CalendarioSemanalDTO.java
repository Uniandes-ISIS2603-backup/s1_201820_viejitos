/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author lf.naranjo11
 */
public class CalendarioSemanalDTO {
    
    private Date ultimaModficacion;
    
    
/**
     * @return la fecha de ultima modificacion
     */
    private Date getUltimaModificacion()
            {
                return ultimaModificacion;
            }
    /**
     * @param pUltimaModificacion la nueva fecha de modifcacion
     */
    private void setUltimaModificacion(Date pUltimaModificacion;)
    {
                ultimaModificacion=pUltimaModificacion;
           }
    
    
    public CalendarioSemanalDTO()
    { 
    }
     
     
    
}
