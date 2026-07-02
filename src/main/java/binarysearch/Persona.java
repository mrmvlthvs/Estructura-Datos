/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\java\binarysearch\GrupoII.java
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #8, #9
Fecha: 2 de marzo de 2026

----- CLASE ENCAPSULADA PERSONA -----

*/
package binarysearch;
import tools.DayArslan;

public class Persona {
    private String nombre;
    private String numControl;
    private DayArslan fecha;
    private String fechaString = "";
    private double peso, estatura;

    public Persona(){
        this.nombre = "";
        this.numControl = "";
        this.fecha = null;
        this.peso = 0.0;
        this.estatura = 0.0;    
    }

    public Persona(String nombre, String numControl, 
        DayArslan fecha, double peso, double estatura){
        this.nombre = nombre;
        this.numControl = numControl;
        this.fecha = fecha;
        this.peso = peso;
        this.estatura = estatura;
    }

    public Persona(String nombre, String numControl, String fecha, double peso, double estatura){
        this.nombre = nombre;
        this.numControl = numControl;
        this.fechaString = fecha;
        this.peso = peso;
        this.estatura = estatura;
    }

    public boolean setNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty() || nombre.isBlank()){
            return false;
        } else if(!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")){
            return false;
        } else if(nombre.length() < 2 || nombre.length() > 50){ 
            return false; 
        } else{
            this.nombre = nombre.trim();
            return true;
        }
    }
    public boolean setNControl(String numControl){
        if(numControl == null || numControl.isBlank()){
            return false;
        }
        if(numControl.length() != 8){
            return false;
        }
        if(!numControl.matches("\\d{8}")){
            return false;
        }
        if(!numControl.substring(2, 4).equals("24")){
            return false;
        }
        int primerosDos = Integer.parseInt(numControl.substring(0, 2));
        if(primerosDos < 18 || primerosDos > 25){
            return false;
        }
        this.numControl = numControl;
        return true;
    }
    public boolean setFecha(int dd, int mm, int yyyy){
        DayArslan temp = new DayArslan(dd, mm, yyyy);
        if (!temp.isValidDay(dd, mm, yyyy)) {
            return false;
        } else {
            this.fecha = temp;
            return true;
        }
    }
    public boolean setPeso(double peso){
        if(peso <= 20 || peso > 250){
            return false;
        } else{
            this.peso = peso;
            return true;
        }
    }
    public boolean setEstatura(double estatura){
        if(estatura <= 0.4 || estatura > 2.5){
            return false;
        } else{
            this.estatura = estatura;
            return true;
        }
    }
    @Override
    public String toString(){
        if(fechaString.isEmpty() || fechaString.isBlank()){
            return "\tNombre: " + nombre
                + "\n\tNumero de control: " + numControl
                + "\n\tFecha de Nacimiento: " + fecha.getStringDate(fecha.slashNumericFormat(), true)
                + "\n\tPeso en Kg: " + peso + "kg"
                + "\n\tEstatura en mts: " + estatura + "m";
        } else {
            return "\tNombre: " + nombre
                + "\n\tNumero de control: " + numControl
                + "\n\tFecha de Nacimiento: " + fechaString
                + "\n\tPeso en Kg: " + peso + "kg"
                + "\n\tEstatura en mts: " + estatura + "m";
        }
    }

    public String getNombre(){return nombre;}
    public String getNControl(){return numControl;}
    public String getFechaN() { return fecha.getStringDate(fecha.slashNumericFormat(), true); } 
    //public void setFechaN(String fecha) { this.fecha = fecha; }
    public double getPeso(){return peso;}
    public double getEstatura(){return estatura;}

    public DayArslan getFecha() {
        return fecha;
    }

    public void setFecha(DayArslan fecha) {
        this.fecha = fecha;
    }
}
