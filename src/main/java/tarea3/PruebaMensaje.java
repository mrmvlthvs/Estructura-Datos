package tarea3;

import java.util.Scanner;

public class PruebaMensaje {
    private Mensaje msg = new Mensaje();
    private Scanner sc = new Scanner(System.in);
    private String remitente;
    private String destinatario;
    private String asunto;
    private String contenido;

    void meta() {
        String mensaje = """
            ================ MENSAJE ADT - PRUEBA ================
            Programa que permite probar los axiomas del ADT Mensaje, 
            para redactar, enviar, visualizar, editar y eliminar 
            mensajes de forma segura y modular.
            ======================================================
            """;
        System.out.println(mensaje);
    }

    void data() {
        remitente = capturarRemitente("Ingrese el remitente (usted): ");
        destinatario = capturarDestinatario("Ingrese el destinatario: ");
        asunto = capturarAsunto("Ingrese el asunto (ENTER para omitir): ");
        contenido = capturarContenido("Ingrese el contenido (max 200 chars): ");
        
        msg = new Mensaje(asunto, contenido, remitente, destinatario);
        
        System.out.println("\n--- Datos capturados correctamente ---");
        System.out.println(msg.mostrarVistaPrevia());
        System.out.println("--------------------------------------\n");
    }

    String capturarRemitente(String prompt) {
        String aux;
        while (true) {
            System.out.print(prompt);
            aux = sc.nextLine();
            if (msg.setRemitente(aux)) return aux;
            System.out.println("Error: Longitud invalida (1-25 caracteres).");
        }
    }

    String capturarDestinatario(String prompt) {
        String aux;
        while (true) {
            System.out.print(prompt);
            aux = sc.nextLine();
            if (msg.setDestinatario(aux)) return aux;
            System.out.println("Error: Longitud invalida (1-25 caracteres).");
        }
    }

    String capturarAsunto(String prompt) {
        String aux;
        while (true) {
            System.out.print(prompt);
            aux = sc.nextLine();
            if (msg.setAsunto(aux)) return aux;
            System.out.println("Error: Longitud invalida (Max 20 caracteres).");
        }
    }

    String capturarContenido(String prompt) {
        String aux;
        while (true) {
            System.out.print(prompt);
            aux = sc.nextLine();
            if (msg.setContenido(aux)) return aux;
            System.out.println("Error: Longitud invalida (1-200 caracteres).");
        }
    }    

    void proceso(int option) {
        switch(option) {
            case 1: 
                if(msg.enviar()) System.out.println("Mensaje enviado con exito.");
                else System.out.println("Error al enviar el mensaje.");                
                break;
            case 2: 
                edicion(); 
                break;
            case 3: 
                eliminar(); 
                break;
            case 4:
                System.out.println("\nMostrando Mensaje Actual:");
                msg.marcarLeido();
                System.out.println(msg.mostrarMensaje());
                break;
        }
    }

    void edicion() {
        int opt = menuEdit();
        if (msg.getID() != -1){
            switch(opt) {
                case 1:
                    String nuevoRem = capturarRemitente("Nuevo remitente: ");
                    msg.setRemitente(nuevoRem);
                    break;
                case 2:
                    String nuevoDest = capturarDestinatario("Nuevo destinatario: ");
                    msg.setDestinatario(nuevoDest);
                    break;
                case 3:
                    String nuevoAsunto = capturarAsunto("Nuevo asunto: ");
                    msg.setAsunto(nuevoAsunto);
                    break;
                case 4:
                    String nuevoCont = capturarContenido("Nuevo contenido: ");
                    msg.modificarContenido(nuevoCont);
                    break;
            }
            System.out.println("Mensaje actualizado.");
        } else System.out.println("No existe el mensaje o fue eliminado.");
    }

    void eliminar() {
        if(msg.eliminar()) System.out.println("Mensaje eliminado.");
        msg.destruirMensaje();
    }

    void resultados() {
        System.out.println("Finalizando el sistema...");
    }

    void navegabilidad() {
        meta();
        data();
        
        int opcion = 0;
        while (opcion != 5) {
            opcion = menuPrincipal();
            if (opcion != 5) proceso(opcion);
        }
        resultados();
        sc.close();
    }

    int menuPrincipal() {
        String menu = """
            \nOPCIONES DEL MENSAJE:
            1) Enviar mensaje
            2) Editar mensaje
            3) Eliminar mensaje
            4) Ver mensaje actual
            5) Salir
            """;
        int opcion = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(menu + "Su eleccion >>> ");
            String aux = sc.nextLine();
            try {
                opcion = Integer.parseInt(aux);
                if (opcion >= 1 && opcion <= 5) valido = true;
                else System.out.println("Error: Ingrese un numero entre 1 y 5.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese unicamente un valor numerico.");
            }
        }
        return opcion;
    }

    int menuEdit() {
        String menu = """
            \nOPCIONES DE EDICION:
            1) Editar remitente
            2) Editar destinatario
            3) Editar asunto
            4) Editar contenido
            5) Cancelar
            """;
        int opcion = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(menu + "Su eleccion >>> ");
            String aux = sc.nextLine();
            try {
                opcion = Integer.parseInt(aux);
                if (opcion >= 1 && opcion <= 5) valido = true;
                else System.out.println("Error: Ingrese un numero entre 1 y 5.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato invalido.");
            }
        }
        return opcion;
    }

    public static void main(String[] args) {
        PruebaMensaje programa = new PruebaMensaje();
        programa.navegabilidad();
    }
}