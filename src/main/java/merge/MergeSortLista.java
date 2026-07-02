/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #27
Fecha: 20 de abril de 2026

----- CLASE MERGE SORT LISTA -----

*/
package merge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import tools.EntryPrompt;

public class MergeSortLista {
    File f1, f2, f3;
    EntryPrompt ep = new EntryPrompt();

    void goal(){        // <----- META
        String title = "FUSION DE ARCHIVOS ORDENADOS";
        String message = """
                        Intercalar datos de dos archivos (merge)
                        cuando tanto como f1 y f2 ya estan ordenados.
                        """;
        
        ep.outPlain(message, title);
    }

    void data(){        // <----- DATA
        String title = "ARCHIVOS";
        String message = """
                        A continuacion, abra los archivos con los datos
                        UNO POR UNO.
                        """;
        boolean run = true;
        while (run){
            ep.outInfo(message, title);
            boolean run1 = fileOpen( 1);
            boolean run2 = fileOpen( 2);
            run = !(run1 && run2);
        }
        fileCreate(); dataMerge(f1, f2, f3);         
    }

    boolean fileOpen(int id){         // <----- DATA 2.2
        String title = "ABRIR ARCHIVO";
        String message = """
                        Se ha abierto el archivo correctamente.
                        Direccion del archivo: 
                        """;
        String errorMess = "Error al abrir el archivo";

        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione el archivo que desee.");
        int get = jfc.showOpenDialog(null);

        if(get == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();

            if(id == 1) f1 = file;
            else if(id == 2) f2 = file;
            ep.outInfo(message + file.getPath(), title + " #" + id);
            return true;
        } else {
            ep.outError(errorMess, title);
            return false;
        }
    }

    void fileCreate(){         // <----- RESULTADOS
        String title = "CREANDO ARCHIVO DE RESULTADOS";
        JFileChooser jfc = new JFileChooser();

        ep.outInfo("Guarde el archivo de resultados", title);
        jfc.setDialogTitle("Guardar archivo de resultados.");
        int get = jfc.showSaveDialog(null);
        if(get == JFileChooser.APPROVE_OPTION){
            f3 = jfc.getSelectedFile();

            if(!f3.getName().toLowerCase().endsWith(".txt"))
                f3 = new File(f3.getAbsolutePath() + ".txt");
            ep.outInfo("Archivo creado en:\n" + f3.getPath(), title);
        } else ep.outError("No se creó el archivo.", title);
    }

    void dataMerge(File f1, File f2, File f3){         // <----- PROCESO
        String title = "MERGE SORT COMPLETADO";
        String message = "Archivos fusionados completamente.";
        String window1, window2, windowS;

        try(BufferedReader br1 = new BufferedReader(new FileReader(f1));
            BufferedReader br2 = new BufferedReader(new FileReader(f2));
            java.io.PrintWriter pw = new java.io.PrintWriter(f3); ){
            window1 = br1.readLine(); window2 = br2.readLine();

            while(window1 != null && window2 != null){
                if(window1.compareToIgnoreCase(window2) <= 0){
                    windowS = window1;
                    pw.println(windowS);
                    window1 = br1.readLine();
                } else {
                    windowS = window2;
                    pw.println(windowS);
                    window2 = br2.readLine();
                }
            }
            while(window1 != null){
                windowS = window1;
                pw.println(windowS);
                window1 = br1.readLine();
            }
            while(window2 != null){
                windowS = window2;
                pw.println(windowS);
                window2 = br2.readLine();
            }
            ep.outInfo(message, title);
        } catch(Exception e){
            ep.outError("Error al procesar archivos:\n" + e.getMessage(),
                        "ERROR");
        }
    }

    void showFile(File f3){         // <----- RESULTADOS 4.4
        if(f3 == null){
            ep.outError("No hay archivo de resultados para mostrar.", "ERROR");
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(f3))){
            String linea;

            System.out.println("\n===== CONTENIDO ORDENADO =====\n");
            while((linea = br.readLine()) != null) System.out.println(linea);
            System.out.println("\n===== FIN DEL ARCHIVO =====\n");
            ep.outPlain("Revise la salida en consola.", "IMPRESION COMPLETA");
        } catch(Exception e){
            String errorTitle = "ERROR";
            String errorMessage = "Error al leer el archivo: \n";

            ep.outError(errorMessage + e.getMessage(), errorTitle);
        }
    }

    int menu(){         // <----- NAVEGABILIDAD
        String title = "MENU PRINCIPAL";
        String message = "Eliga la accion que desee realizar...";
        String options[] = {"Cargar archivos", "Imprimir lista fusionada", 
                            "SALIR"};

        return ep.entryOption(message, title, options);
    }

    public static void main(String args[]){
        MergeSortLista ms = new MergeSortLista();
        int option = -2;

        ms.goal();
        while(option != 2 && option != -1){
            option = ms.menu();
            
            switch(option){
                case 0 -> ms.data();
                case 1 -> ms.showFile(ms.f3);
            }
        }
        ms.ep.outPlain("Cerrando programa...", "FIN PROGRAMA");
    }
}
