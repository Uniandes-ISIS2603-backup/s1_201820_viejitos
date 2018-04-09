/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

//TODO:DONE Borrar lo que no se usa
import co.edu.uniandes.csw.viejitos.entities.CalendarioSemanalEntity;
import co.edu.uniandes.csw.viejitos.entities.FranjaHorariaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase que extiende de {@link CalendarioSemanalDTO} para manejar la
 * transformacion entre los objetos JSON y las Entidades de la base de datos.
 * Para conocer el contenido de la ciudad vaya a la documentacion de
 * {@link CalendarioSemanalDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
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
 * </pre> Por ejemplo un calendario semanal se representa asi:<br>
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
public class CalendarioSemanalDetailDTO extends CalendarioSemanalDTO {

    private List<FranjaHorariaDTO> franjasHorarias;

    /**
     * Constructor sin parámetros
     */
    public CalendarioSemanalDetailDTO() {
        super();
    }

    public CalendarioSemanalDetailDTO(CalendarioSemanalEntity entity) {
        super(entity);
        if (entity != null) {
            List<FranjaHorariaEntity> frjsEnt = entity.getFranjas();
            List<FranjaHorariaDTO> frjsDTO = new ArrayList<>();

            for (FranjaHorariaEntity entityFranja : frjsEnt) {
                FranjaHorariaDTO actual = new FranjaHorariaDTO(entityFranja);
                frjsDTO.add(actual);
            }
            this.franjasHorarias = frjsDTO;

        }

    }

    @Override
    public CalendarioSemanalEntity toEntity() {
        CalendarioSemanalEntity entity = super.toEntity();

        List<FranjaHorariaEntity> fs = new ArrayList<>();

        for (FranjaHorariaDTO actual : franjasHorarias) {
            fs.add(actual.toEntity());
        }
        entity.setFranjas(fs);
        return entity;
    }

    /**
     * @return the franjasHorarias
     */
    public List<FranjaHorariaDTO> getFranjasHorarias() {
        return franjasHorarias;
    }

    /**
     * @param franjasHorarias the franjasHorarias to set
     */
    public void setFranjasHorarias(List<FranjaHorariaDTO> franjasHorarias) {
        this.franjasHorarias = franjasHorarias;
    }

}
