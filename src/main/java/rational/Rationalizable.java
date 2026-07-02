/*
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #14
Fecha: 4 de marzo de 2026

----- INTERFACE RATIONAL ADT -----
*/
package rational;

public interface Rationalizable {
    Rational addition(Rational r2);
    Rational addition(int num);
    Rational substraccion(Rational r2);
    Rational substraccion(int num);
    Rational multiplicacion(Rational r2);
    Rational multiplicacion(int num);
    Rational division(Rational r2);
    Rational division(int num);
    Rational exponentiation(int e);
    Rational negation();
    boolean equals(Rational r2);
    boolean greater(Rational r2);
    boolean less(Rational r2);
    boolean greatEquals(Rational r2);
    boolean lessEquals(Rational r2);
}