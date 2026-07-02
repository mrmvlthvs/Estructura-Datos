/*
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #6, #7
Fecha: 25 de febrero de 2026

----- ALGORITMO SECUENCIAL -----
*/
package sequencialsearch;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tools.DayArslan;
import tools.EntryPrompt;

public class Grupo {
    int cuenta = 0, stop, posicion, opcion;
    String nombreb, nombre, numControl; 
    boolean encontrado; 
    double estatura, peso;
    Persona[] grupo = new Persona[25]; 
    EntryPrompt into = new EntryPrompt();
    DayArslan date = new DayArslan();

    void meta() {
        String mensaje = """
                Registro de personas mediante teclado y lectura de archivo.
                Usted puede registrar hasta un máximo de 25 personas, buscar a
                una persona en específico mediante su nombre e imprimir sus 
                datos o la lista completa.
                """;

        into.outPlain(mensaje, "ALGORITMO SECUENCIAL");
    }
    // 2. DATA
    void entrada() {
        String opciones[] = {"Continuar", "Terminar"}; stop = 0; 

        while (cuenta < grupo.length && stop != 1) {
            grupo[cuenta] = new Persona(); 

            solicitarNombre(grupo, cuenta);
            solicitarNumControl(grupo, cuenta);
            solicitarFechaNacimiento(grupo, cuenta);
            solicitarEstatura(grupo, cuenta);
            solicitarPeso(grupo, cuenta);

            cuenta++; 
            stop = into.entryOption("¿Desea continuar registrando más "
                    + "personas?", "REGISTRO DE DATOS", opciones);
        }
    }

    void solicitarNombre(Persona grupo[], int id){
        boolean correct = false; 
        String error = "Campo OBLIGATORIO.\n";

        while (!correct) { //Nombre 
            nombre = into.entryString("NOMBRE COMPLETO", 
                    "Escriba el nombre completo de la persona #"+(id+1));
            correct = grupo[id].setNombre(nombre);

            if (!correct) into.outError(error+
            "Asegúrese de ingresar un nombre válido.", "NOMBRE INVALIDO");
        }
    }

    void solicitarNumControl(Persona grupo[], int id){
        boolean correct = false;

        while (!correct) {
            numControl = into.entryString("NUMERO DE CONTROL", 
                    "Deme el número de control de la persona #"+(id+1));
            correct = grupo[id].setNControl(numControl);

            if (!correct) {
                String m = """
                        Campo OBLIGATORIO.
                        Asegúrese de escribir correctamente el número de 
                        control.
                        Requisitos:
                        - Longitud de 8 dígitos
                        - Inicia con el año de matriculación (ej. 2025 -> 25)
                        - Seguido de la región del instituto -> 24
                        - Termina con 4 dígitos de matrícula ####
                        """; 
                
                into.outError(m, "NUMERO DE CONTROL INCORRECTO");
            }
        }
    }

    void solicitarFechaNacimiento(Persona grupo[], int id) {
        String title = "FECHA DE NACIMIENTO";
        boolean fechaValida = false;
        
        while (!fechaValida) {
            try {
                int dia = ingresarDia(title);
                int mes = ingresarMes(title);
                int year = ingresarYear(title);
                
                if (date.isValidDay(dia, mes, year)) {
                    DayArslan fecha = new DayArslan(dia, mes, year);
                    fechaValida = grupo[id].setFecha(dia, mes, year);
                } else {
                    into.outError("La fecha ingresada no es válida.",
                                title + " INVÁLIDA");
                }
            } catch (NumberFormatException e) {
                into.outAdv("""
                            Asegúrese de introducir correctamente la fecha.
                            Utilice ÚNICAMENTE números.""", "FECHA INVÁLIDA");
            }
        }
    }

    int ingresarDia(String title) {
        while (true) {
            int dia = into.entryInt(0, title, "Ingrese el día de nacimiento.");
            if (date.setDay(dia)) {
                return dia;
            }
            into.outError("Debe ser entre 1 y 31.", "DÍA INVÁLIDO");
        }
    }

    int ingresarMes(String title) {
        while (true) {
            int mes = into.entryInt(0, title, "Ingrese el número de mes de "
                                + "nacimiento (1-12).");
            if (date.setMonth(mes)) {
                return mes;
            }
            into.outError("Mes inválido. Debe ser entre 1 y 12.", 
                        "MES INVÁLIDO");
        }
    }

    int ingresarYear(String title) {
        while (true) {
            int year = into.entryInt(0,title, "Ingrese el año de nacimiento.");
            if (date.setYear(year)) {
                return year;
            }
            into.outError("Debe estar entre " + (date.currentYear - 100) +
                        " y " + date.currentYear + ".", "AÑO INVÁLIDO");
        }
    }

    void solicitarEstatura(Persona grupo[], int id){
        boolean correct = false;
        while (!correct) { //Estatura
            try {
                String title = "ESTATURA EN METROS";
                estatura = into.entryDouble(0, title,
                        "Deme la estatura de la persona #" + (id + 1));
                correct = grupo[id].setEstatura(estatura);

                if (!correct) into.outError("Asegúrese de introducir "
                            + "correctamente la estatura.", 
                            title + " INCORRECTA");
            } catch (NumberFormatException e) {
                into.outAdv("Campo OBLIGATORIO.\nAsegúrese de ingresar un "
                        +"número decimal válido.", "ESTATURA NO VÁLIDA");
                        
                correct = false;
            }
        }
    }

    void solicitarPeso(Persona grupo[], int id){
        boolean correct = false;
        while (!correct) { //Peso
            try {
                String title = "PESO EN KILOGRAMOS";
                peso = into.entryDouble(0, title,
                        "Deme el peso de la persona #" + (id + 1));
                correct = grupo[id].setPeso(peso);
                if (!correct) {
                    into.outError("Campo OBLIGATORIO.\n"
                            + "Asegúrese de ingresar correctamente el peso.",
                            title + " INCORRECTO");
                }
            } catch (HeadlessException | NumberFormatException e) {
                into.outAdv("Campo OBLIGATORIO.\nAsegúrese de ingresar un"
                        + " número decimal válido.", "PESO NO VÁLIDO");
                correct = false;
            }
        }
    }
    // 2.2 DATA - Archivo
    void archivo() { //Abrir archivo desde navegador de archivos
        JFileChooser fileC = new JFileChooser(); 
        fileC.setDialogTitle("Seleccione un archivo de texto (.txt)");
        int resultado = fileC.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileC.getSelectedFile();
            
            try (BufferedReader br = new BufferedReader(
                                    new FileReader(archivo))) {
                String linea;
                
                while ((linea = br.readLine())!=null && cuenta<grupo.length) { 
                    String[] datos = linea.split(",");
                    
                    if (datos.length == 5) {
                        String nombre = datos[0].trim();
                        String numControl = datos[1].trim();
                        String fecha = datos[2].trim();
                        double peso = Double.parseDouble(datos[3].trim());
                        double estatura = Double.parseDouble(datos[4].trim());
                        grupo[cuenta] = new Persona(nombre, numControl, fecha, 
                                peso, estatura); cuenta++;
                    } else into.outError("Formato incorrecto en la línea: "
                            +linea, "ERROR");
                } 
                into.outInfo("Se han registrado "+cuenta
                        +" personas desde el archivo.", "LECTURA EXITOSA");
            } catch (Exception e) {into.outAdv("Error al leer el archivo: "
                    +e.getMessage(), "ERROR");}
        } else  into.outError("No se seleccionó ningún archivo.", 
                            "ADVERTENCIA");
    }
    //3. PROCESO
    void buscar() {
        int i = 0; encontrado = false;

        if (cuenta == 0) {
            into.outError("No existen registros aun.", "SIN REGISTROS");
            return;
        }
        nombreb = into.entryString("BUSCAR PERSONA", "Deme el nombre de la "
                +"persona que desea buscar");
        if (nombreb.isBlank() || nombreb.isEmpty()) return;
        while (i < cuenta && !encontrado) {
            if (grupo[i].getNombre().equalsIgnoreCase(nombreb)) {
                into.outInfo("Persona encontrada.\nNúmero de comparaciones " 
                        + "realizadas: "+(i+1),"BÚSQUEDA EXITOSA");
                encontrado=true;
            } else i++;
        } 
        posicion = i;
        if (encontrado) into.outPlain(grupo[posicion].toString(), 
                                    "PERSONA ENCONTRADA");
        else into.outError("No se encontró en el grupo.", "RESULTADO");
    }
    //4. SALIDA / RESULTADO
    void imprimir() {
        int i = 0; 
        String resultado = "-----LISTA DE TODAS LAS PERSONAS REGISTRADAS-----";

        if (cuenta == 0) {
            into.outError("No hay personas registradas aún.", "SIN REGISTROS");
            return;
        } 
        while (i < cuenta) {
            resultado += "\nPersona " + (i + 1) + "\n" + grupo[i].toString(); 
            i++; 
        }
        guardarArchivo(resultado);
    }

    void guardarArchivo(String datos){
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Guardar archivo Lista completa");
        int resultado = jfc.showSaveDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = jfc.getSelectedFile();

            if (archivo.exists()) {
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "El archivo ya existe. ¿Desea sobrescribirlo?",
                        "Archivo existente",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta != JOptionPane.YES_OPTION) 
                    return; // Cancelar la operación
            }
            
            try (BufferedWriter writer = new BufferedWriter(
                                        new FileWriter(archivo))) {
                writer.write(datos);
                into.outInfo("Archivo guardado exitosamente.", 
                            "ARCHIVO RESULTADOS");
            } catch (IOException e) {
                into.outError("Error al guardar el archivo: " + e.getMessage(),
                        "Error");
            }
        } else  into.outError("No se guardó ningún archivo.", "ADVERTENCIA");
    }
    
    //5. MENU NAVEGABILIDAD
    int menu() {
        String mensajeOpciones = """
                Usted puede:
                1. Introducir datos manualmente
                2. Leer datos desde un archivo de texto
                3. Buscar una persona por nombre
                4. Imprimir toda la lista en un archivo
                5. Salir del programa

                Elija la opción que desee...
                """;
        String opciones[] = {"Introducir datos", "Leer datos de archivo",
                "Buscar persona", "Imprimir lista", "SALIR"};

        opcion = into.entryOption(mensajeOpciones, "MENÚ PRINCIPAL", opciones);
        return opcion;
    }

    public static void main(String[] args) {
        Grupo mn = new Grupo(); mn.meta();
        int sw = mn.menu();
        
        while (sw != -1 && sw != 4) {
            switch (sw) {
                case 0 -> mn.entrada();
                case 1 -> mn.archivo();
                case 2 -> mn.buscar();
                case 3 -> mn.imprimir();
            } sw = mn.menu();
        }
        mn.into.outPlain("Cerrando programa...", "FIN DEL PROGRAMA");
    }
}