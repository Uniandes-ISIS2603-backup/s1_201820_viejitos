/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.Date;

/**
 *
 * @author f.escobar
 * PagoDTO Objeto de transferencia de datos de Pagos. Los DTO contienen las
 represnetaciones de los JSON que se transfieren entre el cliente y el
 servidor.
 
 Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 <pre>
   {
      "medio": String,
      "pagado": Boolean,
      "fechaLimite": Date,
      "valor": Double
   }
 </pre>
 Por ejemplo una ciudad se representa asi:<br>
 
 <pre>
 
   {
      "medio": "Efectivo",
      "pagado": TRUE,
      "fechaLimite": 12/04/18,
      "valor": 150000
   }
   </pre>
 */

public class PagoDTO {

    private String medio;
    
    private Boolean pagado;
    
    private Date fechaLimite;
    
    private Double valor;
    
    /**
     * @return the medio
     */
    public String getMedio() {
        return medio;
    }

    /**
     * @param medio the medio to set
     */
    public void setMedio(String medio) {
        this.medio = medio;
    }

    /**
     * @return the pagado
     */
    public Boolean isPagado() {
        return pagado;
    }

    /**
     * @param pagado the pagado to set
     */
    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    /**
     * @return the fechaLimite
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * @param fechaLimite the fechaLimite to set
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
    
    public PagoDTO()
    {
        
    }
}
