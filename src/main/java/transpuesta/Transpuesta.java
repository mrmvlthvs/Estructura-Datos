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

import java.io.*;
import java.util.*;
import tools.MiscelaniusGUI;

class  Transpuesta {
  Scanner teclado = new Scanner(System.in);
  int fil, col, operaciones;
  double matriz[][];
  double transp[][];
  MiscelaniusGUI m = new MiscelaniusGUI();
   
  public void inicio() {
    System.out.println("\n\t Calcula LA TRANSPUESTA DE UNA MATRIZ");
  }

  public double[][] entradas(double [][]otraCosa) {
    do fil = Integer.parseInt(m.capInt("¿Cuantos renglones tiene su matriz?:"));
    while (fil < 0); 
    do col = Integer.parseInt(m.capInt("¿Cuantas columnas hay en su matriz?:"));
    while (col < 0); 
    otraCosa = new double[fil][col];
    for (int i=0; i<fil; i++)    
      for (int j=0; j<col; j++) 
        otraCosa[i][j]=m.capReal("Matriz[" +i+ ", "+j+ "]:");
    return otraCosa;
  }
   
  public double [][] calculaTransp(int col, int fil) {
    transp = new double[col][fil];
    for ( int i=0; i < fil; i++ ) {
      for ( int j=0; j < col; j++ ){
        transp[j][i] = matriz[i][j];
        operaciones++;
      }
    }
    return transp;
  }
   
  public void mostrar(double [][]mat) {      //Muestra matriz transpuesta
    for (int j=0; j < mat.length; j++ ) {
      for (int i=0; i < mat[j].length; i++ ) 
        System.out.print(mat[j][i] + ", ");
      System.out.println();
    }
  }

  public void resultados() {
    System.out.println("\nMatriz Original\n");
    mostrar(matriz);
    System.out.println("\nMATRIZ TRANSPUESTA\n");
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
    Transpuesta t=new Transpuesta();
    int resp = 'S';
      
    t.inicio();
    while (resp == 's' || resp == 'S') {
      t.matriz = t.entradas(t.matriz);
      t.transp = t.calculaTransp(t.col, t.fil);
      t.resultados();
      System.out.print("Quiere trasponer otra matriz? s/n:");
      resp = System.in.read();
      System.in.skip(2);
    }        
  }   
}   
