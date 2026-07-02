/* TimeElapsed.java
por Cirino Silva Tovar
para ilustrar aritmetica de fechas
el 9 de agosto 2006
*/
package tools;
import java.util.*;
import java.text.*;

public class TimeElapsed {
  long inicio = System.currentTimeMillis();
  Scanner sc = new Scanner(System.in);
  public long fin, te;
  Date dia;

  void inicial() {
    System.out.println("Calcula tiempo de ejecucion del programa\n");
  }

  void entrada() {
    DateFormat formateadorFecha = DateFormat.getDateInstance();

    dia = new Date();
    System.out.println("hola: hoy es :"+formateadorFecha.format(dia));
    System.out.print("\nDeme <enter> para continuar:");
    sc.nextLine();    
  }

  public void resulta() {
    DateFormat formateadorHora = 
      DateFormat.getTimeInstance(DateFormat.MEDIUM,Locale.FRANCE);

    System.out.print("\nEl tiempo transcurrido durante la ejecucion es:");
    System.out.println(formateadorHora.format(dia));
  }

  public void calcula() {
    fin = System.currentTimeMillis();
    te = fin - inicio;
    dia = new Date(te+3600l*6000l);    
  }

  public static void main(String args[]){
    TimeElapsed timeElap = new TimeElapsed();

    timeElap.inicial();
    timeElap.entrada();
    timeElap.calcula();
    timeElap.resulta();
  }
}



