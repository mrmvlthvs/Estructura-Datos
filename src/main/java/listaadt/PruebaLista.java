/* Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #42
Fecha: 6 de junio de 2026

----- CLASE PRINCIPAL DE PRUEBA METODOLÓGICA (TDA LISTA) -----
*/
package listaadt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import tools.EntryPrompt;

public class PruebaLista {
    private EntryPrompt in = new EntryPrompt();
    private Lista masterList = new Lista(200); 

    // 1. meta()
    void goal() {
        String title = "PRUEBA METODOLOGICA: TDA LISTA";
        String msg = """
            Programa para evaluar operaciones del ADT Lista Enlazada.
            Permite ingresar nombres completos mediante teclado o archivo
            acumulandolos en memoria, y probar operaciones de insercion, 
            remocion, busqueda y segmentacion de la estructura.
            """;

        in.outPlain(msg, title);
    }

    // 2. data()
    void data() {
        String opts[] = {"Teclado", "Archivo"};
        int op = in.entryOption("Elija el medio de captura:", "DATOS", opts);
        
        switch (op) {
            case 0 -> teclado();
            case 1 -> archivo();
        }
    }

    // 2.1. teclado()
    void teclado() {
        int agregados = 0;
        String msg = "Ingrese nombre completo (o 'parar' para finalizar):";
        
        while (true) {
            String nom = in.entryString("DATOS POR TECLADO", msg);
            if (nom == null || nom.isBlank()) continue;
            if (nom.trim().equalsIgnoreCase("parar")) break;
            masterList.add(nom.trim());
            agregados++;
        }
        in.outInfo("Se registraron " + agregados + " nombres.", "EXITO");
    }

    // 2.2. archivo()
    void archivo() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione archivo de nombres (.txt)");
        
        if (jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            in.outError("No se selecciono ningun archivo.", "ERROR");
            return;
        }
        int agregados = 0;
        File f = jfc.getSelectedFile();
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    masterList.add(linea.trim());
                    agregados++;
                }
            }
            in.outInfo("Leidos " + agregados + " nombres del archivo.", "EXITO");
        } catch (IOException e) {
            in.outError("Error de lectura: " + e.getMessage(), "ERROR");
        }
    }

    // Validador cohesivo para asegurar indices numericos correctos
    Integer capturarEntero(String titulo, String msj) {
        while (true) {
            String input = in.entryString(titulo, msj);
            if (input == null) return null;
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                in.outError("Debe ingresar un numero valido.", "ERROR FORMATO");
            }
        }
    }

    // 3. proceso()
    void proceso() {
        if (masterList.isEmpty()) {
            in.outAdv("La lista esta vacia. Registre datos primero.", "AVISO");
            return;
        }
        String opt[] = {"Add (Indice)", "Remove (Indice)", "Remove (Valor)",
                        "Buscar (Contains)", "Generar SubLista"};
        int sel = in.entryOption("Prueba a realizar:", "PROCESO ADT", opt);
        
        try {
            switch(sel) {
                case 0 -> {
                    Integer idx = capturarEntero("INDICE", "Posicion:");
                    if (idx == null) return;
                    String val = in.entryString("VALOR", "Nombre a insertar:");
                    if (val != null) masterList.add(idx, val.trim());
                }
                case 1 -> {
                    Integer idx = capturarEntero("REMOVER", "Indice a remover:");
                    if (idx != null) masterList.remove(idx);
                }
                case 2 -> {
                    String val = in.entryString("REMOVER", "Nombre a remover:");
                    if (val != null) masterList.remove(val.trim());
                }
                case 3 -> {
                    String val = in.entryString("BUSCAR", "Nombre a buscar:");
                    if (val != null) {
                        boolean ok = masterList.contains(val.trim());
                        in.outInfo("¿Contiene '" + val + "'? " + ok, "BUSQUEDA");
                    }
                }
                case 4 -> {
                    Integer d = capturarEntero("INICIO", "Indice inicial (D):");
                    Integer h = capturarEntero("FIN", "Indice final (H):");
                    if (d != null && h != null) {
                        Lista sub = masterList.subLista(d, h);
                        in.outInfo("SubLista:\n" + sub.toString(), "RESULTADO");
                    }
                }
            }
        } catch (Exception e) {
            in.outError("Excepcion del ADT: " + e.getMessage(), "FALLO");
        }
    }

    // 4. resultados()
    void resultados() {
        if (masterList.isEmpty()) {
            in.outAdv("Lista vacia.", "AVISO"); 
            return;
        }
        int op = in.entryOption("Destino de salida:", "IMPRIMIR", 
                                new String[]{"Terminal", "Archivo"});
        String estado = "=== ESTADO DE LA LISTA ===\nTamano actual: " 
                        + masterList.size() + "\n\nElementos:\n" 
                        + masterList.toString() + "\n==========================";
        if (op == 0) {
            System.out.println(estado);
            in.outInfo("Revise la terminal para ver los datos.", "IMPRESION");
        } else if (op == 1) guardarArchivo(estado);
    }

    void guardarArchivo(String content) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Guardar estado de Lista ADT");
        
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            if (!f.getName().toLowerCase().endsWith(".txt")) 
                f = new File(f.getAbsolutePath() + ".txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write(content);
                in.outInfo("Archivo guardado en:\n" + f.getAbsolutePath(), "EXITO");
            } catch (IOException e) {
                in.outError("Error al guardar archivo.", "ERROR");
            }
        } else in.outAdv("Operacion cancelada.", "CANCELADO");
    }

    void vaciarLista() {
        String msg = "¿Esta seguro de eliminar todos los nombres de la lista?";
        if (in.entryOption(msg, "VACIAR", new String[]{"Si", "No"}) == 0) {
            masterList = new Lista(200);
            in.outInfo("La lista ha sido vaciada.", "VACIAR");
        }
    }

    // 5. navegabilidad()
    public static void main(String[] args) {
        PruebaLista app = new PruebaLista();
        app.goal();
        String opts[] = {"Registrar Datos", "Probar ADT", "Imprimir Lista", 
                        "Vaciar Lista", "Salir"};
                        
        while (true) {
            int sel = app.in.entryOption("Seleccione accion:", "MENU PRINCIPAL", opts);
            
            switch (sel) {
                case 0 -> app.data();
                case 1 -> app.proceso();
                case 2 -> app.resultados();
                case 3 -> app.vaciarLista();
                case 4 -> {
                    app.in.outPlain("Saliendo...", "FIN DEL PROGRAMA");
                    return;
                }
            }
        }
    }
}