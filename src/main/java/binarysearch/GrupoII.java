/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\binarysearch\GrupoII.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #8, #9
Fecha: 2 de marzo de 2026

----- ALGORITMO BINARIO -----

*/
package binarysearch;

import java.awt.HeadlessException;
import java.io.*;
import javax.swing.*;
import tools.*;
import java.util.Arrays;
import java.util.Comparator;

public class GrupoII {
    int cuenta = 0, stop, opcion;
    boolean encontrado;
    String nombre, numControl; 
    double estatura, peso;
    Persona[] grupo = new Persona[25];
    EntryPrompt into = new EntryPrompt();

    void meta() {
        String mensaje = """
                Registro de personas mediante teclado y lectura de archivo.
                Usted puede registrar hasta un máximo de 25 personas,
                buscar una persona por nombre utilizando búsqueda binaria
                e imprimir sus datos o la lista completa.
                """;

        into.outPlain(mensaje, "ALGORITMO BINARIO");
    }

    void entrada() {
        String opciones[] = {"Continuar", "Terminar"};
        stop = 0;

        while (cuenta < grupo.length && stop != 1) {
            grupo[cuenta] = new Persona();

            solicitarNombre(grupo, cuenta);
            solicitarNumControl(grupo, cuenta);
            solicitarFechaNacimiento(grupo, cuenta);
            solicitarEstatura(grupo, cuenta);
            solicitarPeso(grupo, cuenta);

            cuenta++;
            stop = into.entryOption("¿Desea continuar registrando más personas?",
                    "REGISTRO DE DATOS", opciones);
        }
        if (cuenta == grupo.length) into.outAdv("Limite de registro alcanzado.", 
                                    "CUPO COMPLETO");
        ordenarPorApellido();
    }

    void solicitarNombre(Persona grupo[], int id) {
        boolean correct = false;
        String error = "Campo OBLIGATORIO.\n";

        while (!correct) { //Nombre 
            nombre = into.entryString("NOMBRE COMPLETO",
                    "Escriba el nombre completo de la persona #" + (id + 1));
            correct = grupo[id].setNombre(nombre);
            if (!correct) into.outError(error
                        + "Asegúrese de ingresar un nombre válido.", 
                        "NOMBRE INCORRECTO");
        }
    }

    void solicitarNumControl(Persona grupo[], int id) {
        boolean correct = false;

        while (!correct) { //Numero de control
            numControl = into.entryString("NUMERO DE CONTROL",
                    "Deme el número de control de la persona #" + (id + 1));
            correct = grupo[id].setNControl(numControl);

            if (!correct) {
                String m = """
                        Campo OBLIGATORIO.
                        Asegúrese de escribir correctamente el número de control.
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
        boolean correct = false;

        while (!correct) { //Fecha de nacimiento
            try {
                String title = "FECHA DE NACIMIENTO";
                int dia = into.entryInt(0, title,
                        "Ingrese el dia de nacimiento.");
                int mes = into.entryInt(0, title,
                        "Ingrese el numero de mes de nacimiento.");
                int year = into.entryInt(0, title,
                        "Ingrese el año de nacimiento.");
                correct = grupo[id].setFecha(dia, mes, year);

                if (!correct) into.outError("Asegúrese de introducir "
                                + "correctamente la fecha utilizando números.",
                                title + " INCORRECTA");
            } catch (NumberFormatException e) {
                into.outAdv("""
                        Asegúrese de introducir correctamente la fecha.
                        Utilice ÚNICAMENTE números.""", "FECHA INVÁLIDA");

                correct = false;
            }
        }
    }

    void solicitarEstatura(Persona grupo[], int id) {
        boolean correct = false;

        while (!correct) { //Estatura
            try {
                String title = "ESTATURA EN METROS";
                estatura = into.entryDouble(0, title,
                        "Deme la estatura de la persona #" + (id + 1));
                correct = grupo[id].setEstatura(estatura);

                if (!correct) into.outError("Asegúrese de introducir "
                            + "correctamente la estatura.", title + " INCORRECTA");
            } catch (NumberFormatException e) {
                into.outAdv("Campo OBLIGATORIO.\nAsegúrese de ingresar un "
                        + "número decimal válido.", "ESTATURA NO VÁLIDA");

                correct = false;
            }
        }
    }

    void solicitarPeso(Persona grupo[], int id) {
        boolean correct = false;

        while (!correct) { //Peso
            try {
                String title = "PESO EN KILOGRAMOS";
                peso = into.entryDouble(0, title,
                        "Deme el peso de la persona #" + (id + 1));
                correct = grupo[id].setPeso(peso);

                if (!correct) into.outError("Campo OBLIGATORIO.\n"
                        + "Asegúrese de ingresar correctamente el peso en kg.",
                        title + " INCORRECTO");
            } catch (HeadlessException | NumberFormatException e) {
                into.outAdv("Asegúrese de ingresar un número decimal válido.",
                            "PESO NO VÁLIDO");

                correct = false;
            }
        }
    }

    void archivo() { //Abrir archivo desde navegador de archivos
        JFileChooser fileC = new JFileChooser(); 
        fileC.setDialogTitle("Seleccione un archivo de texto (.txt)");
        int resultado = fileC.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileC.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;

                while ((linea = br.readLine()) != null && cuenta < grupo.length) { 
                    String[] datos = linea.split(",");

                    if (datos.length == 5) {
                        String nombre = datos[0].trim();
                        String numControl = datos[1].trim();
                        String fecha = datos[2].trim();
                        double peso = Double.parseDouble(datos[3].trim());
                        double estatura = Double.parseDouble(datos[4].trim());
                        grupo[cuenta] = new Persona(nombre, numControl, fecha, 
                                peso, estatura); cuenta++;
                    } else into.outError("Formato incorrecto en la línea: "+linea, 
                            "ERROR");
                } 
                into.outInfo("Se han registrado "+cuenta+" personas desde el archivo.", 
                        "LECTURA EXITOSA");
            } catch (Exception e) {into.outAdv("Error al leer el archivo: "
                    +e.getMessage(), "ERROR");}
        } else  into.outError("No se seleccionó ningún archivo.", "ADVERTENCIA"); 
    }
    //  ORDENAR POR PRIMER APELLIDO
    void ordenarPorApellido() {
        Arrays.sort(grupo, 0, cuenta, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                if (p1 == null || p2 == null) return 0;
                String[] partes1 = p1.getNombre().trim().split(" ");
                String[] partes2 = p2.getNombre().trim().split(" ");
                if (partes1.length < 2 || partes2.length < 2) return 0;
                String apellido1 = partes1[partes1.length - 2];
                String apellido2 = partes2[partes2.length - 2];
                return apellido1.compareToIgnoreCase(apellido2);
            }
        });
    }
    //  BUSCAR (BÚSQUEDA BINARIA)
    void buscar() {
        ordenarPorApellido();
        int intentos = 0;
        
        if (cuenta == 0) {
            into.outError("No hay personas registradas aún.", 
                          "SIN REGISTROS");
            return;
        } 
        String apellidoBuscado = into.entryString("BUSCAR PERSONA",
                "Ingrese los apellidos de la persona a buscar");
        if (apellidoBuscado == null || apellidoBuscado.trim().isEmpty()) {
            into.outError("Debe ingresar al menos un apellido.",
                    "FORMATO INVÁLIDO"); return;
        }
        int inferior = 0, superior = cuenta - 1; encontrado = false;

        while (inferior <= superior) {
            int pivote = (inferior + superior) / 2; intentos++;
            String[] partesActual = grupo[pivote].getNombre().trim().split(" ");
            String apellidoActual = partesActual[partesActual.length - 2] + " "
                    + partesActual[partesActual.length - 1];
            int comparacion = apellidoBuscado.compareToIgnoreCase(apellidoActual);

            if (comparacion == 0) {
                into.outInfo("Persona encontrada en la posición " + (pivote + 1)
                        + "\nNúmero de comparaciones realizadas: " + intentos,
                        "BÚSQUEDA EXITOSA");
                into.outPlain(grupo[pivote].toString(), "PERSONA ENCONTRADA");
                encontrado = true; return;
            }
            if (comparacion < 0) superior = pivote - 1;
            else inferior = pivote + 1;
        }
        if (!encontrado)
            into.outError("No se encontró ninguna persona con esos apellidos.",
                    "RESULTADO");
    }

    void imprimir() {
        if (cuenta == 0) {
            into.outError("No hay personas registradas aún.", "SIN REGISTROS"); 
            return;
        } 
        ordenarPorApellido();
        String resultado = "";

        for (int i = 0; i < cuenta; i++) resultado += "\nPersona "+(i+1)+"\n"
                                                   + grupo[i].toString();
        System.out.println("----- LISTA DE TODAS LAS PERSONAS REGISTRADAS -----"
                        + resultado);
        into.outPlain("Revise la lista impresa en la terminal.",
                "IMPRESIÓN COMPLETA");
    }

    int menu() {
        String mensajeOpciones = """
                Usted puede:
                1. Introducir datos manualmente
                2. Leer datos desde un archivo de texto
                3. Buscar una persona por nombre (búsqueda binaria)
                4. Imprimir toda la lista
                5. Salir del programa
                Elija la opción que desee...
                """;
        String opciones[] = {"Introducir datos", "Leer datos de archivo",
                "Buscar persona", "Imprimir lista", "SALIR"};
        opcion = into.entryOption(mensajeOpciones, "MENÚ PRINCIPAL", opciones);

        return opcion;
    }

    public static void main(String[] args) { // navegabilidad()
        GrupoII mn = new GrupoII();
        mn.meta(); int sw = mn.menu();

        while (sw != -1 && sw != 4) {
            switch (sw) {
                case 0 -> mn.entrada(); 
                case 1 -> mn.archivo();
                case 2 -> mn.buscar();
                case 3 -> mn.imprimir();
            } 
            sw = mn.menu();
        }
    }
}