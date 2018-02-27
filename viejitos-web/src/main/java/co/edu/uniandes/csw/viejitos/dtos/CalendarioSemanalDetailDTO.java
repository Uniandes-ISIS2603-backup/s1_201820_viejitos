/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import java.util.List;

/**
 
 * Clase que extiende de {@link CalendarioSemanalDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CalendarioSemanalDTO}
 * 
 *  Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "ultimaModificacion": Date,
 * "franjasHorarias": [ {
 * 
 * "id": number,
 *      "diaSemana": string,
 *        "horaInicio": number,
 *       "horaFin": number,
 *       "ocupado": boolean
 *         }]
 *   }
 * </pre>
 * Por ejemplo un calendario semanal se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *     "id": 001101,
 *      "ultimaModificacion": 01/07/2018 08:11:13pm,
 * "franjasHorarias": [ {
 * 
 * "id": 1,
 *      "diaSemana": 1,
 *        "horaInicio": 0700,
 *       "horaFin": 0900,
 *       "ocupado": si
 *         },
 * {
 * 
 * "id": 2,
 *      "diaSemana": 1,
 *        "horaInicio": 0900,
 *       "horaFin": 1100,
 *       "ocupado": no
 *         }
 * ]
 *           
 * 
 *   }
 *
 * 
 * 
 * @author lf.naranjo11
 */
public class CalendarioSemanalDetailDTO extends CalendarioSemanalDTO{
    
    private List<FranjaHorariaDTO> franjasHorarias;
     /**
     * Constructor por defecto
     */
    public CalendarioSemanalDetailDTO(CalendarioSemanalEntity entity)
    {
        super(entity);
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
