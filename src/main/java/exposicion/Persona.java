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

----- CLASE ENCAPSULADA "PERSONA" -----
*/
package exposicion;

public class Persona {
    private String nombre;

    public Persona(){
        this.nombre = "";
    }

    public Persona(String nombre){
        this.nombre = nombre;
    }

    public boolean setNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty() || nombre.isBlank())
            return false;
        else if(!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+"))
            return false;
        else if(nombre.length() < 7 || nombre.length() > 50)
            return false; 
        else{
            this.nombre = nombre.trim();
            return true;
        }
    }

    public String getNombre(){return nombre;}
}
