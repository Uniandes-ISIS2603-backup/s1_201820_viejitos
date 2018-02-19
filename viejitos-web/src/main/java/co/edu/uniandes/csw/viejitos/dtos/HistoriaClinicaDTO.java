/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

/**Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "cirugias": string,
 *      "enfermedades": string,
 *      "medicamentos": string
 *   }
 * </pre>
 * Por ejemplo una entidad de HistoriaClinica se representa asi:<br>
 * <pre>
 *
 *   {
 *      "id": 12345,
 *      "cirugias": "Reemplazo de cadera.",
 *      "enfermedades": "Ninguna",
 *      "medicamentos": "Ninguno"
 *   }
 *
 * </pre>
 *
 * @author jj.silva
 */
public class HistoriaClinicaDTO
{
    private Long id;
    
    private String enfermedades;
    
    private String medicamentos;
    
    private String cirugias;
    
    public HistoriaClinicaDTO()
    {
        
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long pId)
    {
        id = pId;
    }
    
    public String getCirugias()
    {
        return cirugias;
    }
    
    public void setCirugias(String pCirugias)
    {
        cirugias = pCirugias;
    }
    
    public String getEnfermedades()
    {
        return enfermedades;
    }
    
    public void setEnfermedades(String pEnfermedades)
    {
        enfermedades = pEnfermedades;
    }
    
    public String getMedicamentos()
    {
        return medicamentos;
    }
    
    public void setMedicamentos(String pMedicamentos)
    {
        medicamentos = pMedicamentos;
    }
}
