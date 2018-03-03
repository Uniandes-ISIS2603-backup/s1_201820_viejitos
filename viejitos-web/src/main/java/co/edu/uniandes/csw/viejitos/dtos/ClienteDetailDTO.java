/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.QuejaEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import java.util.ArrayList;
import java.util.List;

/**Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre: string,
 *      "login": string,
 *      "contrasena": string,
 *      "estado": number,
 *      "tipo": number,
 *      "calificacion": {
 *          "idCalificacion": number,
 *          "puntaje": number,
 *          "comentario": string,
 *          "loginCalificado": string,
 *          "tipoCalificado": string,
 *          "loginCalificador": string,
 *          "tipoCalificador": string },
 *      "calificaciones": [ {
 *          "idCalificacion": number,
 *          "puntaje": number,
 *          "comentario": string,
 *          "loginCalificado": string,
 *          "tipoCalificado": string,
 *          "loginCalificador": string,
 *          "tipoCalificador": string }]
 *      "cita": {
 *          "fecha": Date,
 *          "hora": Date },
 *      "enfermero": {
 *          "nombre": string,
 *          "id": string,
 *          "contrasena": string,
 *          "cv": string,
 *          "tipo": number },
 *      "historiaC": {
 *          "id": number,
 *          "enfermedades": string,
 *          "medicamentos": string,
 *          "cirugias": string },
 *      "medico": {
 *          "nombre": string,
 *          "tipo": number,
 *          "id": string,
 *          "contrasena": string },
 *      "quejas": [ {
 *          "reclamo": string,
 *          "resuelto": boolean } ]
 *      "servicios": [ {
 *          "tipo": number,
 *          "fecha": Date,
 *          "hora": string,
 *          "descripcion": string,
 *          "finalizado": boolean } ]
 *   }
 * </pre>
 * Por ejemplo una entidad de Cliente se representa asi:<br>
 * <pre>
 *
 *   {
 *      "id": 1234,
 *      "nombre: "John Doe",
 *      "login": "johndoe23",
 *      "contrasena": "jd124",
 *      "estado": 1,
 *      "tipo": 1,
 *      "calificacion": {
 *          "idCalificacion": 1111,
 *          "puntaje": 4,
 *          "comentario": "Excelente persona.",
 *          "loginCalificado": "johndoe23",
 *          "tipoCalificado": "1",
 *          "loginCalificador": "jblack45",
 *          "tipoCalificador": "2" },
 *      "calificaciones": [ {
 *          "idCalificacion": 1112,
 *          "puntaje": 5,
 *          "comentario": "Enfermero de muy alta calidad.",
 *          "loginCalificado": "jblack45",
 *          "tipoCalificado": "2",
 *          "loginCalificador": "johndoe23",
 *          "tipoCalificador": "1" }]
 *      "cita": {
 *          "fecha": 15/02/2018,
 *          "hora": 14:00 },
 *      "enfermero": {
 *          "nombre": "Jack Black",
 *          "id": "1113",
 *          "contrasena": "jb125",
 *          "cv": string,
 *          "tipo": 2 },
 *      "historiaC": {
 *          "id": 1114,
 *          "enfermedades": "Ninguna.",
 *          "medicamentos": "Ninguna",
 *          "cirugias": "Reemplazo de cadera." },
 *      "medico": {
 *          "nombre": "Carlos Gonzalez",
 *          "tipo": 3,
 *          "id": "1115",
 *          "contrasena": "cg777" },
 *      "quejas": [ {
 *          "reclamo": "El cliente se demoro en pagar.",
 *          "resuelto": true } ]
 *      "servicios": [ {
 *          "tipo": 1,
 *          "fecha": 15/02/2018,
 *          "hora": "14:00",
 *          "descripcion": "Caminata",
 *          "finalizado": false } ]
 *   }
 *
 * </pre>
 * @author jj.silva
 */
public class ClienteDetailDTO extends ClienteDTO
{

    
    private List<ServicioDTO> servicios;
    private List<QuejaDTO> quejas;
    private CalificacionDTO calificacion;
    private List<CalificacionDTO> calificaciones;
    private CitaDTO cita;
    private MedicoDTO medico;
    private List<EnfermeroDTO> enfermeros;
    private HistoriaClinicaDTO historiaC;
    
    public ClienteDetailDTO()
    {
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity entity)
    {
        super(entity);
        if (entity.getCalificacion()!= null) {
            this.calificacion = new CalificacionDTO(entity.getCalificacion());
        } else {
            entity.setCalificacion(null);
        }
        if (entity.getServicios()!= null) {
            servicios = new ArrayList<>();
            for (ServicioEntity entityServicio : entity.getServicios()) {
                servicios.add(new ServicioDTO(entityServicio));
            }
        }
        
        if (entity.getQuejas()!= null) {
            quejas = new ArrayList<>();
            for (QuejaEntity entityQueja : entity.getQuejas()) {
                quejas.add(new QuejaDTO(entityQueja));
            }
        }
        
        if (entity.getCalificaciones()!= null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }
        
        if (entity.getCita()!= null) {
            this.cita = new CitaDTO(entity.getCita());
        } else {
            entity.setCita(null);
        }
        
        if (entity.getMedico()!= null) {
            this.medico = new MedicoDTO(entity.getMedico());
        } else {
            entity.setMedico(null);
        }
        
        if (entity.getEnfermeros()!= null) {
            enfermeros = new ArrayList<>();
            for (EnfermeroEntity entityEnfermero : entity.getEnfermeros()) {
                enfermeros.add(new EnfermeroDTO(entityEnfermero));
            }
        }
        
        if (entity.getHistoriaC()!= null) {
            this.historiaC = new HistoriaClinicaDTO(entity.getHistoriaC());
        } else {
            entity.setHistoriaC(null);
        }
    }
    
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity clienteE = super.toEntity();
        if (this.getCalificacion() != null) {
            clienteE.setCalificacion(this.getCalificacion().toEntity());
        }
        
        if (this.getCita()!= null) {
            clienteE.setCita(this.getCita().toEntity());
        }
        
        if (getEnfermeros()!= null) {
            List<EnfermeroEntity> enfermerosEntity = new ArrayList<>();
            for (EnfermeroDTO dtoEnfermero : getEnfermeros()) {
                enfermerosEntity.add(dtoEnfermero.toEntity());
            }
            clienteE.setEnfermeros(enfermerosEntity);
        }
        
        if (this.getHistoriaC()!= null) {
            clienteE.setHistoriaC(this.getHistoriaC().toEntity());
        }
        
        if (this.getMedico() != null) {
            clienteE.setMedico(this.getMedico().toEntity());
        }
        
        if (getCalificaciones()!= null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : getCalificaciones()) {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            clienteE.setCalificaciones(calificacionesEntity);
        }
         if (getQuejas()!= null) {
            List<QuejaEntity> quejasEntity = new ArrayList<>();
            for (QuejaDTO dtoQueja : getQuejas()) {
                quejasEntity.add(dtoQueja.toEntity());
            }
            clienteE.setQuejas(quejasEntity);
        }
         
          if (getServicios()!= null) {
            List<ServicioEntity> serviciosEntity = new ArrayList<>();
            for (ServicioDTO dtoServicio : getServicios()) {
                serviciosEntity.add(dtoServicio.toEntity());
            }
            clienteE.setServicios(serviciosEntity);
        }
        return clienteE;
    }

    /**
     * @return the servicios
     */
    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the quejas
     */
    public List<QuejaDTO> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas the quejas to set
     */
    public void setQuejas(List<QuejaDTO> quejas) {
        this.quejas = quejas;
    }
    
    /**
     * @return the calificacion
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the historiaC
     */
    public HistoriaClinicaDTO getHistoriaC() {
        return historiaC;
    }

    /**
     * @param historiaC the historiaC to set
     */
    public void setHistoriaC(HistoriaClinicaDTO historiaC) {
        this.historiaC = historiaC;
    }

    /**
     * @return the cita
     */
    public CitaDTO getCita() {
        return cita;
    }

    /**
     * @param cita the cita to set
     */
    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    /**
     * @return the medico
     */
    public MedicoDTO getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }

    /**
     * @return the enfermeros
     */
    public List<EnfermeroDTO> getEnfermeros() {
        return enfermeros;
    }

    /**
     * @param enfermeros the enfermeros to set
     */
    public void setEnfermeros(List<EnfermeroDTO> enfermeros) {
        this.enfermeros = enfermeros;
    }
    
    
    
    
}
