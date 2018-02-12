/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**
 *
 * @author c.gomezs
 */
public class QuejaDTO {
    
    private String reclamo;
    
    private boolean resuelto;

    /**
     * @return the reclamo
     */
    public String getReclamo() {
        return reclamo;
    }

    /**
     * @param reclamo the reclamo to set
     */
    public void setReclamo(String reclamo) {
        this.reclamo = reclamo;
    }

    /**
     * @return the resuelto
     */
    public boolean isResuelto() {
        return resuelto;
    }

    /**
     * @param resuelto the resuelto to set
     */
    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }
    
    
    public QuejaDTO()
    {
        
    }
    
}
