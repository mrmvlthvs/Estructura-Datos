/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #34
Fecha: 6 de mayo de 2026

----- CLASE FACTORIAL -----
*/
package factorial;

import java.util.Scanner;
import java.math.BigInteger;
public class Factorial {
    Scanner sc = new Scanner(System.in);
    int num; BigInteger result;

    void meta(){
        String mensaje = """
                ============ CALCULA TABLA DE FACTORIALES ============
                | Programa que permite calcular el factorial de un   |
                | numero o varios numeros.                           |
                ======================================================
                """;
        
        System.out.println(mensaje);
    }

    void data(){
        String aux; boolean isNum = false;
        do {
            System.out.print("Ingrese el tamano de la tabla >>>");
            aux = sc.nextLine();
            try {
                num = Integer.parseInt(aux);
                if (num < 0) {
                    System.out.println("NO es valido un numero negativo");
                    isNum = false;
                } else isNum = true;
            } catch (NumberFormatException e) {
                System.out.println("""
                    ERROR: Asegurese de ingresar solo numeros enteros 
                            NO negativos""");
                isNum = false;
            }
        } while(!isNum);
    }

    void proceso() {
        result = BigInteger.ONE;
        for (int i = 2; i <= num; i++) 
            result = result.multiply(BigInteger.valueOf(i));
    }

    void resultados(){
        System.out.println("=".repeat(16)+" RESULTADO FACTORIAL "
                        +"=".repeat(17));
        System.out.println("Factorial de " + num + "!");
        System.out.println("\tR= " + result);
    }

    boolean navegabilidad(){
        boolean isValid = false, ret = false;
        String op, mess = "¿Desea probar con otro numero? S/n >>> ";
        
        while(!isValid){
            System.out.print(mess);
            op = sc.nextLine().trim();
            if (op.equalsIgnoreCase("s") || op.isEmpty() || op.isBlank()){
                    isValid = true; ret = true; 
                    System.out.println("=".repeat(54));
                    return true;
            } else if (op.equalsIgnoreCase("n")){
                isValid = true; ret = false;
                return false;
            } else System.out.println("Opcion Invalida. Solo ingrese "
                                    + "\'S\' o \'N\'");
        }
        return ret;
    }

    // SOLO PARA IMPRIMIR LINEAS DIVISORAS EN LA CONSOLA
    void lineBR(){
        System.out.println("=".repeat(54));
    }
    public static void main(String[] args){
        Factorial fct = new Factorial();
        boolean run = true;

        fct.meta();
        while (run){
            fct.data();
            fct.lineBR();
            fct.proceso();
            fct.lineBR();
            fct.resultados();
            fct.lineBR();
            run = fct.navegabilidad();
        }
        fct.lineBR();
        System.out.println("\t\tFIN DEL PROGRAMA");
        fct.lineBR();
    }
}