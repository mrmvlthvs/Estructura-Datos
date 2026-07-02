/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumnos: Marlene Inés Moreno Velázquez
         Josue Abraham Segura Garcia
Exposicion: Hash Map
Fecha: 10 de junio de 2026

----- CLASE DE PRUEBA "PROGRAMA HASH" -----
*/
package exposicion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class ProgramaHash {
    private int size = 11;
    private TablaHash tabla = new TablaHash(size);
    private Scanner sc = new Scanner(System.in);
  
    void meta() {
        String mensaje = """
            ================ SISTEMA HASH - PRUEBA ================
            Programa que permite probar el ADT Tabla Hash manual,
            para registrar personas desde teclado o archivo, buscar
            nombres y visualizar la tabla con encadenamiento.
            =======================================================
            """;
        
        System.out.println(mensaje);
    }

    void data() {
     System.out.println("Inicializando Tabla Hash vacia con "+size+"cubetas\n");
     System.out.println("--- Estructura en memoria lista ---");
    }

    String capturarNombre(String prompt) {
        String aux;
        while (true) {
            System.out.print(prompt);
            aux = sc.nextLine();
            if (!aux.trim().isEmpty()) return aux.trim();
            System.out.println("Error: El nombre no puede estar vacio.");
        }
    }

    void proceso(int option) {
        switch(option) {
            case 1 -> registrarManual();
            case 2 -> registrarDesdeArchivo();
            case 3 -> buscarPersona();
            case 4 -> resultados(); 
            case 5 -> vaciarTabla(); 
        }
    }

    void registrarManual() {
        Persona nuevaPersona = new Persona();
        boolean valido = false;
        
        while (!valido) {
            System.out.print("Ingrese el nombre de la persona: ");
            String nombre = sc.nextLine();
            String msjE = """
                          Error: Nombre invalido. Asegurese de que tenga entre 
                          7 y 50 caracteres y contenga unicamente letras.""";
            
            if (nuevaPersona.setNombre(nombre)) valido = true;
            else System.out.println(msjE);
        }
        tabla.insertar(nuevaPersona);
        System.out.println("Registro insertado correctamente.");
    }

    void registrarDesdeArchivo() {
        JFileChooser selector = new JFileChooser();
        File archivo; FileReader lector; BufferedReader buffer; String linea;
        int contadorExito = 0, contadorFallos = 0;

        System.out.println("\nAbriendo selector de archivos...");
        if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            archivo = selector.getSelectedFile();
            try {
                lector = new FileReader(archivo);
                buffer = new BufferedReader(lector);
                linea = buffer.readLine();
                
                while (linea != null) {
                    if (!linea.trim().isEmpty()) {
                        Persona nuevaPersona = new Persona();
                        if (nuevaPersona.setNombre(linea)) {
                            tabla.insertar(nuevaPersona);
                            contadorExito++;
                        } else {
                            String msjE = """
                                          Fallo de validacion: Se omitio el 
                                          registro invalido: """;
                            System.out.println(msjE + linea.trim() + ".");
                            contadorFallos++;
                        }
                    } linea = buffer.readLine();
                } buffer.close();
                System.out.println("Proceso finalizado. Se cargaron " 
                        + contadorExito + " registros correctamente (" 
                        + contadorFallos + " omitidos).");
            } catch (IOException e) {
                System.out.println("Error al leer archivo: " + e.getMessage());
            }
        } else System.out.println("No se selecciono ningun archivo.");
    }

    void buscarPersona() {
        String nombreBuscado = capturarNombre("\nIngrese el nombre a buscar: ");
        tabla.buscar(nombreBuscado);
    }

    void vaciarTabla() {
        System.out.println("\n===============================================");
        System.out.println("¡ADVERTENCIA! Se eliminaran todos los registros.");
        System.out.println("=================================================");
        System.out.print("¿Esta totalmente seguro de vaciar la tabla? (S/N): ");
        String confirmacion = sc.nextLine().trim().toUpperCase();

        if (confirmacion.equalsIgnoreCase("S")) {
            tabla = new TablaHash(size); 
            System.out.println("\nExito: La Tabla Hash ha sido vaciada.");
        } else System.out.println("\nOperacion cancelada.");
    }

    void resultados() {
        System.out.println("\nMostrando Estado de la Tabla:");
        tabla.mostrarTabla();
    }

    int menuPrincipal() {
        String menu = """
            \nOPCIONES DE TABLA HASH:
            1) Registrar persona (Teclado)
            2) Registrar personas (Archivo TXT)
            3) Buscar persona por nombre
            4) Mostrar estado de la Tabla Hash
            5) Vaciar todos los registros
            6) Salir
            """;
        int opcion = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(menu + "Su eleccion >>> ");
            String aux = sc.nextLine();
            try {
                opcion = Integer.parseInt(aux);
                if (opcion >= 1 && opcion <= 6) valido = true; 
                else System.out.println("Error: Ingrese un numero (1 al 6).");
            } catch (NumberFormatException e) {
              System.out.println("Error: Ingrese solamente un valor numerico.");
            }
        }
        return opcion;
    }

    public static void main(String[] args) {
        ProgramaHash programa = new ProgramaHash();
        int opcion = 0;
        
        programa.meta();
        programa.data();
        while (opcion != 6) { 
            opcion = programa.menuPrincipal();
            if (opcion != 6) programa.proceso(opcion);
        }
        programa.sc.close(); 
    }
}