/* Path: \EstructuraDatos\src\main\java\tarea2\
   File: BusquedaBinaria.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio: #9
Fecha: 02/03/2026

----- CLASE BUSQUEDA BINARIA -----

*/
package busquedabinaria2;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import tools.DayArslan;
import tools.EntryPrompt;

public class BusquedaBinaria {
    int cuenta = 0, stop, opcion;
    boolean finded;
    String nombre, numControl;
    double estatura, peso;
    Persona[] datos = new Persona[25];
    EntryPrompt ep = new EntryPrompt();
    DayArslan date = new DayArslan();

    void meta(){
        String titulo = "BUSQUEDA BINARIA";
        String mensaje = """
                Registro de datos mediante teclado y lectura de archivo.
                Usted puede registrar hasta un máximo de 25 personas,
                buscar una persona por nombre utilizando búsqueda binaria
                e imprimir sus datos o la lista completa.
                """;

        ep.outPlain(mensaje, titulo);
    }
    // 2. DATA
    void entrada(){
        String titulo = "REGISTRO DE DATOS";
        String mensaje = "¿Desea continuar registrando más personas?";
        String opciones[] = {"Si", "No"};
        int i = cuenta; stop = 0;

        while(cuenta < datos.length && stop == 0){
            datos[i] = new Persona();

            regNombre(datos, i);
            regNControl(datos, i);
            regFechaN(datos, i);
            regEstatura(datos, i);
            regPeso(datos, i);
            
            cuenta++; i = cuenta;
            stop = ep.entryOption(mensaje, titulo, opciones);
        }
        if(cuenta == datos.length)
            ep.outAdv("Limite de registros alcanzado.", "CUPO COMPLETO");
        ordenar();
    }

    void regNombre(Persona datos[], int index){
        boolean isValid = false;
        String titulo = "NOMBRE COMPLETO";
        String mensaje = """
                Ingrese el nombre completo empezando por los nombres, 
                seguido de los apellidos. Evite usar caracteres no validos
                (!@#$%^&*_+... etc.).
                """;
        String errorTitulo = "CAMPO OBLIGATORIO";
        String errorMensaje = """
                Asegurese de ingresar un nombre valido.
                Evite usar caracteres no validos (!@#$%^&*_+... etc.).
                """;

        while (!isValid){
            nombre = ep.entryString(titulo, mensaje);
            isValid = datos[index].setNombre(nombre);

            if(!isValid)
                ep.outError(errorMensaje, errorTitulo);
        }
    }

    void regNControl(Persona datos[], int index){
        boolean isValid = false;
        String titulo = "NUMERO DE CONTROL";
        String mensaje = """
                Ingrese el numero de control. 
                Evite usar caracteres no decimales.
                Evite usar numero de control no valido.
                """;
        String errorTitulo = "CAMPO OBLIGATORIO";
        String errorMensaje = """
                Asegurese de ingresar un numero de control valido.
                Evite usar numero de control no valido.
                """;

        while(!isValid){
            numControl = ep.entryString(titulo, mensaje);
            isValid = datos[index].setNControl(numControl);

            if (!isValid)
                ep.outError(errorMensaje, errorTitulo);
        }
    }

    void regFechaN(Persona datos[], int index){
        String title = "FECHA DE NACIMIENTO";
        boolean fechaValida = false;
        
        while (!fechaValida) {
            try {
                int dia = ingresarDia(title);
                int mes = ingresarMes(title);
                int year = ingresarYear(title);
                
                if (date.isValidDay(dia, mes, year)) {
                    DayArslan fecha = new DayArslan(dia, mes, year);
                    fechaValida = datos[index].setFecha(dia, mes, year);
                } else {
                    ep.outError("La fecha ingresada no es válida.",
                                title + " INVÁLIDA");
                }
            } catch (NumberFormatException e) {
                ep.outAdv("""
                            Asegúrese de introducir correctamente la fecha.
                            Utilice ÚNICAMENTE números.""", "FECHA INVÁLIDA");
            }
        }
    }

    int ingresarDia(String title) {
        while (true) {
            int dia = ep.entryInt(0, title, "Ingrese el día de nacimiento.");
            if (date.setDay(dia)) {
                return dia;
            }
            ep.outError("Debe ser entre 1 y 31.", "DÍA INVÁLIDO");
        }
    }

    int ingresarMes(String title) {
        while (true) {
            int mes = ep.entryInt(0, title, "Ingrese el número de mes de "
                                + "nacimiento (1-12).");
            if (date.setMonth(mes)) {
                return mes;
            }
            ep.outError("Mes inválido. Debe ser entre 1 y 12.", 
                        "MES INVÁLIDO");
        }
    }

    int ingresarYear(String title) {
        while (true) {
            int year = ep.entryInt(0,title, "Ingrese el año de nacimiento.");
            if (date.setYear(year)) {
                return year;
            }
            ep.outError("Debe estar entre " + (date.currentYear - 100) +
                        " y " + date.currentYear + ".", "AÑO INVÁLIDO");
        }
    }

    void regEstatura(Persona datos[], int index){
        boolean isValid = false;
        String titulo = "ESTATURA";
        String mensaje = """
                Ingrese la estatura de la persona utilizando 
                unicamente numeros decimales. La unidad de medida
                es en METROS.
                """;
        String errorTitulo = "CAMPO OBLIGATORIO";
        String errorMensaje = """
                Asegurese de ingresar correctamente la estatura utilizando
                SOLO NUMEROS DECIMALES. Evite ingresar medidas no validas.
                """;

        while(!isValid){
            try {
                estatura = ep.entryDouble(0, titulo, mensaje);
                isValid = datos[index].setEstatura(estatura);

                if(!isValid)
                    ep.outError(errorMensaje, errorTitulo);
            } catch (NumberFormatException e){
                ep.outAdv(errorMensaje, errorTitulo);
                isValid = false;
            }
        }
    }

    void regPeso(Persona datos[], int index){
        boolean isValid = false;
        String titulo = "PESO - TALLA";
        String mensaje = """
                Ingrese el peso de la persona utilizando unicamente 
                numeros decimales. La unidad de medida es en KILOGRAMOS.
                """;
        String errorTitulo = "CAMPO OBLIGATORIO";
        String errorMensaje = """
                Asegurese de ingresar correctamente el peso utilizando
                SOLO NUMEROS DECIMALES. Evite ingresar medidas no validas.
                """;

        while(!isValid){
            try {
                peso = ep.entryDouble(0, titulo, mensaje);
                isValid = datos[index].setPeso(peso);

                if(!isValid)
                    ep.outError(errorMensaje, errorTitulo);
            } catch (NumberFormatException e){
                ep.outAdv(errorMensaje, errorTitulo);
                isValid = false;
            }
        }
    }
    //2.2 DATA readArch
    void archivo(){
        JFileChooser fileC = new JFileChooser();
        fileC.setDialogTitle("Seleccione un archivo de texto.");
        int result = fileC.showOpenDialog(null);
        boolean isSelected = result == fileC.APPROVE_OPTION;

        if (isSelected){
            File arch = fileC.getSelectedFile();
            readArchivo(arch, fileC);
        } else 
            ep.outError("No se selecciono ningun archivo.", 
            "NO HAY ARCHIVO SELECCIONADO");
    }

    void readArchivo(File arch, JFileChooser fileC){
        try (BufferedReader br = new BufferedReader(new FileReader(arch))){
            String linea; int count = 0;

            while ((linea = br.readLine()) != null && 
                cuenta < datos.length){
                String[] data = linea.split(",");
                
                
                if(data.length == 5){
                    nombre = data[0].trim();
                    numControl = data[1].trim();
                    String fecha = data[2].trim();
                    peso = Double.parseDouble(data[3].trim());
                    estatura = Double.parseDouble(data[4].trim());
                    datos[cuenta] = new Persona(nombre, numControl, fecha, 
                        peso, estatura); cuenta++; count++;
                } else
                    ep.outError("Formato incorrecto en la linea: " + linea, 
                    "ERROR DE CAPTURA");
            }
            ep.outInfo("Se han registrado "+count+" personas desde archivo.", 
                    "LECTURA EXITOSA");
        } catch (Exception e){
            ep.outAdv("Error al leer el archivo: "+ e.getMessage(), "ERROR");
        }
        ordenar();
    }
    //3. PROCESO
    void buscar(){
        int intentos = 0, inferior = 0, superior = cuenta - 1; 
        finded = false;
        String apellido;
        String titulo = "BUSCAR PERSONA";
        String mensaje = "Ingrese los apellidos de la persona a buscar.";
        String errorTitulo = "BUSQUEDA INVALIDA";
        String errorMensaje = "Debe ingresar al menos el primer apellido.";
        String error2t = "NO EXISTE EN LOS REGISTROS";
        String error2m = "No se encontró ninguna persona con esos apellidos.";

        if(cuenta == 0){
            ep.outAdv("No hay personas registradas aun.", "SIN REGISTROS");
            return;
        }
        apellido = ep.entryString(titulo, mensaje);
        if(apellido.trim().isEmpty() || apellido == null){
            ep.outError(errorMensaje, errorTitulo);
            return;
        }
        buscarApellido(apellido, inferior, superior, intentos);
        if (!finded) ep.outError(error2m, error2t);
    }
    //3.1 PROCESO
    void ordenar() {
        Arrays.sort(datos, 0, cuenta, new Comparator<Persona>() {
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

    //3.2 PROCESO
    void buscarApellido(String apellido, int inf, int sup, int ints){
        while (inf <= sup) {
            int pivote = (inf + sup) / 2; ints++;
            String[] partesActual = 
                datos[pivote].getNombre().trim().split(" ");
            String apellidoActual = partesActual[partesActual.length - 2] + " "
                    + partesActual[partesActual.length - 1];
            int comparacion = apellido.compareToIgnoreCase(apellidoActual);

            if (comparacion == 0) {
                ep.outInfo("Persona encontrada en la posición " + (pivote + 1)
                        + "\nNúmero de comparaciones realizadas: " + ints,
                        "BÚSQUEDA EXITOSA");
                ep.outPlain(datos[pivote].toString(), "PERSONA ENCONTRADA");
                finded = true; return;
            }
            if (comparacion < 0) sup = pivote - 1;
            else inf = pivote + 1;
        }
    }
    // 4.1 RESULTADOS
    void imprimirTerminal(){
        String resultado = ""; 
        
        if (cuenta == 0) {
            ep.outError("No hay personas registradas aún.", "SIN REGISTROS"); 
            return;
        } 
        for (int i = 0; i < cuenta; i++) 
            resultado += "\nPersona "+(i+1)+"\n"+ datos[i].toString();
        System.out.println("----- LISTA DE TODAS LAS PERSONAS REGISTRADAS -----"
                        + resultado);
        ep.outPlain("Revise la lista impresa en la terminal.",
                "IMPRESIÓN COMPLETA");
    }
    //4.2 RESULTADOS
    void imprimirArchivo() {
        String resultado = "";
        String contenido = "---- LISTA DE TODAS LAS PERSONAS REGISTRADAS ----";
        JFileChooser jfc = new JFileChooser();

        if (cuenta == 0) {
            ep.outError("No hay personas registradas aún.", "SIN REGISTROS"); 
            return;
        } 
        for (int i = 0; i < cuenta; i++) 
            resultado += "\nPersona " + (i + 1) + "\n" + datos[i].toString();
        contenido += resultado;

        jfc.setDialogTitle("Guardar archivo");
        int seleccion = jfc.showSaveDialog(null);

        if (seleccion == jfc.APPROVE_OPTION) {
            File archivo = jfc.getSelectedFile();

            if (!archivo.getName().toLowerCase().endsWith(".txt")) 
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(contenido);
                ep.outInfo("Archivo creado exitosamente en:\n" 
                    + archivo.getAbsolutePath(), "LISTA GUARDADA");
            } catch (IOException e) {
                ep.outError("Error al crear el archivo: " 
                    + e.getMessage(), "ERROR");
            }
        } else 
            ep.outAdv("Operación cancelada por el usuario.", "CANCELADO");
    }
    //4. RESULTADOS
    void imprimir(){
        int opcion;
        String titulo = "IMPRIMIR LISTA DE DATOS COMPLETA";
        String mensaje = "Seleccione la opcion que desee.";
        String opciones[] = {"Imprimir en terminal", "Guardar en archivo"};
        opcion = ep.entryOption(mensaje, titulo, opciones);

        switch(opcion){
            case 0 -> imprimirTerminal();
            case 1 -> imprimirArchivo();
        }
    }
    // 5. NAVEGABILIDAD
    int menu() {
        String titulo = "MENU PRINCIPAL";
        String mensaje = """
                Usted puede:
                1. Introducir datos manualmente
                2. Leer datos desde un archivo de texto
                3. Buscar una persona por nombre (búsqueda binaria)
                4. Imprimir toda la lista
                5. Salir del programa
                Elija la opción que desee...
                """;
        String opciones[] = {"Introducir datos", "Leer de archivo",
                "Buscar persona", "Imprimir lista", "SALIR"};

        opcion = ep.entryOption(mensaje, titulo, opciones);
        return opcion;
    }

    public static void main(String[] args){
        BusquedaBinaria bb = new BusquedaBinaria();
        bb.meta(); int opcion = bb.menu();

        while (opcion != -1 && opcion != 4){
            switch (opcion){
                case 0 -> bb.entrada();
                case 1 -> bb.archivo();
                case 2 -> bb.buscar();
                case 3 -> bb.imprimir();
            }
            opcion = bb.menu();
        }
        bb.ep.outPlain("Fin del programa.", "CERRANDO...");
    }
}
