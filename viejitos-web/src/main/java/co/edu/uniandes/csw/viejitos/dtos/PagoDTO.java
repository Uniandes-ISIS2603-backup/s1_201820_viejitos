/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.PagoEntity;
import java.util.Date;

/**
 *
 * @author f.escobar PagoDTO Objeto de transferencia de datos de Pagos. Los DTO
 * contienen las represnetaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 * {
 * "id": Long,
 * "medio": String,
 * "pagado": Boolean,
 * "fechaLimite": Date,
 * "valor": Double
 * }
 * </pre> Por ejemplo una ciudad se representa asi:<br>
 *
 * <pre>
 *
 * {
 * "id": 123456,
 * "medio": "Efectivo",
 * "pagado": true,
 * "fechaLimite": "12/04/18",
 * "valor": 150000
 * }
 * </pre>
 */
public class PagoDTO {

    /**
     * id del pago
     */
    private Long id;

    /**
     * medio de pago
     */
    private String medio;

    /**
     * estado de pago booleano
     */
    private Boolean pagado;

    /**
     * fecha limite de pago
     */
    private Date fechaLimite;

    /**
     * valor monetario del pago
     */
    private Double valor;

    /**
     * Constructor
     */
    public PagoDTO() {
        //Constructor
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param PagoEntity: Es la entidad que se va a convertir a DTO
     */
    public PagoDTO(PagoEntity pagoEntity) {
        if (pagoEntity != null) {
            this.id = pagoEntity.getId();
            this.medio = pagoEntity.getMedio();
            this.pagado = pagoEntity.getPagado();
            this.fechaLimite = pagoEntity.getFechaLimite();
            this.valor = pagoEntity.getValor();
        }
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setId(this.id);
        entity.setMedio(this.medio);
        entity.setPagado(this.pagado);
        entity.setFechaLimite(this.fechaLimite);
        entity.setValor(this.valor);
        return entity;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id el nuevo id del pago
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return medio
     */
    public String getMedio() {
        return medio;
    }

    /**
     * @param medio el nuevo medio de pago
     */
    public void setMedio(String medio) {
        this.medio = medio;
    }

    /**
     * @return pagado
     */
    public Boolean isPagado() {
        return pagado;
    }

    /**
     * @param pagado el nuevo estado de pago
     */
    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    /**
     * @return fechaLimite
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /**
     * @param fechaLimite la nueva fechaLimite
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * @return valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor el nuevo valor monetario
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
