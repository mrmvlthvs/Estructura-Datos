/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #30
Fecha: 6 de mayo de 2026

----- CLASE FIBONACCI MEDICION -----
*/
package fibonacci;

import java.util.Scanner;
import tools.TimeElapsed;

public class TestFibonacciTime {
    Fibonacci fib = new Fibonacci();
    TimeElapsed timer = new TimeElapsed();
    Scanner sc = new Scanner(System.in);
    long tml; int n;

    void meta(){
        System.out.println("""
                    ================ TEST FIBONACCI TIME ================
                    Programa para medir el tiempo de ejecucion de 1000
                    corridas del calculo de la serie Fibonacci, utilizando
                    la clase TimeElapsed.
                    ======================================================
                    """);
    }

    void data() { 
        System.out.println("EJECUTANDO PROGRAMA FIBONACCI...\n");
        n = fib.data();
    }

    void proceso(){
        fib.imprimirSerie(n);
        System.out.println();
        System.out.println("Ejecutando 1000 corridas del cálculo...");
        for (int i = 0; i < 1000; i++) 
            fib.calcularSerieSilencioso(n);
        timer.calcula();
        tml = timer.te;
    }

    void resultados(){
        System.out.println("\n============== RESULTADO DE LA MEDICION " +
                        "==============");
        timer.resulta();
        System.out.printf("Tiempo en milisegundos: %d ms\n", tml);
        System.out.println("=".repeat(54) + "\n");
    }

    void navegabilidad(){
        boolean continuar = true;
        meta();

        while(continuar){
            boolean rValid = false;

            data();
            proceso();
            resultados();
            while (!rValid){
                System.out.print("¿Desea probar con otro numero? S/n\n>>>");
                String op = sc.nextLine().trim();
                if (op.equalsIgnoreCase("s") || op.isEmpty() || op.isBlank()){
                    continuar = true;
                    rValid = true;
                    System.out.println("=".repeat(54));
                } else if (op.equalsIgnoreCase("n")){
                    continuar = false;
                    rValid = true;
                } else System.out.println("Opcion Invalida. Solo ingrese "
                                        + "\'S\' o \'N\'\nENTER = Si");
            }
        }
    }

    public static void main(String[] args) {
        TestFibonacciTime tft = new TestFibonacciTime();
        boolean continuar = true;
        
        tft.meta();
        while(continuar){
            boolean rValid = false;

            tft.data();
            tft.proceso();
            tft.resultados();
            while (!rValid){
                System.out.print("¿Desea probar con otro numero? S/n\n>>>");
                String op = tft.sc.nextLine().trim();
                if (op.equalsIgnoreCase("s") || op.isEmpty() || op.isBlank()){
                    continuar = true;
                    rValid = true;
                    System.out.println("=".repeat(54));
                } else if (op.equalsIgnoreCase("n")){
                    continuar = false;
                    rValid = true;
                } else System.out.println("Opcion Invalida. Solo ingrese "
                                        + "\'S\' o \'N\'\nENTER = Si");
            }
        }
        System.out.println("=".repeat(54) + "\n\t\tFIN DEL PROGRAMA\n" 
                        + "=".repeat(54) + "\n");
    }
}
