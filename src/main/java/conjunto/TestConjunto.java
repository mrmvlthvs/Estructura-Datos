/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\conjunto\TestConjunto.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #19
Fecha: 18 de marzo de 2026

----- ALGORITMO PRUEBA TEST CONJUNTO ADT -----
*/
package conjunto;

import tools.EntryPrompt;

public class TestConjunto {
    EntryPrompt ep = new EntryPrompt();
    Conjunto a = new Conjunto();
    Conjunto b = new Conjunto();
    boolean back = false;

    void meta(){
        String mensaje = """
                        El siguiente programa permite al usuario probar las 
                        diferentes prestaciones del programa Conjunto.
                        """;

        ep.outInfo(mensaje, "CONJUNTO - TEST PROGRAMA");
    }

    void data(){
        int size = leerTamanio("Conjunto A", "Ingrese la cantidad de "
                + "elementos de este conjunto."); 
        conjA(size);
        size = leerTamanio("Conjunto B", "Ingrese la cantidad de "
                + "elementos de este conjunto."); 
        conjB(size);
        
        menuPrincipal(); // Regresar a menu principal
    }
    // 2.0 Validación: método para leer tamaño positivo (>0)
    int leerTamanio(String titulo, String mensaje) {
        int size;

        do {
            size = ep.entryInt(0, titulo, mensaje); 
            if (size <= 0) 
                ep.outError("El tamaño debe ser un número entero positivo.",
                            "TAMAÑO INVÁLIDO");
            else if (size > 25)
                ep.outError("Limite de tamaño alcanzado.", "LIMITE ALCANZADO");
        } while (size <= 0 || size > 25);
        return size;
    }

    int dato() {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            String aux = ep.entryString("Agregar elemento", "Ingrese un valor "
                                        + "entero como elemento.");
            try {
                numero = Integer.parseInt(aux);
                valido = true;  // si llega aquí, es entero válido
            } catch (NumberFormatException e) {
                ep.outError("No es un número entero válido. Intente de nuevo.",
                            "ERROR DE FORMATO");
                // el bucle continúa
            }
        }
        return numero;
    }
    // 2.1 Data
    void conjA(int size){
        int elemento;
        for(int i = 0; i < size; i++){
            do {
                elemento = dato();
                if (!esElementoUnico(elemento, 0)) 
                    ep.outError("Intente con otro valor diferente.", 
                                "ELEMENTO REPETIDO");
                else break;
            } while (true);
            a.add(elemento);
        }
    }
    // 2.2 Data
    void conjB(int size) {
        int elemento;
        for (int i = 0; i < size; i++) {
            do {
                elemento = dato();
                if (!esElementoUnico(elemento, 1)) 
                    ep.outError("Intente con otro valor diferente.", 
                                "ELEMENTO REPETIDO");
                else break;
            } while (true);
            b.add(elemento);
        }
    }

    boolean esElementoUnico(int value, int wconj) {
        switch(wconj) {
            case 0: if (a.contains(value)) {
                        ep.outError("El elemento " + value 
                            + " ya existe en el Conjunto A."
                            + " No se pueden repetir elementos"
                            + " dentro del mismo conjunto.", 
                            "ELEMENTO DUPLICADO");
                        return false;
                    }
                    return true;
            case 1: if (b.contains(value)) {
                        ep.outError("El elemento " + value 
                            + " ya existe en el Conjunto B."
                            + " No se pueden repetir elementos"
                            + " dentro del mismo conjunto.", 
                            "ELEMENTO DUPLICADO");
                        return false;
                }
                return true;
            default:
                return false;
        }
    }

    // 3.1 Operacion
    void agregarElemento(int value, int wconj){
        if (esElementoUnico(value, wconj)) {
            switch(wconj){
                case 0 -> a.add(value);
                case 1 -> b.add(value);
            }
            ep.outInfo("Elemento " + value + " agregado correctamente.", 
                    "AGREGADO");
        } else 
            ep.outError("No se pudo agregar el elemento porque ya existe.", 
                    "OPERACIÓN CANCELADA");
    }

    // 3.2 Operacion
    void eliminarElemento(int value, int wconj){
        switch(wconj){
            case 0 -> a.remove(value);
            case 1 -> b.remove(value);
        }
    }

    // 3.3 Operacion
    void encontrarElemento(int value, int wconj){
        switch(wconj){
            case 0 -> ep.outInfo("Elemento " + value 
                    + " esta en el conjunto?\n" + a.contains(value), 
                    "Encontrar en Conjunto A"); 
            case 1 -> ep.outInfo("Elemento " + value 
                    + " esta en el conjunto?\n" + b.contains(value), 
                    "Encontrar en Conjunto B"); 
        }
    }

    // 4. Resultados
    void resultado(Conjunto operacion, int op){
        switch(op){
            case 0 -> {ep.outPlain("A union con B = " + operacion, 
                    "RESULTADOS");}
            case 1 -> {ep.outPlain("A interseccion con B = " + operacion, 
                    "RESULTADOS");}
            case 2 -> {ep.outPlain("A diferencia con B = " + operacion, 
                    "RESULTADOS");}
        }
    }

    // 5. Navegabilidad
    void menuPrincipal(){
        String opciones1[] = {"Editar conjunto", "Realizar operacion", 
                            "Salir"};
        int opcion = ep.entryOption("Elija una opcion", "MENU PRINCIPAL", 
                            opciones1); 
        
        menuAccion(opcion);
    }

    // 5.1 Navegabilidad
    void menuOperaciones(int op){
        switch(op){
            case 0 -> { Conjunto union = a.union(b);
                        resultado(union, op);}
            case 1 -> { Conjunto inter = a.intersection(b);
                        resultado(inter, op);}
            case 2 -> { Conjunto diff = a.difference(b);
                        resultado(diff, op);}
        }
    }

    // 5.2 Navegabilidad
    void menuAccion(int op){
        int o;
        String opciones2[] = { "Agregar elemento", "Eliminar elemento",
                                "Encontrar elemento", "Rehacer conjunto", 
                                "Ver tamano de conjunto"};
        String opciones3[] = {"Union", "Interseccion", "Diferencia"}; 

        switch(op){
            case 0 -> { o = ep.entryOption("Elija una opcion", 
                        "Editar conjunto", opciones2);
                        menuEditar(o);} 
            case 1 -> { o = ep.entryOption("Elija una opcion", 
                        "Realizar operacion", opciones3);
                        menuOperaciones(o);}
            case 2 -> back = true;
            case -1 -> back = true;
        }
    }

    // 5.3 Navegabilidad
    void menuEditar(int op){
        int e; 
        String conjs[] = {"A", "B"};
        int wconj = ep.entryOption("Eliga el conjunto a continuacion.", 
                "SELECCIONAR CONJUNTO", conjs);

        switch(op){
            case 0 -> {e = ep.entryInt(0,"AGREGAR ELEMENTO","Ingrese un valor "
                    +"entero como elemento. \n(Valor por defecto = 0)"); 
                    agregarElemento(e, wconj);}
            case 1 -> {e = ep.entryInt(0,"ELIMINAR ELEMENTO","Ingrese un valor"
                    +" del elemento a eliminar. \n(Valor por defecto = 0)"); 
                    eliminarElemento(e, wconj);}
            case 2 -> {e = ep.entryInt(0, "ENCONTRAR ELEMENTO", "Ingrese un " +
                    "valor del elemento a encontrar. \n" 
                    + "(Valor por defecto = 0)"); 
                    encontrarElemento(e, wconj);}
            case 3 -> { int size; 
                        switch(wconj){
                            case 0 -> { // Validar tamaño de conjunto A
                                size = leerTamanio("Conjunto A", "Ingrese la "
                                + "cantidad de elementos de este conjunto.");
                                conjA(size);
                            } 
                            case 1 -> { // Validar tamaño de conjunto B
                                size = leerTamanio("Conjunto B", "Ingrese la "
                                +  "cantidad de elementos de este conjunto.");
                                conjB(size);
                            }
                        }}
            case 4 -> {swcnj(wconj);} 
        }
    }

    void swcnj(int wconj){
        switch(wconj){
            case 0: ep.outInfo("Tamaño de conjunto A = " 
                        + a.size(), "TAMAÑO DE CONJUNTO");
                        break;
            case 1: ep.outInfo("Tamaño de conjunto B = " 
                        + b.size(), "TAMAÑO DE CONJUNTO");
                        break;}
    }

    public static void main(String args[]){
        TestConjunto tc = new TestConjunto();
        
        tc.meta();
        tc.data(); 
        while(!tc.back) tc.menuPrincipal();
    }
}