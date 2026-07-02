/*
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #17
Fecha: 18 de marzo de 2026

----- ALGORITMO CONJUNTO ADT -----
*/
package conjunto;
import java.util.ArrayList;

public class Conjunto implements Conjuntable {
    private ArrayList<Integer> elementos;

    public Conjunto() {
        elementos = new ArrayList<>();
    }

    public void add(int e) {
        if (!elementos.contains(e)) elementos.add(e);
    }

    public void remove(int e) {
        elementos.remove((Integer) e);
    }

    public boolean contains(int e) {
        return elementos.contains(e);
    }

    public int size() {
        return elementos.size();
    }

    public Conjunto union(Conjunto c) {
        Conjunto res = new Conjunto();
        
        res.elementos.addAll(this.elementos);
        for (int e : c.elementos) 
            if (!res.elementos.contains(e)) res.elementos.add(e);                    
        return res;
    }

    public Conjunto intersection(Conjunto c) {
        Conjunto res = new Conjunto();

        for (int e : elementos) 
            if (c.elementos.contains(e)) res.add(e);
        return res;
    }

    public Conjunto difference(Conjunto c) {
        Conjunto res = new Conjunto();

        for (int e : elementos) 
            if (!c.elementos.contains(e)) res.add(e);
        return res;
    }
    
    @Override
    public String toString() {
        return elementos.toString();
    }
}
