/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;

/**Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "cirugias": string,
 *      "enfermedades": string,
 *      "medicamentos": string,
 *      "cliente": {
 *          "id": number,
 *          "nombre: string,
 *          "login": string,
 *          "contrasena": string,
 *          "estado": number,
 *          "tipo": number }
 *      
 *   }
 * </pre>
 * Por ejemplo una entidad de HistoriaClinica se representa asi:<br>
 * <pre>
 *
 *   {
 *      "id": 12345,
 *      "cirugias": "Reemplazo de cadera.",
 *      "enfermedades": "Ninguna",
 *      "medicamentos": "Ninguno",
 *      "cliente": {
 *          "id": 12345,
 *          "nombre: "John Doe",
 *          "login": "johndoe23",
 *          "contrasena": "jd124",
 *          "estado": 1,
 *          "tipo": 1 }
 *   }
 *
 * </pre>
 *
 * @author jj.silva
 */
public class HistoriaClinicaDetailDTO extends HistoriaClinicaDTO
{
    private ClienteDTO cliente;
    
    public HistoriaClinicaDetailDTO()
    {
        super();
    }
    
    public HistoriaClinicaDetailDTO(HistoriaClinicaEntity entity)
    {
        super(entity);
        if (entity.getCliente()!= null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        } else {
            entity.setCliente(null);
        }
    }
    
     @Override
    public HistoriaClinicaEntity toEntity() {
        HistoriaClinicaEntity historiaCE = super.toEntity();
        if (this.getCliente()!= null) {
            historiaCE.setCliente(this.getCliente().toEntity());
        }
        return historiaCE;
    }

    /**
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
