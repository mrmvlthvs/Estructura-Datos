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

----- ALGORITMO RATIONAL implements RATIONALIZABLE ADT -----
*/
package rational;

public class Rational implements Rationalizable {
    private int n;
    private int d;

    public Rational(int n, int d){
        if(d == 0)
        throw new IllegalArgumentException("El denominador no puede ser 0");
        this.n = n;
        this.d = d;
        simplificar();
    }

    public void setX(int n){
        this.n = n;
    }

    public boolean setD(int d){
        if(d == 0) return false;
        this.d = d;
        return true;
    }

    public int getN(){
        return n;
    }
    
    public int getD(){
        return d;
    }

    private void simplificar(){
        int mcd = mcd(n,d);
        n = n/mcd;
        d = d/mcd;

        if(d < 0){
            n = -n;
            d = -d;
        }
    }

    private int mcd(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);

        while(b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // ===== OPERACIONES =====
    public Rational addition(Rational r2){
        int num = n*r2.d + r2.n*d;
        int den = d*r2.d;

        return new Rational(num,den);
    }

    public Rational addition(int num){
        int newNum = n + num*d;
        
        return new Rational(newNum,d);
    }

    public Rational substraccion(Rational r2){
        int num = n*r2.d - r2.n*d;
        int den = d*r2.d;
        
        return new Rational(num,den);
    }

    public Rational substraccion(int num){
        int newNum = n - num*d;
        
        return new Rational(newNum,d);
    }

    public Rational multiplicacion(Rational r2){
        int num = n*r2.n;
        int den = d*r2.d;
        
        return new Rational(num,den);
    }

    public Rational multiplicacion(int num){
        return new Rational(n*num,d);
    }

    public Rational division(Rational r2){
        int num = n*r2.d;
        int den = d*r2.n;
        
        return new Rational(num,den);
    }

    public Rational division(int num){
        return new Rational(n,d*num);
    }

    public Rational exponentiation(int e){
        int num = (int)Math.pow(n,e);
        int den = (int)Math.pow(d,e);
        
        return new Rational(num,den);
    }

    public Rational negation(){
        return new Rational(-n,d);
    }

    // ===== COMPARACIONES =====
    public boolean equals(Rational r2){
        return n*r2.d == r2.n*d;
    }

    public boolean greater(Rational r2){
        return n*r2.d > r2.n*d;
    }

    public boolean less(Rational r2){
        return n*r2.d < r2.n*d;
    }

    public boolean greatEquals(Rational r2){
        return n*r2.d >= r2.n*d;
    }

    public boolean lessEquals(Rational r2){
        return n*r2.d <= r2.n*d;
    }

    public String toString(){
        return n + "/" + d;
    }
}