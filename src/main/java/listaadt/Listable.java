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

----- INTERFAZ LISTABLE -----
*/
package listaadt;

public interface Listable {
    String toString();
    void add(Object o);
    void add(int i, Object o);
    Object remove(int i);
    boolean remove(Object o);
    Object get(int i);
    boolean contains(Object o);
    int size();
    boolean isEmpty();
    Lista concat(Lista l);
    boolean isEquals(Lista l);
    Lista subLista(int d, int h);
}
