/*
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #17
Fecha: 18 de marzo de 2026

----- ALGORITMO INTERFACE CONJUNTABLE ADT -----
*/
package conjunto;

public interface Conjuntable {
    void add(int e);
    void remove(int e);
    boolean contains(int e);
    Conjunto union(Conjunto c);
    Conjunto intersection(Conjunto c);
    Conjunto difference(Conjunto c);
    int size();
}
