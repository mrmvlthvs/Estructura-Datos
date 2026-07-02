/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\rational\TestRational.java
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

----- ALGORITMO PRUEBA RATIONAL ADT -----
*/
package rational;
import tools.EntryPrompt;

public class TestRational {
    EntryPrompt entry = new EntryPrompt();
    int n1, d1, n2, d2, op, e;
    Rational r1, r2, resultado;
    boolean eq;

    void meta(){
        entry.outInfo("""
        Programa de prueba del ADT Rational
        Permite realizar operaciones entre fracciones
        ""","RATIONAL TEST");
    }

    void data1() {
        boolean stopa = false;

        while (!stopa) stopa = fracc1();
        r1 = new Rational(n1, d1);
    }

    void data2(){
        boolean stopb = false;

        while (!stopb)  stopb = fracc2();
        r2 = new Rational(n2, d2);
    }

    boolean fracc1() {
        boolean isValid = false, isInd = true;

        while (!isValid || isInd) {
            n1 = entry.entryInt(0, "Fraccion 1", 
                                "Ingrese numerador");
            d1 = entry.entryInt(0, "Fraccion 1", 
                                "Ingrese denominador");

            if (n1 != 0) {
                isValid = true;
                if (d1 != 0) isInd = false;
                else entry.outError("El denominador no puede ser 0.", 
                            "INDETERMINACION");
            } else entry.outError("Si el numerador es 0, toda la fraccion es "
                        +"igual a 0.", "FRACCION CON VALOR NULO");
        }
        return true;
    }

    boolean fracc2() {
        boolean isValid = false, isInd = true;

        while (!isValid || isInd) {
            n2 = entry.entryInt(0, "Fraccion 2", 
                                "Ingrese numerador");
            d2 = entry.entryInt(0, "Fraccion 2", 
                                "Ingrese denominador");
            if (n2 != 0) {
                isValid = true;
                if (d2 != 0) isInd = false;
                else entry.outError("El denominador no puede ser 0.", 
                            "INDETERMINACION");
            } else entry.outError("Si el numerador es 0, toda la fraccion es "
                        +"igual a 0.", "FRACCION CON VALOR NULO");
        }
        return true;
    }

    void operacion(){
        String opciones[] = {"Sumar", "Restar", "Multiplicar", "Dividir",
                            "Exponente", "Negacion", "Comparar"};
        op = entry.entryOption("Seleccione operacion","OPERACIONES",opciones);

        proceso(op);
    }

    void proceso(int seleccion){
        switch(seleccion){
            case 0 -> {data2(); resultado = r1.addition(r2);}
            case 1 -> {data2(); resultado = r1.substraccion(r2);}
            case 2 -> {data2(); resultado = r1.multiplicacion(r2);}
            case 3 -> {data2(); resultado = r1.division(r2);}
            case 4 -> { e = entry.entryInt(2,"Exponente",
                        "Ingrese exponente");
                        resultado = r1.exponentiation(e);}
            case 5 -> resultado = r1.negation();
            case 6 -> {data2(); eq = r1.equals(r2);}
        }
    }

    void resultados(){
        switch(op){
            case 0 -> {entry.outPlain(r1+" + "+r2+" = "+resultado,
                        "RESULTADO");}
            case 1 -> {entry.outPlain(r1+" - "+r2+" = "+resultado,
                        "RESULTADO");}
            case 2 -> {entry.outPlain(r1+" * "+r2+" = "+resultado,
                        "RESULTADO");}
            case 3 -> {entry.outPlain(r1+" / "+r2+" = "+resultado,
                        "RESULTADO");}
            case 4 -> {entry.outPlain(r1+"^"+e+" = "+resultado,
                        "RESULTADO");}
            case 5 -> {entry.outPlain("Negacion de "+r1+" = "
                    +resultado,"RESULTADO");}
            case 6 -> {entry.outPlain(r1+" == "+r2+" ? "+ eq,"COMPARACION");}
        }
    }

    void navegabilidad() {
        boolean continuar = true;
        String[] opciones = {"Sí", "No"};

        while (continuar) {
            data1();          // Primera fracción
            operacion();      // Segunda fracción (si aplica) y operación
            resultados();     // Muestra el resultado
            int reload = entry.entryOption("¿Desea realizar otra operación?", 
                                            "Continuar", opciones);
            if (reload == 1) continuar = false;
        }
        entry.outInfo("Programa finalizado.", "Fin del programa");
    }

    public static void main(String[] args) {
        TestRational test = new TestRational();

        test.meta();
        test.navegabilidad(); 
    }
}