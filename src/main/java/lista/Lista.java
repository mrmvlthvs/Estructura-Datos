/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\lista\Lista.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #20
Fecha: 20 de marzo de 2026

----- ALGORITMO LISTA -----
*/
package lista;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lista {
  String name[] = new String [25];  // cadenas nulas
  Scanner tecla = new Scanner(System.in);
  
  void meta() {
     System.out.println("Para  desplegar una lista de nombres");
     System.out.println("Del grupo B de estructuras de datos");
     System.out.println("Usando un lazo iterativo (for-each)");
  }
  
  void listaArchivos(String path){
    File canal = new File(path);
    File []lista = canal.listFiles();
    String l="Archivos presentes en el subdirectorio de trabajo:";
    
    l+= path+"\n\n"; 
    for(int i = 0; i < lista.length;i++)  // lazo contado
      l = l + lista[i]+"\n";
    System.out.println(l);
  }
  
  String data() {
     String nombre = null;

     do {  // lazo repeticional
       System.out.print("Deme el nombre del archivo o <enter> :");
       nombre = tecla.nextLine();
       if (nombre.equals("")) listaArchivos(".");
     } while(nombre.length() <= 0);
     return nombre;
  }
  
  boolean dataArch(String arch) {
     Scanner archi = null;
     File f = null;
     boolean flag = false;
     
     try {
       f = new File(arch);
       int i = 0;
       
       if (f.exists() && f.canRead()) {
         archi = new Scanner(f);  // abre archivo de datos     
         while(archi.hasNextLine() && i < name.length) // lazo condicional 
           name[i++] = new String(archi.nextLine()); // lee datos del archivo
         flag = true;    
       }
     } catch (FileNotFoundException fnf) {
         System.out.println("No existe el archivo: " +arch);
       } finally {  if (archi != null) archi.close();  }    
       // cierra archivo de datos
     return flag;  
  }
  
  // no hay calculos
  
  void display() { // lazo iterativo for-each para recorrer el arreglo
    for (String n:name) // lazo iterativo
      if (n != null) System.out.println(n);
  }
  
  public static void main(String args[]) {
     Lista l = new Lista();
     String nom = null;
     boolean sigue = false;
     String resp = "S";
     
     l.meta();
     while (resp.toUpperCase().charAt(0) == 'S') {  // lazo condicional
       do {      //lazo repeticional
         nom= l.data();
         sigue = l.dataArch(nom);
       } while (!sigue);  
       l.display();
       do {
         System.out.print("Quiere desplegar otro archivo? s/n:");
          resp = l.tecla.nextLine();
       } while (resp.length() <=0);
     }  
  } 
}

