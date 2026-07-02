/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\tablaasciiibm\TablaAsciiIBM.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #12
Fecha: 11 de marzo de 2026

----- ALGORITMO TABLA ASCII IBM -----
*/
package tablaasciiibm;

import java.util.Scanner;

public class TablaAsciiIBM {
    private Scanner scn = new Scanner(System.in);
    private StringBuilder resultado;   // almacenará la tabla generada

    void meta() {
        System.out.println("""
            ================== TABLA ASCII IBM PC DE 1980 ==================
            Programa para desplegar la tabla de códigos ascii original de la 
            IBM PC de 1980.
            ================================================================
            """);
    }

    // 2. DATA: validar entrada del usuario
    private int leerOpcion(String mensaje, int min, int max) {
        int opcion = -1;
        while (true) {
            System.out.print(mensaje);
            if (scn.hasNextInt()) {
                opcion = scn.nextInt();
                if (opcion >= min && opcion <= max) {
                    scn.nextLine(); // limpiar buffer
                    return opcion;
                } else System.out.printf("Error: El valor debe estar entre %d"
                                        + " y %d.\n", min, max);
            } else {
                System.out.println("Error: Ingrese solo números enteros.");
                scn.next(); // descartar entrada incorrecta
            }
            scn.nextLine(); // limpiar buffer siempre
        }
    }

    // 3. PROCESO: genera la tabla según la opción
    void proceso(int opcion) {
        resultado = new StringBuilder();
        switch (opcion) {
            case 1 -> generarBloque(0, 31, 
                    "Bloque 1: Caracteres de Control y Símbolos (0 - 31)");
            case 2 -> generarBloque(32, 127, 
                    "Bloque 2: ASCII Estándar (32 - 127)");
            case 3 -> generarBloque(128, 255, 
                    "Bloque 3: ASCII Extendido (128 - 255)");
            case 4 -> {
                generarBloque(0, 31, 
                    "Bloque 1: Caracteres de Control y Símbolos (0 - 31)");
                generarBloque(32, 127, "Bloque 2: ASCII Estándar (32 - 127)");
                generarBloque(128, 255, 
                    "Bloque 3: ASCII Extendido (128 - 255)");
            }
            default -> resultado.append("Opción no válida.\n");
        }
    }

    // Método auxiliar para generar un bloque y agregarlo al StringBuilder
    private void generarBloque(int inicio, int fin, String titulo) {
        resultado.append("\n--- ").append(titulo).append(" ---\n");
        for (int codigo = inicio; codigo <= fin; codigo++) 
            imprimirFilaEnBuffer(codigo);
    }

    // Agrega una línea al buffer (sin imprimir directamente)
    private void imprimirFilaEnBuffer(int codigo) {
        if (codigo == 8 || codigo == 9 || codigo == 10 || codigo == 13) 
            resultado.append(String.format("Código: %3d | Carácter: "
                                        + "[Control/Espaciado]%n", codigo));
        else resultado.append(String.format("Código: %3d | Carácter: %c%n", 
                                        codigo, (char) codigo));
    }

    // 4. RESULTADOS: imprime lo obtenido en proceso()
    void resultados() {
        if (resultado != null && resultado.length() > 0) 
            System.out.print(resultado.toString());
        else System.out.println("No hay resultados para mostrar.");
    }

    // 5. NAVEGABILIDAD: menú, validación y bucle
    void navegabilidad() {
        int opcion;
        do {
            opcion = mostrarMenuYObtenerOpcion();
            if (opcion == 0) break;
            proceso(opcion);
            resultados();
            System.out.println("\n" + "=".repeat(60) + "\n");
        } while (true);
        System.out.println("""
            ================================================================
            =                       FIN DEL PROGRAMA                       =
            ================================================================
            """);
    }

    // Menú de navegabilidad
    private int mostrarMenuYObtenerOpcion() {
        System.out.println("""
            Ingrese el inciso de la opcion que desee o ingrese 0 para salir.
                1) Bloque 1 - Caracteres de Control y Simbolos.
                2) Bloque 2 - ASCII Estandar
                3) Bloque 3 - ASCII Extendido
                4) Todo el contenido completo
                0) SALIR
            ================================================================
            """);
        return leerOpcion("Su eleccion >>> ", 0, 4);
    }

    public static void main(String[] args) {
        TablaAsciiIBM app = new TablaAsciiIBM();
        app.meta();
        app.navegabilidad();
    }
}