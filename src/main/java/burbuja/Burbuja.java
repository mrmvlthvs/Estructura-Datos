/* Direccion: \src\main\java\burbuja/Burbuja.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #22
Fecha: 25 de marzo de 2026

----- ORDENAMIENTO BURBUJA -----
*/

package burbuja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import tools.EntryPrompt;

public class Burbuja {
    private EntryPrompt in = new EntryPrompt();
    private String salida;
    private int[] datos = null;   

    void meta() {
        String mensaje = """
                Programa de ordenamiento de datos ingresados mediante
                teclado y/o por archivo de texto.
                """;
        String titulo = "Algoritmo BURBUJA";

        in.outInfo(mensaje, titulo);
    }

    void data() {
        int opcion = menuCaptura();

        switch (opcion) {
            case 0 -> teclado();
            case 1 -> archivo();
            case -1 -> { }
        }
    }

    void teclado() {
        String centinela = "\"parar\"";
        String titulo = "Datos por Teclado";
        String mensaje = "Ingrese el dato entero o escriba " + centinela 
                + " para detener la captura.";
        ArrayList<Integer> lista = new ArrayList<>();

        while (true) {
            Integer dato = isNum(titulo, mensaje);
            
            if (dato == null) 
                break; 
            lista.add(dato);
        }

        datos = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) 
            datos[i] = lista.get(i);
        in.outInfo("Se ingresaron " + datos.length + " datos.", 
                "Captura por teclado exitosa");
    }

    Integer isNum(String titulo, String mensaje) {
        boolean valido = false;
        Integer numero = null;
        
        while (!valido) {
            String aux = in.entryString(titulo, mensaje);
            
            if (aux == null) aux = "";
            if (aux.trim().equalsIgnoreCase("parar")) 
                return null; 
            try {
                numero = Integer.parseInt(aux.trim());
                valido = true;  
            } catch (NumberFormatException e) {
                String msj = "No es un número entero válido ni la palabra de"
                            + " parada. Intente de nuevo.";
                in.outError(msj, "ERROR DE FORMATO");
            }
        }
        return numero;
    }

    void archivo() {
        JFileChooser fileC = new JFileChooser();
        fileC.setDialogTitle("Seleccione el archivo de texto (.txt)");

        if (fileC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileC.getSelectedFile();
            ArrayList<Integer> lista = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(
                new FileReader(archivo))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();

                    if (!linea.isEmpty()) {
                        try {
                            int dato = Integer.parseInt(linea);
                            lista.add(dato);
                        } catch (NumberFormatException e) {
                            in.outError("El dato '" + linea 
                                    + "' no es un número entero.", "ERROR");
                        }
                    }
                }

                datos = new int[lista.size()];
                for (int i = 0; i < lista.size(); i++) 
                    datos[i] = lista.get(i);

                in.outInfo("Se han registrado " + datos.length + " datos.", 
                        "LECTURA EXITOSA");
            } catch (Exception e) {
                in.outAdv("Error al leer el archivo: " + e.getMessage(), 
                        "ERROR");
            }
        } else 
            in.outError("No se seleccionó ningún archivo", "ADVERTENCIA");
    }

    void ordenar() {
        int nd = datos.length;
        int comparacion = 0;
        int intercambio = 0;

        for (int i = 0; i < nd - 1; i++) {
            for (int j = 0; j < nd - i - 1; j++) {   
                comparacion++;
                if (datos[j] > datos[j + 1]) {
                    int temp = datos[j];
                    datos[j] = datos[j + 1];
                    datos[j + 1] = temp;
                    intercambio++;
                }
            }
        }
        salida = "Número de comparaciones: " + comparacion
                + "\nNúmero de intercambios: " + intercambio;

        in.outInfo(salida, "ORDENAMIENTO EXITOSO");
    }

    void proceso(int opcion) {
        switch (opcion) {
            case 0 -> {
                data();
                if (datos != null && datos.length > 0) ordenar();
            }
            case 1 -> {
                if (datos == null || datos.length == 0) 
                    in.outAdv("Capture los datos antes de imprimir", 
                            "Sin datos para mostrar");
                else {
                    int medio = menuImpresion();
                    if (medio != -1) imprimir(medio);
                }
            }
        }
    }

    void resultados() {
        in.outInfo("Ejecución finalizada de manera ordenada.", 
                "SISTEMA CERRADO");
    }

    int menuImpresion() {
        String opciones[] = {"Imprimir en terminal", "Imprimir en archivo"};
        String titulo = "Imprimir datos capturados";
        String mensaje = "Elija alguna de las opciones";

        return in.entryOption(mensaje, titulo, opciones);
    }

    void imprimir(int medio) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < datos.length; i++) 
            resultado.append(datos[i]).append("\n");
        switch (medio) {
            case 0 -> {   
                System.out.println("===== DATOS INGRESADOS Y ORDENADOS =====");
                System.out.println(salida + "\n" + "=".repeat(54));
                System.out.print(resultado);
                in.outInfo("Revise los datos en la terminal.", 
                        "IMPRIMIR RESULTADOS");
            }
            case 1 -> crearArchivo(resultado); 
        }
    }

    void crearArchivo(StringBuilder resultado) {
        JFileChooser fileC = new JFileChooser();
        fileC.setDialogTitle("Guardar archivo de resultados");
        int seleccion = fileC.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileC.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".txt")) 
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo))) {
                if (resultado.length() > 0) 
                    bw.write("=".repeat(54) + "\n" 
                            + salida + "\n" + "=".repeat(54) + "\n"
                            + resultado.toString());
                in.outInfo("Archivo guardado correctamente en:\n" 
                        + archivo.getAbsolutePath(), "ARCHIVO RESULTADOS");
            } catch (IOException e) {
                in.outError("Ocurrió un error al crear el archivo: " 
                            + e.getMessage(), "ERROR");
            }
        } else 
            in.outAdv("Guardado cancelado por el usuario.", "ADVERTENCIA");
    }

    int menu() {
        String opciones[] = {"Capturar datos", "Imprimir resultados", "Salir"};
        String mensaje = "Ingrese la acción que desee realizar.";
        String titulo = "Opciones - Algoritmo BURBUJA";

        return in.entryOption(mensaje, titulo, opciones);
    }

    int menuCaptura() {
        String opciones[] = {"Teclado", "Archivo"};
        String mensaje = "Ingrese el medio por el cual capturará los datos.";
        String titulo = "Capturar - Algoritmo BURBUJA";

        return in.entryOption(mensaje, titulo, opciones);
    }

    void navegabilidad() {
        meta();
        int opcion = 0;
        
        while (opcion != 2 && opcion != -1) {
            opcion = menu();
            if (opcion != 2 && opcion != -1) 
                proceso(opcion);
        }
        resultados();
    }

    public static void main(String args[]) {
        new Burbuja().navegabilidad();
    }
}