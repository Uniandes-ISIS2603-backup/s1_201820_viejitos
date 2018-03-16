/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;


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
    private Long id;
     /**
     * Constructor por defecto
     */
    public FranjaHorariaDTO(FranjaHorariaEntity entity)
    {
    if(entity!=null)
    {
    horaInicio=entity.getHoraInicio();
    horaFin=entity.getHoraFin();
    ocupado=entity.isOcupado();
    diaSemana=entity.getDiaSemana();
    id=entity.getId();
    }
    }
    public FranjaHorariaDTO()
    {
    
    }
     public FranjaHorariaEntity toEntity()
     {
         FranjaHorariaEntity entity=new FranjaHorariaEntity();
         entity.setDiaSemana(this.diaSemana);
         entity.setHoraFin(this.horaFin);
         entity.setHoraInicio(this.horaInicio);
         entity.setId(this.id);
         entity.setOcupado(this.ocupado);
         return entity;
     }
    
    
    /**
     * @return  la  hora de inicio de la cita
     */
    public Integer getHoraInicio()
            {
                return horaInicio;
            }
    
    /**
     * @param pHoraInicio la nueva hora de inicio de la franja
     */
    public void setHoraInicio(Integer pHoraInicio)
            {
                horaInicio=pHoraInicio;
            }
    
    /**
     * @return la  hora de finalizacion de la franja
     */
    public Integer getHoraFin ()
            {
                return horaFin;
            }
    /**
     * @param pHoraFin la nueva hora de finalizacion de la franja
     */
    public void setHoraFin(Integer pHoraFin)
            {
                horaFin=pHoraFin;
            }
    /**
     * @return la  id de franja horaria
     */
    public Long getId()
            {
                return id;
            }
    
    
     /**
     * @param pId la nueva hora de finalizacion de la franja
     */
    public void setid(Long pId)
            {
                id=pId;
            }
    
    
    
    /**
     * @return el estado de la franja horaria
     */
    public Boolean getOcupado()
            {
                return ocupado;
            }
    /**
     * @param pOcupado el nuevo estado de la franja horaria
     */
    public void setOcupado(Boolean pOcupado)
            {
                ocupado=pOcupado;
            }
    
    /**
     * @return dia de la semana de la franja
     */
    public String getDiaSemana()
            {
                return diaSemana;
            }
    /**
     * @param pDiaSemana el nuevo dia de la semana de la franja
     */
    public void setDiaSemana(Integer pDiaSemana)
            {
                horaInicio=pDiaSemana;
            }
    
}
