/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicios #24
Fecha: 20 de abril de 2026

----- CLASE SHELL -----
*/
package shellsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import tools.EntryPrompt;

public class Shell {
    EntryPrompt in = new EntryPrompt();
    int datos[] = null; 
    int comparaciones = 0;
    int intercambios = 0; 
    String salida = "";

    void meta() {
        String titulo = "Algoritmo SHELL";
        String mensaje = """
                Programa que ordena datos usando el algoritmo de Shell.
                Permite capturar datos por teclado o leerlos desde archivo,
                acumulando los registros para ordenarlos y mostrarlos.
                """;

        in.outInfo(mensaje, titulo);
    }

    void data() {
        int opcion = menuCaptura();

        switch (opcion) {
            case 0 -> teclado();
            case 1 -> archivo();
        }
    }
    
    // 2.1 DATA 
    void teclado() {
        String titulo = "Datos por Teclado";
        String mensaje = "Ingrese el dato o 'parar' para detener.";
        ArrayList<Integer> lista = new ArrayList<>();

        while (true){
            String entrada = in.entryString(titulo, mensaje);

            if(entrada.isEmpty() || entrada.isBlank()) continue;
            String entTrim = entrada.trim();

            if(entTrim.equalsIgnoreCase("parar")) break;
            try {
                int num = Integer.parseInt(entTrim);
                lista.add(num);
            } catch(NumberFormatException e){
                String errort = "ERROR DE FORMATO";
                String errorm = """
                        No es un numero entero valido. Asegurese de 
                        ingresar un numero entero o 'parar'. 
                        """;

                in.outError(errorm, errort);
            }
        } 
        int agregados = lista.size();
        if (agregados > 0) {
            int actual = (datos != null) ? datos.length : 0;
            int temp[] = new int[actual + agregados];

            if (actual > 0) System.arraycopy(datos, 0, temp, 0, actual);
            for(int i = 0; i < agregados; i++) temp[actual + i] = lista.get(i);
            datos = temp;
        }
        String infoT = "CAPTURA EXITOSA";
        String infoM = "Se agregaron " + agregados + " nuevos datos.";
        in.outInfo(infoM, infoT);
    }
    
    // 2.2 DATA - Acumula sobre registros existentes
    void archivo() {
        JFileChooser fileC = new JFileChooser();        
        fileC.setDialogTitle("Seleccione el archivo de texto...");

        if(fileC.showOpenDialog(null) != JFileChooser.APPROVE_OPTION){
            String errorT = "SIN ARCHIVO SELECCIONADO";
            String errorM = "No se selecciono ningun archivo.";

            in.outError(errorM, errorT);
            return;
        }
        File archivo = fileC.getSelectedFile();
        ArrayList<Integer> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String linea;
            
            while ((linea = br.readLine()) != null){
                String[] partes = linea.trim().split("\\s+");

                for (String parte : partes){
                    if (parte.isEmpty()) continue;
                    try {
                        int num = Integer.parseInt(parte);
                        lista.add(num); 
                    } catch (NumberFormatException e){
                        String errorT = "ERROR EN LECTURA";
                        String errorM = "El dato " + parte;
                        errorM += " no es un numero entero.";

                        in.outError(errorM, errorT);
                    }
                }
            }
        } catch (IOException e){
            String errorT = "ERROR CON ARCHIVO";
            String errorM = "Ocurrio un error al leer el archivo: ";

            in.outError(errorM+e.getMessage(), errorT);
            return;
        } 
        int agregados = lista.size();

        if (agregados > 0) {
            int actual = (datos != null) ? datos.length : 0;
            int temp[] = new int[actual + agregados];

            if (actual > 0) System.arraycopy(datos, 0, temp, 0, actual);
            for (int i = 0; i < agregados; i++) temp[actual + i] = lista.get(i);
            datos = temp;
        }
        String infoT = "LECTURA EXITOSA";
        String infoM = "Se han agregado " + agregados + " datos desde archivo.";

        in.outInfo(infoM, infoT);
    }
    
    void vaciarRegistros() {
        String titulo = "VACIAR REGISTROS";
        String msj = "¿Esta totalmente seguro de vaciar la estructura?\n"
                    + "Esta accion eliminara todos los datos acumulados.";
        String opciones[] = {"Si, vaciar", "Cancelar"};
        
        int seleccion = in.entryOption(msj, titulo, opciones);
        if (seleccion == 0) {
            datos = null;
            comparaciones = 0;
            intercambios = 0;
            in.outInfo("Los registros han sido eliminados por completo.",titulo);
        } else 
            in.outAdv("Operacion cancelada. Los datos siguen a salvo.", titulo);
    }
    
    //3. PROCESO
    void proceso(){
        if (datos == null || datos.length == 0){
            String advT = "SIN REGISTROS";
            String advM = "NO hay datos cargados para ordenar.";

            in.outAdv(advM, advT);
            return;
        }
        comparaciones = intercambios = 0;
        int n = datos.length;
        int salto = n, aux, j, k;

        while (salto > 1){
            boolean ordenado;
            salto = salto/2;

            do {
                ordenado = true;

                for (j = 0; j <= n-1-salto; j++){
                    k = j + salto;
                    comparaciones++;

                    if (datos[j] > datos[k]){
                        aux = datos[j];
                        datos[j] = datos[k];
                        datos[k] = aux;
                        intercambios++;
                        ordenado = false;
                    }
                }
            } while (!ordenado);
        }
        String infoT = "ORDENAMIENTO SHELL";
        String infoM = "Proceso de ordenacion completado.";

        in.outInfo(infoM, infoT);
    }

    String getSalida(){
        return "Comparaciones: " + comparaciones
            + "\nIntercambios: " + intercambios;
    }
    
    //4.1 RESULTADOS
    void resultados(int medio){
        if (datos == null || datos.length == 0){
            String advT = "SIN REGISTROS";
            String advM = "No existen datos para imprimir.";

            in.outAdv(advM, advT);
            return;
        }
        StringBuilder resultado = new StringBuilder();

        for (int val : datos) resultado.append(val).append("\n");
        switch (medio){
            case 0 -> imprimirEnTerminal(resultado);
            case 1 -> guardarEnArchivo(resultado);
        }
    }

    void imprimirEnTerminal(StringBuilder resultado){
        String infoT = "IMPRIMIR RESULTADOS";
        String infoM = "Revise los datos en la terminal.";

        System.out.println("===== RESULTADOS DE ORDENAMIENTO SHELL =====");
        System.out.println(getSalida() + "\n" + "=".repeat(54));
        System.out.println(resultado.toString());
        in.outInfo(infoM, infoT);
    }
    
    void guardarEnArchivo(StringBuilder resultado){
        JFileChooser fileC = new JFileChooser();
        fileC.setDialogTitle("Guardar archivo de resultados");

        if (fileC.showSaveDialog(null) != JFileChooser.APPROVE_OPTION){
            String advT = "OPERACION CANCELADA";
            String advM = "Guardado cancelado por el usuario.";

            in.outAdv(advM, advT);
            return;
        }
        File archivo = fileC.getSelectedFile();

        if (!archivo.getName().toLowerCase().endsWith(".txt"))
            archivo = new File(archivo.getAbsolutePath() + ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            if (resultado.length() > 0){
                bw.write("=".repeat(54) + "\n");
                bw.write(getSalida() + "\n");
                bw.write("=".repeat(54) + "\n");
                bw.write(resultado.toString()); 
            } else bw.write("Sin datos registrados.");
            String infoT = "ARCHIVO DE RESULTADOS";
            String infoM = "Archivo guardado correctamente en:\n"; 
            infoM += archivo.getAbsolutePath(); 

            in.outInfo(infoM, infoT);
        } catch (IOException e){
            String errorT = "ERROR";
            String errorM = "Ocurrio un error al crear el archivo: "; 
            errorM += e.getMessage();

            in.outError(errorM, errorT);
        }
    }

    int menuPrincipal() {
        String titulo = "Opciones - Algoritmo SHELL";
        String mensaje = "Ingrese la accion que desee realizar.";
        String opciones[] = {"Capturar datos", "Imprimir", "Vaciar registros", 
                            "Salir"};

        return in.entryOption(mensaje, titulo, opciones);
    }
    
    //5.1 NAVEGABILIDAD
    int menuCaptura(){
        String titulo = "Capturar - Algoritmo SHELL";
        String mensaje = "Ingrese el medio por el cual capturara los datos.";
        String opciones[] = {"Teclado", "Archivo"};

        return in.entryOption(mensaje, titulo, opciones);
    }
    
    //5.2 NAVEGABILIDAD
    int menuImpresion() {
        String titulo = "Imprimir datos capturados";
        String mensaje = "Elija alguna de las opciones";
        String opciones[] = {"Imprimir en terminal", "Imprimir en archivo"};

        return in.entryOption(mensaje, titulo, opciones);
    }
    
    public static void main(String args[]) {
        Shell programa = new Shell();

        programa.meta();
        while (true){
            int opcion = programa.menuPrincipal();

            switch(opcion) {
                case 0 -> {programa.data(); programa.proceso();}
                case 1 -> {
                    if (programa.datos == null || programa.datos.length == 0){
                        String advT = "SIN DATOS PARA MOSTRAR";
                        String advM = "Capture los datos antes de imprimir.";

                        programa.in.outAdv(advM, advT);
                    } else programa.resultados(programa.menuImpresion());
                }
                case 2 -> programa.vaciarRegistros(); 
                case 3 -> {
                    programa.in.outPlain("Saliendo...", "FIN DEL PROGRAMA");
                    return; 
                }
            }
        }
    }
}