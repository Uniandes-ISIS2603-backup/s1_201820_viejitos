/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;

/**
 *
 * @author l.pardo
 */
public class CitaDTO {
    private Date fecha;
    private Date hora;
    public CitaDTO()
    {
        
    }
    public Date getFecha()
    {
        return fecha;
    }
    public Date getHora()
    {
        return hora;
    }
    public void setFecha(Date pFecha)
    {
        fecha=pFecha;
    }
    public void setHora(Date pHora)
    {
        hora= pHora;
    }
}
