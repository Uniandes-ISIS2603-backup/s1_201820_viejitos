/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.HistoriaClinicaEntity;

/**
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "cirugias": string,
 *      "enfermedades": string,
 *      "medicamentos": string
 *   }
 * </pre> Por ejemplo una entidad de HistoriaClinica se representa asi:<br>
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
public class HistoriaClinicaDTO {

    private Long id;

    private String enfermedades;

    private String medicamentos;

    private String cirugias;

    public HistoriaClinicaDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param HistoriaClinicaEntity: Es la entidad que se va a convertir a DTO
     */
    public HistoriaClinicaDTO(HistoriaClinicaEntity historiaCEntity) {
        //TODO:historiaCEntity puede ser null
        this.id = historiaCEntity.getId();
        this.enfermedades = historiaCEntity.getEnfermedades();
        this.medicamentos = historiaCEntity.getMedicamentos();
        this.cirugias = historiaCEntity.getCirugias();
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public HistoriaClinicaEntity toEntity() {
        HistoriaClinicaEntity entity = new HistoriaClinicaEntity();
        entity.setId(this.id);
        entity.setCirugias(this.cirugias);
        entity.setEnfermedades(this.enfermedades);
        entity.setMedicamentos(this.medicamentos);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getCirugias() {
        return cirugias;
    }

    public void setCirugias(String pCirugias) {
        cirugias = pCirugias;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String pEnfermedades) {
        enfermedades = pEnfermedades;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String pMedicamentos) {
        medicamentos = pMedicamentos;
    }
}
