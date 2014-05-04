/*
 * Entrega.java
 *
 * Created on 26 de octubre de 2006, 05:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ar.com.jmc.webplatform.base.model;

/**
 *
 * @author Juan Manuel
 */
public class Entrega {
    
    private int nroEquipo;
    private String nroViajeLocalidad_id;
    private int horaEntrega;
    private int fechaEntrega;
    private int localidad_id;
    private String Localidad;
    private int nroViaje;
    private String viajeLocalidad;
    
    
    /** Creates a new instance of Entrega */
    public Entrega() {
    }

    public int getNroEquipo() {
        return nroEquipo;
    }

    public void setNroEquipo(int nroEquipo) {
        this.nroEquipo = nroEquipo;
    }

    public String getNroViajeLocalidad_id() {
        return nroViajeLocalidad_id;
    }

    public void setNroViajeLocalidad_id(String nroViajeLocalidad_id) {
        this.nroViajeLocalidad_id = nroViajeLocalidad_id;
    }

    public int getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(int horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public int getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(int fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getLocalidad_id() {
        return localidad_id;
    }

    public void setLocalidad_id(int localidad_id) {
        this.localidad_id = localidad_id;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }

    public int getNroViaje() {
        return nroViaje;
    }

    public void setNroViaje(int nroViaje) {
        this.nroViaje = nroViaje;
    }

    public String getViajeLocalidad() {
        return viajeLocalidad;
    }

    public void setViajeLocalidad(String viajeLocalidad) {
        this.viajeLocalidad = viajeLocalidad;
    }
    
}
