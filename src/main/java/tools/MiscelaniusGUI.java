package tools;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;

public class MiscelaniusGUI {
  JOptionPane jop = new JOptionPane();
  String aux;
  
  public boolean isNum(String cad) {
    try {
      Double.parseDouble(cad);
      return true;
    } catch (NumberFormatException nfe) {
        jop.showMessageDialog(null, "\nchistoso digite un numero");
        return false;
      }
  }
  
  public boolean isByte(String cad) {
    try {
      Byte.parseByte(cad);
      return true;
    } catch (NumberFormatException nfe) {
        jop.showMessageDialog(null, "\nSOLO ENTEROS entre -128 A 127");
        return false;
      }
  }

  public double capReal(String prompt) {
    do aux = jop.showInputDialog(prompt);
    while(!isNum(aux));
    return Double.parseDouble(aux);
  }
  
  public double capRealPositive(String prompt) {
    double data = -1;
    String aux = "-1";
    
    do {
      do aux = jop.showInputDialog(prompt);
      while (!isNum(aux)); 
      data = Double.parseDouble(aux);
      if (data <= 0.0) 
        jop.showMessageDialog(null, "solo se aceptan valores positivos");
    } while (data <= 0.0);
    return data;
  }  
  
  public String capInt(String prompt) {
    do aux = jop.showInputDialog(prompt);
    while(!isNum(aux));
    return aux;
  }
  
  public byte capBytePositive(String prompt) {
    byte data = -1;
    String aux = "";
    
    do {
      do aux = jop.showInputDialog(prompt);
      while (!isByte(aux)); 
      data = Byte.parseByte(aux);
      if (data <= 0) 
        jop.showMessageDialog(null, "solo se aceptan valores positivos");
    } while (data <= 0);
    return data;
  }  
  
  public byte capByte(String prompt){
    do aux = jop.showInputDialog(prompt);
    while(!isByte(aux));
    return Byte.parseByte(aux);
  }
  
  public void display(String file, String msg) {
    String html = "<html><body width='%1s'><h1>Imagen</h1>"
                + "<h2>"+ msg +"<br><br>"
                + "<p>.";
            // change to alter the width 
    int w = 100;
    //JLabel label = new JLabel(msg);
    //label.setFont(new Font("Arial", Font.BOLD, 18));
    ImageIcon icon = new ImageIcon("images/"+file+".png");
    jop.showMessageDialog(null, String.format(html, w, w), 
                          "Imagen", jop.WARNING_MESSAGE, icon);
  }
}

