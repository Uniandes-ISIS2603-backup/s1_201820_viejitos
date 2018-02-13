/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**
 *
 * @author lf.naranjo11
 */
public class CalendarioSemanalDetailDTO extends CalendarioSemanalDTO{
    
    private List<FranjaHorariaDTO> franjasHorarias;
    
    public CalendarioSemanalDetailDTO()
    {
    }
    
    
    

    public void setFranjasHorarias(List<FranjaHorariaDTO> pfranjas) {
        this.franjasHorarias = pfranjas;
    }

    public List<FranjaHorariaDTO> getFranjasHorarias() {
        return franjasHorarias;
    }


    
    
}
