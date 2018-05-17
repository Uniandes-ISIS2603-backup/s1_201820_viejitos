/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.CalificacionEntity;
import co.edu.uniandes.csw.viejitos.entities.ClienteEntity;
import java.util.List;

import co.edu.uniandes.csw.viejitos.entities.EnfermeroEntity;
import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import java.util.ArrayList;

/**
 * Objeto de transferencia de datos con relaciones para un enfermero. Al
 * serializarse como DTO se genera un JSON de la siguiente manera
 * <pre>
 *  {
 *      "id":number,
 *      "nombre":string,
 *      "cv":string,
 *      "login":string,
 *      "contrasenia":string,
 *      "tipo":string,
 *      "calificaciones":[calificaciones],
 *      "servicios":[servicios],
 *      "clientes":[clientes]
 *      "calendar":{calendar}
 *  }
 * </pre>
 *
 * Por ejemplo, un enfermero se representa asi:
 * <pre>
 *  {
 *      "id":2678389,
 *      "nombre":"Miguel Andres Marulanda Ramírez",
 *      "cv":"./documents/miguelHDV.pdf",
 *      "login":,"ma.marulanda"
 *      "contrasenia":"a89ess0909",
 *      "tipo":"Enfermero",
 *      "calificaciones":[{"id":209873, "puntaje":4.5,"comentario":"El enfermero fue grosero conmigo, se rehuso a prestarme un servicio adecuado","tipoCalificador":"cliente",
 *      "loginCalificador":"af.ramirez","tipoCalificado":"enfermero","loginCalificado":"ma.marulanda"}]
 *      "servicios":[{ "tipo": 2,"fecha": 03/05/2017,"hora": "9:30","descripción": "Acompañamiento a cita","finalizado": true}],
 *      "clientes":[{"id": 12345, "nombre": "Andres Felipe Ramírez Triana", "login": "af.ramirez","contrasena": "jd124, "estado": 1,"tipo": 1}]
 *      "calendar":{"id": 001101,"ultimaModificacion": 01/07/2018 08:11:13pm,"franjasHorarias": [ {"id": 1,"diaSemana": 1,"horaInicio": 0700,"horaFin": 0900,"ocupado": si},{"id": 2,"diaSemana": 1, "horaInicio": 0900,"horaFin": 1100, "ocupado": no}]}
 *  }
 * </pre>
 *
 * @author js.espitia
 */
public class EnfermeroDetailDTO extends EnfermeroDTO {

    private List<CalificacionDTO> calificaciones;

    private List<ServicioDTO> servicios;

    private List<ClienteDTO> cliente;

    private CalendarioSemanalDTO calendar;

    public EnfermeroDetailDTO() {
        super();
    }

    public EnfermeroDetailDTO(EnfermeroEntity entidad) {
        super(entidad);
        if (entidad != null) {
            List<CalificacionEntity> califs = entidad.getCalificaciones();
            List<CalificacionDTO> calDTOS = new ArrayList<>();
            for (CalificacionEntity cal : califs) {
                CalificacionDTO actual = new CalificacionDTO(cal);
                calDTOS.add(actual);
            }
            this.calificaciones = calDTOS;
            List<ServicioEntity> servs = entidad.getServicios();
            List<ServicioDTO> srDTOS = new ArrayList<>();
            for (ServicioEntity srv : servs) {
                ServicioDTO actual = new ServicioDTO(srv);
                srDTOS.add(actual);
            }
            this.servicios = srDTOS;
            List<ClienteEntity> clients = entidad.getClientes();
            List<ClienteDTO> clDTOS = new ArrayList<>();
            for (ClienteEntity cl : clients) {
                ClienteDTO actual = new ClienteDTO(cl);
                clDTOS.add(actual);
            }
            this.cliente = clDTOS;
            this.calendar = new CalendarioSemanalDTO(entidad.getCalendario());
        }
    }

    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }

    public List<ClienteDTO> getCliente() {
        return cliente;
    }

    public void setCliente(List<ClienteDTO> cliente) {
        this.cliente = cliente;
    }

    public CalendarioSemanalDTO getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarioSemanalDTO calendar) {
        this.calendar = calendar;
    }

    @Override
    public EnfermeroEntity toEntity() {
        EnfermeroEntity entidad = super.toEntity();
        //DONE this.calendar puede ser null
        if(this.calendar!=null)
            entidad.setCalendario(this.calendar.toEntity());
        List<CalificacionEntity> cals = new ArrayList<>();
        for (CalificacionDTO actual : calificaciones) {
            cals.add(actual.toEntity());
        }
        entidad.setCalificaciones(cals);
        List<ClienteEntity> cls = new ArrayList<>();
        for (ClienteDTO actual : cliente) {
            cls.add(actual.toEntity());
        }
        entidad.setClientes(cls);
        List<ServicioEntity> servs = new ArrayList<>();
        for (ServicioDTO actual : servicios) {
            servs.add(actual.toEntity());
        }
        entidad.setServicios(servs);
        return entidad;
    }
}
