/* Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #35
Fecha: 6 de mayo de 2026

----- CLASE COMPUTE FACTORIAL de LIANG -----
*/
package factorial;

import java.util.Scanner;

public class ComputeFactorial {
    int num; 
    long result;
    private Scanner scn = new Scanner(System.in);

    void meta(){
        String msj = """
                ===================== COMPUTE FACTORIAL =======================
                Programa que permite calcular el factorial de un numero entero.
                Ejemplo sacado y modificado del libro de Daniel Liang.
                """;

        System.out.println(msj);
    }

    void data(){
        boolean isValid = false;
        while (!isValid){
            System.out.print("Ingrese un numero entero positivo (0 - 20): ");
            num = isNum();
            if (num < 0) 
                System.out.println("Ingrese unicamente valores positivos.");
            else if (num > 20) 
                System.out.println("El limite maximo es 20. Intente de nuevo.");
            else isValid = true;
        }
    }

    int isNum(){
        boolean run = true;
        int result = 0;
        while (run){
            try {
                String aux = scn.nextLine();
                result = Integer.parseInt(aux);
                run = false;
            } catch (NumberFormatException e){
                System.out.print("Ingrese unicamente un numero entero valido: ");
            }
        }
        return result;
    }

    boolean isOption(String msj){
        boolean run = true, result = true;
        String aux;
        while (run){
            System.out.print(msj);
            aux = scn.nextLine().trim();
            if (aux.equalsIgnoreCase("S")){
                run = false;
                result = true;
            } else if (aux.equalsIgnoreCase("N")){
                run = false;
                result = false;
            } else System.out.println("Ingrese unicamente 'S' o 'N'.");
        }
        return result;
    }

    long factorial(int n){
        if (n == 0) return 1;
        else return n * factorial(n - 1);
    }

    void resultado(){
        String msj = "El resultado de factorial de " + num + " es = " 
                    + factorial(num);
        System.out.println(msj);
    }

    void navegabilidad(){
        String msj = "¿Desea probar con otro numero? S/n >>> ";
        boolean run = true;
        meta();
        while(run){
            data();
            resultado();
            run = isOption(msj);
        }
        scn.close();
        System.out.println("FIN DEL PROGRAMA.");
    }

    public static void main(String[] args){
        ComputeFactorial cf = new ComputeFactorial();
        cf.navegabilidad();
    }
}