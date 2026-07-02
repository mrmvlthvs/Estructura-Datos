/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumnos: Marlene Inés Moreno Velázquez
         Josue Abraham Segura Garcia
Exposicion: Hash Map
Fecha: 10 de junio de 2026

----- CLASE "NODO" PARA MANEJAR LAS COLISIONES -----
*/
package exposicion;

public class NodoPersona {
    
    private Persona dato;
    private NodoPersona siguiente;

    public NodoPersona(Persona dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Persona getDato() {
        return dato;
    }

    public NodoPersona getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPersona siguiente) {
        this.siguiente = siguiente;
    }
}