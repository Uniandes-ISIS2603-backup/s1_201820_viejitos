/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;


 /**
 * FranjaHorariaDTO Objeto de transferencia de datos de franjaHOraria. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "diaSemana": string,
 *        "horaInicio": number,
 *       "horaFin": number,
 *       "ocupado": boolean
 *   }
 * </pre>
 * Por ejemplo una franja se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 * "id": 001101,
 *      "diaSemana": lunes,
 *        "horaInicio": 0800,
 *       "horaFin": 0900,
 *       "ocupado": true     
 * 
 *   }
 *
 * </pre>

  @author lf.naranjo11
 */

public class FranjaHorariaDTO {
    private Integer horaInicio;
    private Integer horaFin;
    private Boolean ocupado;
    private String diaSemana;
    private Integer id;
     /**
     * Constructor por defecto
     */
    public FranjaHorariaDTO()
    {
    }
     
    
    
    /**
     * @return  la  hora de inicio de la cita
     */
    private int getHoraInicio()
            {
                return horaInicio;
            }
    
    /**
     * @param pHoraInicio la nueva hora de inicio de la franja
     */
    private void setHoraInicio(Integer pHoraInicio)
            {
                horaInicio=pHoraInicio;
            }
    
    /**
     * @return la  hora de finalizacion de la franja
     */
    private int getHoraFin ()
            {
                return horaFin;
            }
    /**
     * @param pHoraFin la nueva hora de finalizacion de la franja
     */
    private void setHoraFin(Integer pHoraFin)
            {
                horaFin=pHoraFin;
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
     * @return el estado de la franja horaria
     */
    private Boolean getOcupado()
            {
                return ocupado;
            }
    /**
     * @param pOcupado el nuevo estado de la franja horaria
     */
    private void setOcupado(Boolean pOcupado)
            {
                ocupado=pOcupado;
            }
    
    /**
     * @return dia de la semana de la franja
     */
    private String getDiaSemana()
            {
                return diaSemana;
            }
    /**
     * @param pDiaSemana el nuevo dia de la semana de la franja
     */
    private void setDiaSemana(Integer pDiaSemana)
            {
                horaInicio=pDiaSemana;
            }
    
}
