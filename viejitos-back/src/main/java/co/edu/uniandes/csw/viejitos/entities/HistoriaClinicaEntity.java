/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author jj.silva
 */
@Entity
public class HistoriaClinicaEntity extends BaseEntity implements Serializable
{
    private String enfermedades;
    
    private String medicamentos;
    
    private String cirugias;
    
    @OneToOne(mappedBy = "historiaC", fetch = FetchType.EAGER)
    @PodamExclude
    private ClienteEntity cliente;

    /**
     * @return the enfermedades
     */
    public String getEnfermedades() {
        return enfermedades;
    }

    /**
     * @param enfermedades the enfermedades to set
     */
    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    /**
     * @return the medicamentos
     */
    public String getMedicamentos() {
        return medicamentos;
    }

    /**
     * @param medicamentos the medicamentos to set
     */
    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    /**
     * @return the cirugias
     */
    public String getCirugias() {
        return cirugias;
    }

    /**
     * @param cirugias the cirugias to set
     */
    public void setCirugias(String cirugias) {
        this.cirugias = cirugias;
    }	

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
