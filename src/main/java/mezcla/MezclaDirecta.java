/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #28
Fecha: 20 de abril de 2026

----- CLASE MEZCLA DIRECTA -----
*/
package mezcla;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;
import tools.EntryPrompt;

public class MezclaDirecta {
    private EntryPrompt ep = new EntryPrompt();
    private int dataList[] = new int[100]; 
    private int counting = 0;  
    private File file;
    private int comparaciones;
    private int movimientos; 

    // 1. meta()
    void goal() { 
        String title = "MEZCLA DIRECTA";
        String message = """
            Programa que permite ordenar datos ingresados mediante teclado
            o archivo mediante el metodo de ordenamiento "Mezcla directa".
            Reporta el numero de comparaciones y movimientos realizados.
            """;

        ep.outPlain(message, title);
    }

    // 2. data()
    void regisMenu() {
        String title = "ORIGEN DE DATOS";
        String msg = "Seleccione el medio de origen de los datos a ingresar";
        String options[] = {"Teclado", "Archivo"};
        int option = ep.entryOption(msg, title, options);
        
        switch(option) {
            case 0 -> keyboard();
            case 1 -> file();
            default -> { return; }
        } process(); 
    }

    // 2.1. teclado() 
    void keyboard() { 
        String title = "DATOS POR TECLADO";
        String msg = "Ingrese un numero entero (maximo 100 datos totales).\n"
                    + "Para finalizar la captura, escriba 'parar'.";
        int agregados = 0;

        while (counting < dataList.length) {
            Integer dato = isNum(title, msg);
            if (dato == null) break; 
            
            dataList[counting] = dato;
            counting++; agregados++;
        }
        if (counting == dataList.length) 
            ep.outAdv("Limite maximo de datos alcanzado (100).", "SIN ESPACIO");
        ep.outInfo("Se agregaron " + agregados + " nuevos datos.", "EXITO");
    }

    // 2.a. isNum(titulo, mensaje)
    Integer isNum(String title, String message) {
        while (true) {
            String input = ep.entryString(title, message);
            
            if (input == null) input = "";
            String aux = input.trim();
            if (aux.equalsIgnoreCase("parar")) return null; 
            try {
                return Integer.parseInt(aux);
            } catch (NumberFormatException e) {
                ep.outError("No es un numero entero valido ni la palabra de "
                            + "parada. Intente de nuevo.", "ERROR DE FORMATO");
            }
        }
    }

    boolean fileOpen() {
        String title = "ABRIR ARCHIVO";
        String msg = "Seleccione el archivo con los datos a ordenar";
        JFileChooser jfc = new JFileChooser();
        
        jfc.setDialogTitle(msg);
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
            ep.outInfo("Archivo abierto correctamente.", title);
            return true;
        } else {
            ep.outAdv("No se selecciono ningun archivo.", title);
            return false;
        }
    }

    // 2.2. archivo() 
    void file() { 
        if (!fileOpen()) return;
        
        int agregados = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext() && counting < dataList.length) {
                String token = scanner.next();
                try {
                    dataList[counting] = Integer.parseInt(token);
                    counting++; 
                    agregados++;
                } catch (NumberFormatException e) {
                    ep.outError("Dato no numerico ignorado: "+ token, "AVISO");
                }
            }
            if (scanner.hasNext() && counting == dataList.length) 
                ep.outAdv("Limite (100) alcanzado. Restantes ignorados.", 
                        "LIMITE ALCANZADO");
            if (agregados == 0) 
                ep.outError("No se encontraron numeros enteros.", "SIN DATOS");
            else 
                ep.outInfo("Se agregaron " + agregados + " numeros.", "EXITO");
                
        } catch (IOException e) {
            ep.outError("Error de lectura: " + e.getMessage(), "ERROR");
        }
    }

    void emptyData() {
        String title = "VACIAR REGISTROS";
        String msg = "¿Esta totalmente seguro de vaciar la estructura?\n"
                    + "Esta accion eliminara todos los datos acumulados.";
        String options[] = {"Si, vaciar", "Cancelar"};
        
        int sel = ep.entryOption(msg, title, options);
        if (sel == 0) {
            counting = 0;
            ep.outInfo("Los registros han sido eliminados.", title);
        } else 
            ep.outAdv("Operacion cancelada. Los datos siguen a salvo.", title);
    }

    // 3. proceso(opcion)
    void printMenu() {
        String title = "DESTINO DE RESULTADOS";
        String msg = "Seleccione el medio de destino de los datos a imprimir";
        String options[] = {"Terminal", "Archivo"};
        int option = ep.entryOption(msg, title, options);
        
        switch(option) {
            case 0 -> printCmd();
            case 1 -> printFile();
            default -> { return; }
        }
    }

    // 3.1. ordenar() -> Algoritmo interno de mezcla recursiva
    private int[] mezclar(int[] array) {
        if (array.length <= 1) return array;
        int medio = array.length / 2;
        int[] izquierda = Arrays.copyOfRange(array, 0, medio);
        int[] derecha = Arrays.copyOfRange(array, medio, array.length);
        
        izquierda = mezclar(izquierda);
        derecha = mezclar(derecha);
        int i = 0, j = 0, k = 0;

        while (i < izquierda.length && j < derecha.length) {
            comparaciones++; 
            if (izquierda[i] <= derecha[j]) {array[k] = izquierda[i]; i++;} 
            else {array[k] = derecha[j]; j++;}
            movimientos++; k++;
        }
        while (i < izquierda.length) {
            array[k] = izquierda[i];
            i++; k++; movimientos++;
        }
        while (j < derecha.length) {
            array[k] = derecha[j]; 
            j++; k++; movimientos++;
        }
        return array;
    }

    void process() { 
        comparaciones = movimientos = 0;
        if (counting == 0) return;
        
        int[] toSort = Arrays.copyOf(dataList, counting);
        toSort = mezclar(toSort);
        System.arraycopy(toSort, 0, dataList, 0, counting);
        String stats = "Comparaciones: " + comparaciones +
                        "\nMovimientos (asignaciones): " + movimientos;

        ep.outInfo("Procesando ordenamiento por mezcla...", "MEZCLA DIRECTA");
        ep.outInfo(stats, "ESTADISTICAS DE ORDENAMIENTO");
    }

    // 4. imprimir(medio)
    void printCmd() { 
        if (counting == 0) {
            ep.outAdv("No existen registros para imprimir.", "SIN REGISTROS");
            return;
        }
        System.out.println("===== DATOS REGISTRADOS Y ORDENADOS =====");
        for(int i = 0; i < counting; i++) 
            System.out.println("DATO #" + (i+1) + ":\t" + dataList[i]);
        System.out.println("===== FIN DEL ARREGLO =====");
    }

    // 4. imprimir(medio)
    void printFile() { 
        if (counting == 0) {
            ep.outAdv("No existen registros para imprimir.", "SIN REGISTROS");
            return;
        }
        StringBuilder res = new StringBuilder();
        res.append("Estadísticas de Ordenamiento:\n");
        res.append("Comparaciones: ").append(comparaciones).append("\n");
        res.append("Movimientos: ").append(movimientos).append("\n\n");
        res.append("===== DATOS ORDENADOS =====\n");
        
        for(int i = 0; i < counting; i++) 
            res.append(dataList[i]).append("\n");
        createFile(res);
    }

    // 4.1. crearArchivo(resultado)
    void createFile(StringBuilder results) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Guardar archivo de resultados");
        int sel = jfc.showSaveDialog(null);
        
        if (sel == JFileChooser.APPROVE_OPTION) {
            File saveFile = jfc.getSelectedFile();
            
            if(!saveFile.getName().toLowerCase().endsWith(".txt")) 
                saveFile = new File(saveFile.getAbsolutePath() + ".txt");
            try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(saveFile))) {
                bw.write(results.toString());
                ep.outInfo("Archivo guardado correctamente.\nRuta: " 
                        + saveFile.getAbsolutePath(), "ARCHIVO DE RESULTADOS");
            } catch(IOException e) {
                ep.outError("Ocurrio un error al crear el archivo: " 
                            + e.getMessage(), "ERROR");
            }
        } else ep.outAdv("Operacion cancelada por el usuario.", "CANCELADO");
    }

    // 5. navegabilidad() -> Menú Actualizado
    int mainMenu() {
        String title = "MENU PRINCIPAL";
        String msg = "Seleccione la accion que desee realizar.";
        String opt[] = {"Registrar", "Imprimir", "Vaciar registros", "SALIR"};
        
        return ep.entryOption(msg, title, opt);
    }

    public static void main(String[] args) {
        MezclaDirecta md = new MezclaDirecta();
        
        md.goal(); 
        while (true) {
            int option = md.mainMenu();
            
            switch (option) {
                case 0 -> md.regisMenu();
                case 1 -> md.printMenu();
                case 2 -> md.emptyData(); 
                case 3 -> { md.ep.outInfo("Ejecución finalizada.", "SALIR");
                            return;}
                default -> {}
            }
        }
    }
}