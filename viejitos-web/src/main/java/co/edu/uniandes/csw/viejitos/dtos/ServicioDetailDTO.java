/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viejitos.dtos;

import co.edu.uniandes.csw.viejitos.entities.ServicioEntity;
import java.util.List;

/**
 * Clase que extiende de {@link ServicioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del servicio vaya a la documentacion de {@link ServicioDTO}
 * 
 * <pre>
 *   {
 *      "tipo": number,
 *      "fecha": date,
 *      "hora": string,
 *      "descripción": string,
 *      "finalizado": boolean,
 *      "quejas":[],
 *      "pagoInicial": pagoDTO,
 *      "pagoFinal": pagoDTO,
 *      "factura": facturaDTO,
 *      "cliente": clienteDTO,
 *      "calificacion": calificacionDTO,
 *      "enfermero": enfermeroDTO
 *   }
 * </pre>
 * 
 * Por ejemplo un servicio se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "tipo": 1,
 *      "fecha: "12/02/2018",
 *      "hora": "8:37",
 *      "descripción": "Acompañamiento a cita médica",
 *      "finalizado": false
 *      "quejas": [{"reclamo": "El enfermero tenia una actitud muy grosera.",
 *      "resuelto: true}]
 *      "pagoInicial": {"medio": "Efectivo", "pagado": TRUE, "fechaLimite": 12/04/18, "valor": 150000},
 *      "pagoFinal": null,
 *      "factura": {"costoTotal": 120100, "fechaExpedicion": 12/04/18, "descripcion": "Acompanar al banco", "nombreCliente": "Felipe Escobar", "ccCliente": 1234567, "servicioPrestado": "Acompanamiento", "nombreEmpresa": "Hospital"},
 *      "cliente": {"id": 12345, "nombre: "John Doe", "login": "johndoe23", "contrasena": "jd124", "estado": 1, "tipo": 1},
 *      "calificacion": {"id":209873, "puntaje":4.5, "comentario":"El enfermero fue grosero conmigo, se rehuso a prestarme un servicio adecuado", "tipoCalificador":"cliente", "loginCalificador":"af.ramirez", "tipoCalificado":"enfermero", "loginCalificado":"ma.marulanda",},
 *      "enfermero": {"id":2678389, "nombre":"Juan Espitia", "cv":"./documents/juanHDV.pdf", "login":,"js.espitia" "contrasenia":"a89ess0909", "tipo":"Enfermero",}
 *   }
 *
 * </pre>
 * 
 * @author c.gomezs
 */
public class ServicioDetailDTO extends ServicioDTO{
    
    private List<QuejaDTO> quejas;
    
    private PagoDTO pagoInicial;
    
    private PagoDTO pagoFinal;
    
    private FacturaDTO factura;
    
    private ClienteDTO cliente;
    
    private CalificacionDTO calificacion;
    
    private EnfermeroDTO enfermero;

    /**
     * Constructor por defecto
     */
    public ServicioDetailDTO()
    {
        super();
    }
    
    /**
    * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
    * la entidad que viene de argumento.
    * @param ServicioEntity: Es la entidad que se va a convertir a DTO
     */
    public ServicioDetailDTO( ServicioEntity entity )
    {
        super(entity);
        
        if(entity!=null)
        {
        
        }
    }
        
    /**
    * Convertir DTO a Entity
    * @return Un Entity con los valores del DTO
    */
    public ServicioEntity toEntity( )
    {
        ServicioEntity entity = new ServicioEntity( );
	
        return entity;
    }
    
    /**
     * @return lista de quejas
     */
    public List<QuejaDTO> getQuejas() {
        return quejas;
    }

    /**
     * @param quejas nueva lista de quejas
     */
    public void setQuejas(List<QuejaDTO> quejas) {
        this.quejas = quejas;
    }

    /**
     * @return el pago inicial
     */
    public PagoDTO getPagoInicial() {
        return pagoInicial;
    }

    /**
     * @param pagoInicial nuevo pago inicial
     */
    public void setPagoInicial(PagoDTO pagoInicial) {
        this.pagoInicial = pagoInicial;
    }

    /**
     * @return el pago final
     */
    public PagoDTO getPagoFinal() {
        return pagoFinal;
    }

    /**
     * @param pagoFinal el nuevo pago final
     */
    public void setPagoFinal(PagoDTO pagoFinal) {
        this.pagoFinal = pagoFinal;
    }

    /**
     * @return la factura
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * @param factura la nueva factura
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    /**
     * @return el cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente nuevo cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return la clasificación
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion nueva clasificación
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return el enfermero
     */
    public EnfermeroDTO getEnfermero() {
        return enfermero;
    }

    /**
     * @param enfermero nuevo enfermero
     */
    public void setEnfermero(EnfermeroDTO enfermero) {
        this.enfermero = enfermero;
    }
    
}
