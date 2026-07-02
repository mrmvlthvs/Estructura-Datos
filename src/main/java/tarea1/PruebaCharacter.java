/* Direccion de archivo: \NetBeansProjects\EstructuraDatos\src\main\java\tarea1\PruebaCharacter.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Tarea #1
Fecha: 11 de marzo de 2026

----- ALGORITMO PRUEBA CHARACTER -----
*/
package tarea1;

import tools.EntryPrompt;
import java.lang.Character;

public class PruebaCharacter {
    EntryPrompt ep = new EntryPrompt();
    char caracter; String entrada, ch;
    
    void meta(){
        String mensaje = """
                    Solicitar al usuario ingresar el carácter de su elección
                    y posteriormente analizar qué tipo de carácter ingreso 
                    para finalmente imprimir la información correspondiente
                    en pantalla para mostrarse al usuario.""";
        String titulo = "PRUEBA CHARACTER JAVA";
        
        ep.outInfo(mensaje, titulo);
    }
    
    String data(){
        boolean isValid = false; entrada = "";
        String mensaje = "Ingrese el caracter de su eleccion";
        String titulo = "Ingresar Caracter";
        String aux = """
                            Entrada vacia. Asegurese de ingresar 
                            al menos un caracter.""";

        do {
            entrada = ep.entryString(titulo, mensaje);
            if (entrada.isEmpty())
                ep.outAdv(aux, titulo);
        } while (entrada.isEmpty());
        caracter = entrada.charAt(0);
        return Character.toString(caracter);
    }

    void proceso() {
        String mensaje = "";
        String m1 = """
                \nNOTA: Usted ingreso una cadena de texto, por lo tanto solo
                se tomara en cuenta el primer caracter para el analisis."""; 
        ch = data();
        
        if (Character.isLetter(caracter)) {
            mensaje = "Usted ingresó " + ch + " y es una letra ";
            if (Character.isUpperCase(caracter)) 
                mensaje += "mayúscula.";
            else if (Character.isLowerCase(caracter)) 
                mensaje += "minúscula.";
        } 
        else if (Character.isDigit(caracter)) 
            mensaje = "Usted ingresó " + ch + " y es un dígito.";
        else 
            mensaje = "Usted ingresó "+ ch + " y no es ni letra ni dígito.";    
        if (entrada.length() > 1) 
            mensaje += m1 + "\nEntrada original: " + entrada;
        ep.outInfo(mensaje, "Resultado - PRUEBA CHARACTER JAVA");
        ch = null;
    }
    
    void salida(){ // <------ RESULTADOS - CIERRE DEL PROGRAMA
        ep.outPlain("FIN DEL PROGRAMA", "PRUEBA CHARACTER JAVA");
    }

    void ciclo(){ // <----- Navegabilidad (Menu & Ciclo)
        String opciones[] = {"Si", "No"}; //valor default
        String mensaje = "¿Quiere volver a intentarlo?";
        String titulo = "Regresar - PRUEBA CHARACTER JAVA";
        int opcion = 0;
        
        while (opcion == 0) { //0 = Si, 1 = No
            proceso();
            opcion = ep.entryOption(mensaje, titulo, opciones); 
        }
    }
    
    public static void main(String args[]){// <------- Navegabilidad
        PruebaCharacter test = new PruebaCharacter();
        
        test.meta();
        test.ciclo();
        test.salida();
    }
}
