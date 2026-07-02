/* 
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #38
Fecha: 6 de mayo de 2026

----- CLASE CURVA HILBERT -----
*/
package curvahilbert;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class CurvaHilbert extends JPanel {
    private int orden;
    private int x, y;
    private Graphics g;
    private Scanner teclado = new Scanner(System.in);

    public void meta() {
        System.out.println("\nGRAFICADOR RECURSIVO DE LA CURVA DE HILBERT (FRACTAL)");
        System.out.println("Permite visualizar la curva de Hilbert en cualquier");
        System.out.println("nivel de orden.\n");
    }

    public int capInt(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese unicamente un numero entero valido.");
            }
        }
    }

    public void data() {
        boolean isValid = false;
        while (!isValid) {
            orden = capInt("Ingrese el nivel de orden de la curva (Sugerido 1 - 6):");
            if (orden < 1) 
                System.out.println("El orden del fractal debe ser mayor o igual a 1.");
            else if (orden > 7) 
                System.out.println("Ingrese un valor en menor o igual que 6.");
            else isValid = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        g.setColor(Color.RED); // Color de trazado de la curva
        
        int n = (int) Math.pow(2, orden);
        int padding = 40;
        int ladoSoportado = Math.min(getWidth(), getHeight()) - (padding * 2);
        int dist = ladoSoportado / (n - 1);
        if (dist < 1) dist = 1;
        
        int anchoTotal = dist * (n - 1);
        x = padding + (getWidth() - (padding * 2) - anchoTotal) / 2;
        y = padding + (getHeight() - (padding * 2) - anchoTotal) / 2 + anchoTotal;
        
        hilbert(orden, 0, 1, dist);
    }

    private void hilbert(int nivel, int direccion, int rotacion, int dist) {
        if (nivel == 0) return;

        direccion += rotacion;
        hilbert(nivel - 1, direccion, -rotacion, dist);
        mover(direccion & 3, dist);

        direccion -= rotacion;
        hilbert(nivel - 1, direccion, rotacion, dist);
        mover(direccion & 3, dist);

        hilbert(nivel - 1, direccion, rotacion, dist);
        direccion -= rotacion;
        mover(direccion & 3, dist);

        hilbert(nivel - 1, direccion, -rotacion, dist);
    }

    private void mover(int dir, int dist) {
        int oldX = x;
        int oldY = y;
        switch (dir) {
            case 0: y -= dist; break; // Hacia Arriba
            case 1: x += dist; break; // Hacia la Derecha
            case 2: y += dist; break; // Hacia Abajo
            case 3: x -= dist; break; // Hacia la Izquierda
        }
        g.drawLine(oldX, oldY, x, y);
    }

    public void mostrarVentana() {
        JFrame ventana = new JFrame("Curva Fractal de Hilbert - Orden " + orden);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.BLACK); 
        ventana.add(this);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public String capOpcion(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String entrada = teclado.nextLine().trim().toUpperCase();
            if (entrada.equals("S") || entrada.equals("N")) {
                return entrada;
            }
            System.out.println("Error: Opcion no valida. Ingrese estrictamente 'S' o 'N'.");
        }
    }

    public static void main(String[] args) {
        CurvaHilbert ch = new CurvaHilbert();
        String resp = "S";
        ch.meta();
        while (resp.equalsIgnoreCase("S")) {
            ch.data();
            SwingUtilities.invokeLater(() -> ch.mostrarVentana());
            resp = ch.capOpcion("¿Desea dibujar otra curva de Hilbert? s/n:");
        }
        System.out.println("\nEjecución finalizada.");
    }
}