/*
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
 */
package tools;

public class DayArslan{
    private String monthSP, monthEN;
    private int day, year, monthNumber;
    public final int currentYear = 2026;

    public DayArslan(int dd, int mm, int yyyy){
        this.day = dd;
        this.monthNumber = mm;
        this.year = yyyy;
        switch(monthNumber){
            case 1 -> { monthEN = "January"; monthSP = "Enero";}
            case 2 -> { monthEN = "February"; monthSP = "Febrero";}
            case 3 -> { monthEN = "March"; monthSP = "Marzo";}
            case 4 -> { monthEN = "April"; monthSP = "Abril";}
            case 5 -> { monthEN = "May"; monthSP = "Mayo";}
            case 6 -> { monthEN = "June"; monthSP = "Junio";}
            case 7 -> { monthEN = "July"; monthSP = "Julio";}
            case 8 -> { monthEN = "August"; monthSP = "Agosto";}
            case 9 -> { monthEN = "September"; monthSP = "Septiembre";}
            case 10 -> {monthEN = "October"; monthSP = "Octubre";}
            case 11 -> {monthEN = "November"; monthSP = "Noviembre";}
            case 12 -> {monthEN = "December"; monthSP = "Diciembre";}
        }
    }

    public DayArslan(){}

    public boolean setDay(int dd){
        if (dd > 31 || dd < 1)
            return false;
        else return true;
    }

    public boolean setMonth(int mm){
        if (mm > 12 || mm < 1)
            return false;
        else return true;
    }

    public boolean setYear(int yyyy){
        if (yyyy > currentYear || yyyy < currentYear-100)
            return false;
        else return true;
    }

    public boolean isValidDay(int dd, int mm, int yyyy) {
        if (yyyy < 1960 || yyyy > this.currentYear) return false;
        if (mm < 1 || mm > 12) return false;
        boolean esBisiesto = (yyyy % 4 == 0 && yyyy % 100 != 0) || 
                            (yyyy % 400 == 0);
        int diasEnMes;
        switch (mm) {
            case 1, 3, 5, 7, 8, 10, 12 -> diasEnMes = 31;
            case 4, 6, 9, 11 -> diasEnMes = 30;
            case 2 ->  diasEnMes = esBisiesto ? 29 : 28;
            default -> {return false;}
        }
        return dd >= 1 && dd <= diasEnMes;
    }

    public String getStringDate(int typeOfFormat, boolean inSpanish){
        String date = "", days, months, years;
        if(inSpanish){
            switch(typeOfFormat){
            case 1: 
                if(this.day < 10) days = "0" + this.day; 
                else days = Integer.toString(this.day);
                if(this.monthNumber < 10) months = "0" + this.monthNumber;
                else months = Integer.toString(this.monthNumber);
                years = Integer.toString(this.year);
                date = days + "/" + months + "/"+ years; break;
            case 2:
                days = Integer.toString(this.day); 
                months = this.monthSP; 
                years = Integer.toString(this.year);
                date = days + " de " + months + " del " + years; break;
            }
        } else{
            switch(typeOfFormat){
            case 1: 
                if(this.day < 10) days = "0" + this.day; 
                else days = Integer.toString(this.day);
                if(this.monthNumber < 10) months = "0" + this.monthNumber;
                else months = Integer.toString(this.monthNumber);
                years = Integer.toString(this.year);
                date = months + "/" + days + "/"+ years; break;
            case 2:
                String ord;
                days = Integer.toString(this.day); 
                if(day == 1 || day == 11 || day == 21 || day == 31)
                    ord = "st";
                else if(day == 2 || day == 12 || day == 22)
                    ord = "nd";
                else if(day == 3 || day == 13 || day == 23)
                    ord = "rd";
                else ord = "th";
                months = this.monthEN; 
                years = Integer.toString(this.year);
                date = months + ", " + days +  ord +", " + years; break;
            }
        }
        return date;
    }

    public int slashNumericFormat(){return 1;}
    
    public int textFormat(){return 2;}
}
