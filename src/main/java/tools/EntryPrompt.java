package tools;
import javax.swing.JOptionPane;
/**
 *
 * @author marle
 */
public class EntryPrompt {
    private final int plainM = JOptionPane.PLAIN_MESSAGE;
    private final int infoM = JOptionPane.INFORMATION_MESSAGE;
    private final int advM = JOptionPane.WARNING_MESSAGE;
    private final int errorM = JOptionPane.ERROR_MESSAGE;
    private final int defaultO = JOptionPane.DEFAULT_OPTION;
    
    public String entryString(String titulo, String mensaje){
        String resultado = JOptionPane.showInputDialog(null, mensaje, titulo, 
                plainM());
        return resultado;
    }
    
    public int entryInt(int defaultValue, String titulo, String mensaje) { 
    int resultado = defaultValue;
        try { resultado = Integer.parseInt(JOptionPane.showInputDialog(null, 
                    mensaje, titulo, plainM()));} 
        catch(NumberFormatException e){e.getMessage();}
        return resultado;
    }
    
    public long entryLong(long defaultValue, String titulo, String mensaje) { 
    long resultado = defaultValue;
        try { resultado = Long.parseLong(JOptionPane.showInputDialog(null, 
                    mensaje, titulo, plainM()));} 
        catch(NumberFormatException e){e.getMessage();}
        return resultado;
    }
    
    public double entryDouble(double defaultValue, String titulo, 
            String mensaje){
        double resultado = defaultValue;
        try { resultado = Double.parseDouble(JOptionPane.showInputDialog(null, 
                mensaje, titulo, plainM()));} 
        catch(NumberFormatException e){ e.getMessage();}
        return resultado;
    }
    
    public int entryOption(String mensaje, String titulo, 
            String[] arrayOpciones){
        return JOptionPane.showOptionDialog(null, mensaje, titulo, defaultO, 
                plainM(), null, arrayOpciones, arrayOpciones[0]);
    }
    
    public void outPlain(String mensaje, String titulo){
       JOptionPane.showMessageDialog(null, mensaje, titulo, plainM());
    }
    public void outError(String mensaje, String titulo){
        JOptionPane.showMessageDialog(null, mensaje, titulo, errorM());
    }
    public void outInfo(String mensaje, String titulo){
        JOptionPane.showMessageDialog(null, mensaje, titulo, infoM());
    }
    public void outAdv(String mensaje, String titulo){
        JOptionPane.showMessageDialog(null, mensaje, titulo, advM());
    }
    
    public int errorM(){return errorM;}
    public int plainM(){return plainM;}
    public int infoM() {return infoM;}
    public int advM() {return advM;}
    public int defaultO() {return defaultO;}
}
