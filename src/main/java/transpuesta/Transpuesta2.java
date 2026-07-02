/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #33
Fecha: 6 de mayo de 2026

----- CLASE TRANSPUESTA -----
*/
package transpuesta;

import java.io.IOException;
import java.util.Scanner;

public class Transpuesta2 {
    Scanner teclado = new Scanner(System.in);
    int fil, col, operaciones; 
    double matriz[][], transp[][];

    public void meta() {
        System.out.println("\nCalcula LA TRANSPUESTA DE UNA MATRIZ Y COMPROBACIÓN O(n²)");
    }
    
    public int capInt(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese unicamente un numero entero valido.");
            }
        }
    }

    public double capReal(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                return Double.parseDouble(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese unicamente un numero real valido.");
            }
        }
    }

    public String capOpcion(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String entrada = teclado.nextLine().trim().toUpperCase();
            
            if (entrada.equals("S") || entrada.equals("N")) 
                return entrada;
            System.out.println("Error: Opcion no valida. Ingrese estrictamente"
                            + " 'S' o 'N'.");
        }
    }

    public double[][] data(double [][]otraCosa) {
        do fil = capInt("¿Cuantos renglones tiene su matriz?:");
        while (fil < 0); 
        do col = capInt("¿Cuantas columnas hay en su matriz?:");
        while (col < 0);  
        otraCosa = new double[fil][col];

        for (int i=0; i<fil; i++) 
            for (int j=0; j<col; j++) 
                otraCosa[i][j] = capReal("Matriz[" + i + ", " + j + "]:");
        return otraCosa;
    }

    public double [][] calculaTransp(int col, int fil) {
        transp = new double[col][fil];
        operaciones = 0; 
        
        for ( int i=0; i < fil; i++ ) {
            for ( int j=0; j < col; j++ ) {
                transp[j][i] = matriz[i][j];
                operaciones++; 
            }
        } 
        return transp;
    }

    public void mostrar(double [][]mat) {      
        for (int j=0; j < mat.length; j++ ) {
            for (int i=0; i < mat[j].length; i++ ) 
                System.out.print(mat[j][i] + "\t");
            System.out.println();
        }
    }

    public void resultados() {
        System.out.println("\nMatriz Original:\n");
        mostrar(matriz);
        System.out.println("\nMATRIZ TRANSPUESTA:\n");
        mostrar(transp);
        System.out.printf(
            "\n====================================================\n" +
            "       RENDIMIENTO Y COMPLEJIDAD ALGORÍTMICA        \n" +
            "====================================================\n" +
            "Dimensiones evaluadas: %d filas x %d columnas.\n" +
            "Número de asignaciones (intercambios de posición): %d\n" +
            "Demostración de complejidad Teórica:\n" +
            " -> Frecuencia de ejecución del bucle interno\n" +
            "    f(n) = filas * columnas.\n" +
            " -> Si evaluamos una matriz cuadrada de tamaño n x n,\n" +
            "    el algoritmo efectúa exactamente n² asignaciones\n" +
            "    elementales.\n" +
            " -> Por definición de la notación Big-O, al\n" +
            "    omitir constantes de inicialización, su\n" +
            "    desempeño asintótico es estrictamente O(n²).\n" +
            "====================================================\n\n",
            fil, col, operaciones
        );
    }

    public static void main(String []args) throws IOException {
        Transpuesta2 t = new Transpuesta2();
        String resp = "S";

        t.meta();
        while (resp.equalsIgnoreCase("s")) {
            t.matriz = t.data(t.matriz);
            t.transp = t.calculaTransp(t.col, t.fil);
            t.resultados();
            resp = t.capOpcion("¿Quiere trasponer otra matriz? s/n:");
        }        
        System.out.println("\nEjecución finalizada.");
    }   
}
