/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**
 
 * Clase que extiende de {@link CalendarioSemanalDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CalendarioSemanalDTO}
 * @author lf.naranjo11
 */
public class CalendarioSemanalDetailDTO extends CalendarioSemanalDTO{
    
    private List<FranjaHorariaDTO> franjasHorarias;
     /**
     * Constructor por defecto
     */
    public CalendarioSemanalDetailDTO()
    {
    }
    
    
    
/**
     *
     * @param pfranjas las frnajas nueva
     * 
     */
    public void setFranjasHorarias(List<FranjaHorariaDTO> pfranjas) {
        this.franjasHorarias = pfranjas;
    }

    /**
     *
     * @return las franjas
     */
    public List<FranjaHorariaDTO> getFranjasHorarias() {
        return franjasHorarias;
    }


    
    
}
