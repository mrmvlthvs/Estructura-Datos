package tarea3;

import java.util.Date;
import java.util.Random;

public class Mensaje implements Mensajeable {
    private String asunto;
    private String contenido;
    private String remitente;
    private String destinatario;
    private Date fechaEnvio;
    private String estado;
    private int prioridad;
    private int idMensaje;

    public Mensaje() {
        Random rndm = new Random();
        this.idMensaje = rndm.nextInt(9999 - 1000 + 1) + 1000;
        this.asunto = "Sin asunto";
        this.contenido = "Sin contenido";
        this.estado = "Borrador";
        this.prioridad = 1;
        this.fechaEnvio = new Date();
    }

    public Mensaje(String asunto, String contenido, String remitente, 
        String destinatario) {
        Random rndm = new Random();
        this.idMensaje = rndm.nextInt(9999 - 1000 + 1) + 1000;
        this.asunto = asunto;
        this.contenido = contenido;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.estado = "Enviado";
        this.prioridad = 2;
        this.fechaEnvio = new Date();
    }
    
    @Override
    public Mensaje crearMensaje(String remitente, String destinatario, 
        String contenido) {
        return new Mensaje("Sin asunto", contenido, remitente, destinatario);
    }

    @Override
    public Mensaje crearMensaje(String asunto, String contenido, 
        String remitente, String destinatario) {
        return new Mensaje(asunto, contenido, remitente, destinatario);
    }

    @Override
    public boolean modificarContenido(String nuevoContenido) {
        if (nuevoContenido == null || nuevoContenido.isBlank()) 
            return false;
        this.contenido = nuevoContenido;
        return true;
    }

    @Override
    public boolean enviar() {
        if (this.remitente == null || this.destinatario == null || 
            this.contenido.isBlank()) 
            return false;
        this.estado = "Enviado";
        this.fechaEnvio = new Date();
        return true;
    }

    @Override
    public boolean eliminar() {
        this.estado = "Eliminado";
        this.contenido = "";
        return true;
    }

    @Override 
    public void marcarLeido() { 
        this.estado = "Leido"; 
    }

    @Override
    public String obtenerPrioridadString() {
        switch(this.prioridad) {
            case 1: return "Baja";
            case 2: return "Media";
            case 3: return "Alta";
            default: return "Prioridad invalida";
        }
    }

    @Override
    public int obtenerPrioridad() { 
        return prioridad; 
    }

    @Override
    public int compararPrioridad(Mensaje m2) {
        return this.prioridad - m2.obtenerPrioridad();
    }

    @Override 
    public String mostrarMensaje() {
        return "ID: " + this.idMensaje + " | Estado: " + this.estado +
                "\nDe: " + this.remitente + " | Para: " + this.destinatario +
                "\nAsunto: " + this.asunto +
                "\nContenido: " + this.contenido + 
                "\nFecha: " + this.fechaEnvio;
    }

    @Override
    public String mostrarVistaPrevia(){
        return  "De: " + this.remitente + " | Para: " + this.destinatario +
                "\nAsunto: " + this.asunto +
                "\nContenido: " + this.contenido;
    }

    public int getID() { return idMensaje; }
    public String getEstado() { return estado; }
    public String getAsunto() { return asunto; }
    
    @Override
    public String leerContenido() { return contenido; }
    
    public String getRemitente() { return remitente; }
    public String getDestinatario() { return destinatario; }
    
    @Override
    public Date obtenerFecha() { return fechaEnvio; }

    public boolean setAsunto(String asunto) {
        if (asunto != null && !asunto.isBlank() && asunto.length() <= 20) {
            this.asunto = asunto;
            return true;
        } else if (asunto != null && asunto.isBlank()) {
            this.asunto = "Sin asunto";
            return true;
        }
        return false;
    }

    public boolean setContenido(String contenido) {
        if (contenido != null && !contenido.isBlank() && 
            contenido.length() <= 200) {
            this.contenido = contenido;
            return true;
        }
        return false;
    }

    public boolean setRemitente(String remitente) {
        if (remitente != null && !remitente.isBlank() && 
            remitente.length() <= 25) {
            this.remitente = remitente;
            return true;
        }
        return false;
    }

    public boolean setDestinatario(String destinatario) {
        if (destinatario != null && !destinatario.isBlank() && 
            destinatario.length() <= 25) {
            this.destinatario = destinatario;
            return true;
        }
        return false;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public void destruirMensaje(){
        this.idMensaje = -1;
        this.contenido = null;
        this.estado = "Eliminado";
        this.prioridad = 0;
        this.fechaEnvio = null;
    }
}