/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import java.util.List;

/**
 *
 * @author jj.silva
 */
public class ClienteDetailDTO extends ClienteDTO
{
    private UsuarioDTO usuario;
    private List<ServicioDTO> servicios;
    private List<QuejaDTO> quejas;
    private CalificacionDTO calificacion;
    private List<CalificacionDTO> calificaciones;
    private CitaDTO cita;
    private MedicoDTO medico;
    private EnfermeroDTO enfermero;
    private HistoriaClinicaDTO historiaC;
    
    public ClienteDetailDTO()
    {
        
    }
    
    
}
