/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #42
Fecha: 6 de junio de 2026

----- CLASE CONCRETA LISTA ADT -----
*/
package listaadt;

public class Lista implements Listable {
    private Nodo primero;
    private int capacidad;
    private int tamano;

    public Lista() {
        this.primero = null;
        this.capacidad = Integer.MAX_VALUE;
        this.tamano = 0;
    }

    public Lista(int capacidad) {
        if (capacidad <= 0) {
            System.out.println("Error: La capacidad debe ser mayor a cero.");
            this.capacidad = 10; 
        } else this.capacidad = capacidad;
        this.primero = null;
        this.tamano = 0;
    }

    public void add(Object o) {
        if (o == null) return;
        if (tamano >= capacidad) {
            System.out.println("Error: Capacidad de lista excedida.");
            return;
        }
        Nodo nuevo = new Nodo(o);
        if (isEmpty()) primero = nuevo;
        else {
            Nodo aux = primero;
            while (aux.getSiguiente() != null) aux = aux.getSiguiente();
            aux.setSiguiente(nuevo);
        }
        tamano++;
    }

    public void add(int i, Object o) {
        if (o == null) return;
        if (i < 0 || i > tamano) {
            System.out.println("Error: Índice fuera de rango continuo.");
            return;
        }
        if (tamano >= capacidad) {
            System.out.println("Error: Capacidad de lista excedida.");
            return;
        }
        
        Nodo nuevo = new Nodo(o);
        if (i == 0) {
            nuevo.setSiguiente(primero);
            primero = nuevo;
        } else {
            Nodo aux = primero;
            for (int k = 0; k < i - 1; k++) aux = aux.getSiguiente();
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }
        tamano++;
    }

    public Object remove(int i) {
        if (isEmpty() || i < 0 || i >= tamano) {
            System.out.println("Error: Índice inválido de eliminación.");
            return null;
        }
        Object eliminado;
        if (i == 0) {
            eliminado = primero.getDato();
            primero = primero.getSiguiente();
        } else {
            Nodo aux = primero;
            for (int k = 0; k < i - 1; k++) aux = aux.getSiguiente();
            eliminado = aux.getSiguiente().getDato();
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
        }
        tamano--;
        return eliminado;
    }

    public boolean remove(Object o) {
        if (o == null || isEmpty() || !contains(o)) return false;
        if (primero.getDato().equals(o)) {
            primero = primero.getSiguiente();
            tamano--;
            return true;
        }
        Nodo aux = primero;
        while (aux.getSiguiente() != null && 
            !aux.getSiguiente().getDato().equals(o)) 
            aux = aux.getSiguiente();
        if (aux.getSiguiente() != null) {
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
            tamano--;
            return true;
        }
        return false;
    }

    public Object get(int i) {
        if (i < 0 || i >= tamano) {
            System.out.println("Error: Índice de consulta fuera de límites.");
            return null;
        }
        Nodo aux = primero;
        for (int k = 0; k < i; k++) aux = aux.getSiguiente();
        return aux.getDato();
    }

    public boolean contains(Object o) {
        if (o == null) return false;
        Nodo aux = primero;
        while (aux != null) {
            if (aux.getDato().equals(o)) return true;
            aux = aux.getSiguiente();
        }
        return false;
    }

    public int size() {
        return tamano;
    }

    public boolean isEmpty() {
        return tamano == 0;
    }

    public Lista concat(Lista l) {
        if (l == null) return this;
        Lista nueva = new Lista(this.capacidad + l.capacidad);
        Nodo aux = this.primero;
        
        while (aux != null) {
            nueva.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        aux = l.primero;
        
        while (aux != null) {
            nueva.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        return nueva;
    }

    public boolean isEquals(Lista l) {
        if (l == null || this.tamano != l.tamano) return false;
        Nodo aux1 = this.primero;
        Nodo aux2 = l.primero;
        
        while (aux1 != null && aux2 != null) {
            if (!aux1.getDato().equals(aux2.getDato())) return false;
            aux1 = aux1.getSiguiente();
            aux2 = aux2.getSiguiente();
        }
        return true;
    }

    public Lista subLista(int d, int h) {
        if (d < 0 || h >= tamano || d > h) {
            System.out.println("Error: Delimitadores de rango incoherentes.");
            return null;
        }
        Lista nueva = new Lista(this.capacidad);
        Nodo aux = primero;
        
        for (int k = 0; k < d; k++) aux = aux.getSiguiente();
        for (int k = d; k <= h; k++) {
            if (aux != null) {
                nueva.add(aux.getDato());
                aux = aux.getSiguiente();
            }
        }
        return nueva;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo aux = primero;
        while (aux != null) {
            sb.append(aux.getDato());
            if (aux.getSiguiente() != null) sb.append("\n");
            aux = aux.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    @SuppressWarnings("removal")
    protected void finalize() throws Throwable {
        this.primero = null;
        this.tamano = 0;
        System.out.println("Destructor: Memoria del objeto Lista liberada.");
        super.finalize();
    }
}
