/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumnos: Marlene Inés Moreno Velázquez
         Josue Abraham Segura Garcia
Exposicion: Hash Map
Fecha: 10 de junio de 2026

----- CLASE "TABLA HASH" CREACION DE CLAVES Y BUSQUEDA -----
*/
package exposicion;

public class TablaHash {
    private NodoPersona[] tabla;
    private int tamano;

    public TablaHash(int tamano) {
        int i;
        this.tamano = tamano;
        this.tabla = new NodoPersona[tamano];
        
        for (i = 0; i < tamano; i++)
            tabla[i] = null;
    }

    private int funcionHash(String clave) {
        int suma = 0, i;
        char caracter;
        
        for (i = 0; i < clave.length(); i++) {
            caracter = clave.charAt(i);
            suma = suma + (int) caracter;
        }
        return suma % tamano;
    }

    public int obtenerClaveRegistro(String nombre) {
        return funcionHash(nombre);
    }

    public void insertar(Persona p) {
        int indice;
        NodoPersona nuevo;
        NodoPersona actual;
        
        indice = funcionHash(p.getNombre());
        nuevo = new NodoPersona(p);
        
        if (tabla[indice] == null) {
            tabla[indice] = nuevo;
            return;
        }
        
        actual = tabla[indice];
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    public void buscar(String nombre) {
        int indice;
        int comparaciones;
        NodoPersona actual;
        boolean encontrado;
        indice = funcionHash(nombre);
        comparaciones = 0;
        encontrado = false;
        actual = tabla[indice];
        
        System.out.println("Buscando [" + nombre + "] en cubeta: " + indice);
        
        while (actual != null) {
            comparaciones++;
            if (actual.getDato().getNombre().equals(nombre)) {
                encontrado = true;
                break;
            }
            actual = actual.getSiguiente();
        }
        if (encontrado) {
            System.out.println("EXITO: Encontrado.");
            System.out.println("-> Clave de registro (Cubeta): " + indice);
            System.out.println("-> Rendimiento: " + comparaciones 
                    + " saltos en la lista.");
        } else System.out.println("FALLO: No existe. Se realizaron " 
                    + comparaciones + " saltos.");
    }

    public void mostrarTabla() {
        int i;
        NodoPersona actual;
        
        System.out.println("\n--- ESTADO DE LA TABLA HASH ---");
        for (i = 0; i < tamano; i++) {
            System.out.print("Cubeta [" + i + "]: ");
            actual = tabla[i];
            
            if (actual == null) System.out.print("Vacia");
            while (actual != null) {
                System.out.print("[" + actual.getDato().getNombre() + "] -> ");
                actual = actual.getSiguiente();
            }
            System.out.println("null");
        }
    }
}