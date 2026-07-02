package merge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import tools.EntryPrompt;

public class MergeSort {
    File f1, f2, f3;
    EntryPrompt ep = new EntryPrompt();

    // ---------------- META ----------------
    void goal(){
        String title = "MERGE SORT RECURSIVO EN ARCHIVOS";
        String message = """
                Ordenamiento de archivos mediante el algoritmo Merge Sort.
                Se divide el archivo en partes más pequeñas (recursión),
                se ordenan y posteriormente se fusionan.
                """;
        ep.outPlain(message, title);
    }

    // ---------------- DATA ----------------
    void data(){
        ep.outInfo("Seleccione el archivo a ordenar", "ARCHIVO FUENTE");

        if(fileOpen()){
            fileCreate();
            mergeSortArchivo(f1, f3);
            ep.outInfo("Archivo ordenado correctamente", "PROCESO COMPLETADO");
        }
    }

    boolean fileOpen(){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccionar archivo");

        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            f1 = jfc.getSelectedFile();
            ep.outInfo("Archivo seleccionado:\n" + f1.getPath(), "OK");
            return true;
        }
        return false;
    }

    void fileCreate(){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Guardar archivo ordenado");

        if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            f3 = jfc.getSelectedFile();

            if(!f3.getName().toLowerCase().endsWith(".txt"))
                f3 = new File(f3.getAbsolutePath() + ".txt");

            ep.outInfo("Archivo destino:\n" + f3.getPath(), "CREADO");
        }
    }

    // ---------------- PROCESO (MERGE SORT REAL) ----------------
    void mergeSortArchivo(File origen, File destino){
        try{
            if(esArchivoPequeno(origen)){
                copiarArchivo(origen, destino);
                return;
            }

            File temp1 = new File("temp1.txt");
            File temp2 = new File("temp2.txt");

            particionar(origen, temp1, temp2);

            File ordenado1 = new File("ord1.txt");
            File ordenado2 = new File("ord2.txt");

            mergeSortArchivo(temp1, ordenado1);
            mergeSortArchivo(temp2, ordenado2);

            merge(ordenado1, ordenado2, destino);

        } catch(Exception e){
            ep.outError("Error en MergeSort:\n" + e.getMessage(), "ERROR");
        }
    }

    // ---------------- PARTICION ----------------
    void particionar(File origen, File fA, File fB) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new FileReader(origen));
            PrintWriter pw1 = new PrintWriter(fA);
            PrintWriter pw2 = new PrintWriter(fB);
        ){
            String linea;
            boolean toggle = true;

            while((linea = br.readLine()) != null){
                if(toggle) pw1.println(linea);
                else pw2.println(linea);
                toggle = !toggle;
            }
        }
    }

    // ---------------- MERGE ----------------
    void merge(File fA, File fB, File destino) throws IOException {
        try(
            BufferedReader br1 = new BufferedReader(new FileReader(fA));
            BufferedReader br2 = new BufferedReader(new FileReader(fB));
            PrintWriter pw = new PrintWriter(destino);
        ){
            String w1 = br1.readLine();
            String w2 = br2.readLine();
            String wS;

            while(w1 != null && w2 != null){
                if(w1.compareToIgnoreCase(w2) <= 0){
                    wS = w1;
                    w1 = br1.readLine();
                } else {
                    wS = w2;
                    w2 = br2.readLine();
                }
                pw.println(wS);
            }

            while(w1 != null){
                pw.println(w1);
                w1 = br1.readLine();
            }

            while(w2 != null){
                pw.println(w2);
                w2 = br2.readLine();
            }
        }
    }

    // ---------------- UTILIDADES ----------------
    boolean esArchivoPequeno(File f) throws IOException {
        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            while(br.readLine() != null && count < 2) count++;
        }
        return count <= 1;
    }

    void copiarArchivo(File origen, File destino) throws IOException {
        try(
            BufferedReader br = new BufferedReader(new FileReader(origen));
            PrintWriter pw = new PrintWriter(destino);
        ){
            String linea;
            while((linea = br.readLine()) != null)
                pw.println(linea);
        }
    }

    // ---------------- RESULTADOS ----------------
    void showFile(){
        if(f3 == null){
            ep.outError("No hay archivo para mostrar", "ERROR");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(f3))){
            String linea;
            System.out.println("\n===== ARCHIVO ORDENADO =====\n");
            while((linea = br.readLine()) != null)
                System.out.println(linea);
        } catch(Exception e){
            ep.outError("Error al leer archivo", "ERROR");
        }
    }

    // ---------------- NAVEGABILIDAD ----------------
    int menu(){
        String opciones[] = {"Ordenar archivo", "Mostrar resultado", "Salir"};
        return ep.entryOption("Seleccione una opción", "MENÚ", opciones);
    }

    public static void main(String[] args){
        MergeSort ms = new MergeSort();
        ms.goal();

        int op;
        while((op = ms.menu()) != 2 && op != -1){
            switch(op){
                case 0 -> ms.data();
                case 1 -> ms.showFile();
            }
        }
    }
}