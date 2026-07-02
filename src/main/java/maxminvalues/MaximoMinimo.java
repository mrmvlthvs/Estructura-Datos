/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\maxminvalues\MaximoMinimo.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #10
Fecha: 4 de marzo de 2026.

----- MAXIMOS Y MINIMOS DE DATOS PRIMITIVOS JAVA -----
*/
package maxminvalues;

import tools.EntryPrompt;

public class MaximoMinimo {
    EntryPrompt en = new EntryPrompt();
    int opcion;
    String resultado;
    String titulo;

    void meta(){
        String mensaje = """
                Este programa imprime los valores maximos y minimos, 
                y mas detalles de las variables primitivas en Java.
                    - byte          - long
                    - short         - float
                    - int           - double 
                    - boolean       - char
                """;

        en.outPlain(mensaje, "MAXIMOS Y MINIMOS EN JAVA");
    }

    void byteData(){
        titulo = "BYTE INFORMACION";
        resultado = "\nEspacio en bits: " + Byte.SIZE
                    + "\nTamano en bytes: " + Byte.BYTES
                    + "\nValor minimo: " + Byte.MIN_VALUE
                    + "\nValor maximo: " + Byte.MAX_VALUE;

        resultados();
    }

    void shortData(){
        titulo = "SHORT INFORMACION";
        resultado = "\nEspacio en bits: " + Short.SIZE
                    + "\nTamano en bytes: " + Short.BYTES
                    + "\nValor minimo: " + Short.MIN_VALUE
                    + "\nValor maximo: " + Short.MAX_VALUE;

        resultados();
    }

    void intData(){
        titulo = "INTEGER INFORMACION";
        resultado = "\nEspacio en bits: " + Integer.SIZE
                    + "\nTamano en bytes: " + Integer.BYTES
                    + "\nValor minimo: " + Integer.MIN_VALUE
                    + "\nValor maximo: " + Integer.MAX_VALUE;

        resultados();
    }

    void longData(){
        titulo = "LONG INFORMACION";
        resultado = "\nEspacio en bits: " + Long.SIZE
                    + "\nTamano en bytes: " + Long.BYTES
                    + "\nValor minimo: " + Long.MIN_VALUE
                    + "\nValor maximo: " + Long.MAX_VALUE;

        resultados();
    }

    void floatData(){
        titulo = "FLOAT INFORMACION";
        resultado = "\nEspacio en bits: " + Float.SIZE
                    + "\nTamano en bytes: " + Float.BYTES
                    + "\nValor minimo: " + Float.MIN_VALUE
                    + "\nValor maximo: " + Float.MAX_VALUE;

        resultados();
    }

    void doubleData(){
        titulo = "DOUBLE INFORMACION";
        resultado = "\nEspacio en bits: " + Double.SIZE
                    + "\nTamano en bytes: " + Double.BYTES
                    + "\nValor minimo: " + Double.MIN_VALUE
                    + "\nValor maximo: " + Double.MAX_VALUE;

        resultados();
    }

    void charData(){
        titulo = "CHARACTER INFORMACION";
        resultado = "\nEspacio en bits: " + (int)Character.SIZE
                    + "\nTamano en bytes: " + (int)Character.BYTES
                    + "\nValor minimo: " + (int)Character.MIN_VALUE
                    + "\nValor maximo: " + (int)Character.MAX_VALUE;

        resultados();
    }

    void booleanData(){
        titulo = "BOOLEAN INFORMACION";
        resultado = "\nTipo de variable primitiva: " + Boolean.TYPE
                    + "\nValor predeterminado: " + Boolean.FALSE
                    + "\nValores posibles: " + Boolean.TRUE
                    + " y " + Boolean.FALSE;

        resultados();
    }

    void resultados(){
        en.outInfo(resultado, titulo);
    }

    int menu(){
        String opciones[] = {"Byte", "Short", "Int", "Long", "Float", "Double",
                        "Char", "Boolean", "SALIR"};

        return en.entryOption("Elija el tipo de dato que desea informacion.", 
                "DATOS PRIMITIVOS JAVA", opciones); 
    }

    public static void main(String[] args) {
        MaximoMinimo m = new MaximoMinimo();
        m.meta(); m.opcion = m.menu();

        while(m.opcion != -1 && m.opcion != 8){
           switch(m.opcion){
                case 0 -> m.byteData();
                case 1 -> m.shortData();
                case 2 -> m.intData();
                case 3 -> m.longData();
                case 4 -> m.floatData();
                case 5 -> m.doubleData();
                case 6 -> m.charData();
                case 7 -> m.booleanData();
            } 
            m.opcion = m.menu();
        }        
    }
}