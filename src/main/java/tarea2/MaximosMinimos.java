/* Path: \EstructuraDatos\src\main\java\tarea2\
   File: MaximosMinimos.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio: #10 y Tarea #2
Fecha: 20 de abril de 2026

----- CLASE MAXIMOS Y MINIMOS -----

*/
package tarea2;

import tools.EntryPrompt;

public class MaximosMinimos {
    EntryPrompt ep = new EntryPrompt();
    String titulo1, resultado;
    
    void meta(){
        String titulo = "MAXIMOS Y MINIMOS EN JAVA";
        String mensaje = """
                Este programa imprime los valores maximos y minimos, 
                y mas detalles de las variables primitivas en Java.
                    - byte          - long
                    - short         - float
                    - int           - double 
                    - boolean       - char
                """;

        ep.outPlain(mensaje, titulo);
    }

    void byteData(){ //                             2.1 DATA
        this.titulo1 = "BYTE INFORMACION";
        this.resultado = "Tipo de dato: NUMERICO"
                    + "\nEspacio en bits: " + Byte.SIZE
                    + "\nTamano en bytes: " + Byte.BYTES
                    + "\nValor minimo: " + Byte.MIN_VALUE
                    + "\nValor maximo: " + Byte.MAX_VALUE; 
        resultados();
    }

    void shortData(){ //                             2.2 DATA
        this.titulo1 = "SHORT INFORMACION";
        this.resultado = "Tipo de dato: NUMERICO"
                    + "\nEspacio en bits: " + Short.SIZE
                    + "\nTamano en bytes: " + Short.BYTES
                    + "\nValor minimo: " + Short.MIN_VALUE
                    + "\nValor maximo: " + Short.MAX_VALUE; 
        resultados();
    }

    void intData(){ //                             2.3 DATA
        this.titulo1 = "INTEGER INFORMACION";
        this.resultado = "Tipo de dato: NUMERICO"
                    + "\nEspacio en bits: " + Integer.SIZE
                    + "\nTamano en bytes: " + Integer.BYTES
                    + "\nValor minimo: " + Integer.MIN_VALUE
                    + "\nValor maximo: " + Integer.MAX_VALUE; 
        resultados();
    }

    void longData(){ //                             2.4 DATA
        this.titulo1 = "LONG INFORMACION";
        this.resultado = "Tipo de dato: NUMERICO"
                    + "\nEspacio en bits: " + Long.SIZE
                    + "\nTamano en bytes: " + Long.BYTES
                    + "\nValor minimo: " + Long.MIN_VALUE
                    + "\nValor maximo: " + Long.MAX_VALUE; 
        resultados();
    }

    void floatData(){ //                             2.5 DATA
        this.titulo1 = "FLOAT INFORMACION";
        this.resultado = "Tipo de dato: NUMERICO DECIMAL"
                    + "\nEspacio en bits: " + Float.SIZE
                    + "\nTamano en bytes: " + Float.BYTES
                    + "\nValor minimo: " + Float.MIN_VALUE
                    + "\nValor maximo: " + Float.MAX_VALUE; 
        resultados();
    }

    void doubleData(){ //                             2.6 DATA
        this.titulo1 = "DOUBLE INFORMACION";
        this.resultado = "Tipo de dato: NUMERICO DECIMAL"
                    + "\nEspacio en bits: " + Double.SIZE
                    + "\nTamano en bytes: " + Double.BYTES
                    + "\nValor minimo: " + Double.MIN_VALUE
                    + "\nValor maximo: " + Double.MAX_VALUE; 
        resultados();
    }

    void charData(){ //                             2.7 DATA
        this.titulo1 = "CHARACTER INFORMACION";
        this.resultado = "Tipo de dato: CARACTER"
                    + "\nEspacio en bits: " + Character.SIZE
                    + "\nTamano en bytes: " + Character.BYTES
                    + "\nValor minimo: " + (int)Character.MIN_VALUE
                    + "\nValor maximo: " + (int)Character.MAX_VALUE; 
        resultados();
    }

    void booleanData(){ //                             2.8 DATA
        this.titulo1 = "BOOLEAN INFORMACION";
        this.resultado = "Tipo de dato: LOGICO"
                    + "\nValor minimo: " + Boolean.FALSE
                    + "\nValor maximo: " + Boolean.TRUE; 
        resultados();
    }

    void opciones(){ // 3.1 PROCESO
        String titulo = "TIPO DE DATOS EN JAVA";
        String mensaje = """
                Seleccione el tipo de dato que desee revisar sus propiedades.
                """;
        String opciones[] = {"Byte", "Short", "Integer", "Long",
                            "Float", "Double", "Character", "Boolean"};
        int salida = ep.entryOption(mensaje, titulo, opciones);
        proceso(salida);
    }
    
    void proceso(int opcion){
        switch(opcion){
            case 0 -> byteData();
            case 1 -> shortData();
            case 2 -> intData();
            case 3 -> longData();
            case 4 -> floatData();
            case 5 -> doubleData();
            case 6 -> charData();
            case 7 -> booleanData();
        }
    }

    void resultados(){
        ep.outPlain(resultado, titulo1);
    }

    int menu(){
        String titulo = "MAXIMOS Y MINIMOS EN JAVA";
        String mensaje = "¿Desea continuar desplegando más tipos de datos?";
        String opciones[] = {"Si (Continuar)", "No (Salir)"};

        return ep.entryOption(mensaje, titulo, opciones);
    }

    public static void main(String[] args){
        MaximosMinimos test = new MaximosMinimos();
        int option = 0;
        test.meta();

        while (option == 0){
            test.opciones();
            option = test.menu();
        }
    }
}
