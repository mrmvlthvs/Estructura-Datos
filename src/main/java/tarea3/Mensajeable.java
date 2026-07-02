package tarea3;

import java.util.Date;

public interface Mensajeable {
    Mensaje crearMensaje(String remitente, String destinatario, String contenido);
    Mensaje crearMensaje(String asunto, String contenido, String remitente, String destinatario);
    String leerContenido();
    boolean modificarContenido(String nuevoContenido);
    boolean enviar();
    boolean eliminar();
    void marcarLeido();
    Date obtenerFecha();
    String obtenerPrioridadString();
    int obtenerPrioridad();
    int compararPrioridad(Mensaje m2);
    String mostrarMensaje();
    String mostrarVistaPrevia();
    void destruirMensaje();
}
