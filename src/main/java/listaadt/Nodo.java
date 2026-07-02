/* Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #42
Fecha: 6 de junio de 2026

----- CLASE ENLACE NODO -----
*/
package listaadt;

public class Nodo {
    private Object dato;
    private Nodo siguiente;

    public Nodo(Object o) {
        this.dato = o;
        this.siguiente = null;
    }

    public Nodo(Object o, Nodo sig) {
        this.dato = o;
        this.siguiente = sig;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object o) {
        this.dato = o;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo sig) {
        this.siguiente = sig;
    }
}
