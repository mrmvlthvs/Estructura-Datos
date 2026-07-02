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

----- CLASE FIBONACCI NORMAL -----
*/
package fibonacci;

import java.util.Scanner;

public class Fibonacci {
    Scanner t = new Scanner(System.in); 

    void goal(){ // <----- META
        String message = """
                \n
                ================= FIBONACCI PROGRAMA =================
                Programa para imprimir una serie de numeros fibonacci.
                Famosa por la pelicula \"Codigo D' Vinci\".
                ======================================================
                """;
        
        System.out.println(message);
    }

    int data(){
        String message = """
                Ingrese el numero correspondiente a la cantidad de 
                numeros deseados en la serie.
                """;
        boolean isValid = false;
        String en; int cant = 0;

        while(!isValid){
            System.out.print(message + "\n>>>");
            en = t.nextLine();
            if (en.isBlank() || en.isEmpty())
                System.out.println("\nERROR: Entrada vacia.");
            else {
                isValid = isNum(en);
                if(isValid) 
                    cant = Integer.parseInt(en);
            }
        }
        return cant;
    }

    boolean isNum(String cad) {      
        try {                          
            int tr = Integer.parseInt(cad);       
            return true;                 
        } catch (NumberFormatException nfe) {
            System.out.println("\nERROR: Solo se admiten numero enteros\n"
                            + "=".repeat(54));
            return false;
        }
    }

    void imprimirSerie(int n) {
        long fnm1, fnm2, fn;
        fnm1 = fnm2 = fn = 0;
        if (n == 0) {
            System.out.println("-".repeat(19)+" SERIE VACIA "+"-".repeat(20));
            return;
        }
        if(n < 0) {
            n = Math.abs(n);
            
            System.out.println("Se ingreso un numero negativo.\n"
                            + "Se tomara en cuenta el valor absoluto.");
        }
        System.out.print(fnm2);
        if (n >= 2) {
            fnm1 = 1;
            for (int i = 2; i <= n; i++) {
                fn = fnm1 + fnm2;
                fnm2 = fnm1;
                fnm1 = fn;
                System.out.print(", " + fn);
            }
        }
        System.out.println();
    }

    // Calcula la serie de Fibonacci hasta el término n, SIN imprimir nada.
    // Metodo para el calculo de TIME ELAPSED.
    void calcularSerieSilencioso(int n) {
        if (n <= 0) return;
        long fnm1 = 0, fnm2 = 0, fn = 0;
        if (n >= 2) fnm1 = 1;
        for (int i = 2; i <= n; i++) {
            fn = fnm1 + fnm2;
            fnm2 = fnm1;
            fnm1 = fn;
        }
    }

    boolean nav(){
        String message = """
                \n¿Desea desplegar otra serie (S) o salir (N)? S/n
                default: ENTER = S
                """;
        boolean repeat = true, res = false;
    
        while(repeat){
            System.out.print(message + "\n>>>");
            String r = t.nextLine();
            // Si el usuario ingresa S o solo da ENTER
            if(r.equalsIgnoreCase("s") || r.isEmpty() || r.isBlank()){
                repeat = false;
                res = true;
                System.out.println("=".repeat(54));
            } // Si el usuario ingresa N
            else if(r.equalsIgnoreCase("n"))
                repeat = false;
            // Si el usuario ingresa un caracter diferente
            else System.out.println("ERROR: Opcion invalida! " 
                                    + "Asegurese de usar solo S o N.");
        }
        return res;
    }

    public static void main(String args[]) {
        Fibonacci f = new Fibonacci();
        boolean run = true;

        f.goal();
        while (run){
            f.imprimirSerie(f.data());
            run = f.nav();
        }
        System.out.println("=".repeat(19) + " FIN DEL PROGRAMA " 
                        + "=".repeat(19));
    }
}
