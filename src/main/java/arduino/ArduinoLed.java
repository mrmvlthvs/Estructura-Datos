package arduino;

import com.fazecast.jSerialComm.SerialPort;
//C:\Users\marle\Documents\GitHub\Estructura-de-Datos\lib\jSerialComm.jar

public class ArduinoLed {
    public static void main(String[] args){
        SerialPort puerto = SerialPort.getCommPort("COM5");
        puerto.setBaudRate(9600);
        puerto.openPort();

        puerto.writeBytes("1".getBytes(), 1); // Envía '1' para encender el LED
        System.out.println("LED encendido");
        
        puerto.closePort();
    }
}
