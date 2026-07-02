/* Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #39
Fecha: 25 de mayo de 2026

----- CLASE RECORRIDO DEL CABALLO (KNIGHT'S TOUR) -----
*/
package knighttour;

public class KnightTour { 
    int N = 8; 
    int sol[][] = new int[N][N];
    int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2}; 
    int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1}; 
    boolean exito;
    
    void meta() {
        String msj = """
                    ===========================================================
                                PROBLEMA DEL RECORRIDO DEL CABALLO 
                    ===========================================================
                    Este programa resuelve el dilema del recorrido del caballo 
                    en un tablero de ajedrez de 8x8 empleando la tecnica de 
                    Vuelta Atras. El algoritmo busca una secuencia donde la 
                    pieza visite cada casilla exactamente una sola vez. Si no 
                    se halla un camino completo válido, notificara la 
                    inexistencia de solucion.
                    ===========================================================
                    """;
        
        System.out.println(msj);
    }

    // Data: No hay
    
    void proceso() {
        for (int x = 0; x < N; x++) 
            for (int y = 0; y < N; y++) sol[x][y] = -1; 
        sol[0][0] = 0;   
        exito = solveKTUtil(0, 0, 1);
    }

    void resultados() {
        if (!exito) 
            System.out.println("La solucion no existe para las dimensiones o "
                            + "la raiz especificada.");
        else {
            System.out.println("RECORRIDO ENCONTRADO CON ÉXITO:\n");
            printSolution();
        }
    }

    boolean isSafe(int x, int y) { 
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1); 
    } 
  
    boolean solveKTUtil(int x, int y, int movei) { 
        int k, next_x, next_y; 

        if (movei == N * N) return true; 
        
        for (k = 0; k < N; k++) { 
            next_x = x + xMove[k]; 
            next_y = y + yMove[k]; 
            
            if (isSafe(next_x, next_y)) { 
                sol[next_x][next_y] = movei; 
                
                if (solveKTUtil(next_x, next_y, movei + 1)) return true; 
                else sol[next_x][next_y] = -1; 
            } 
        } 
        return false; 
    } 
  
    void printSolution() { 
        for (int x = 0; x < N; x++) { 
            for (int y = 0; y < N; y++) 
                System.out.print(sol[x][y] + "\t"); 
            System.out.println(); 
        } 
    } 

    public static void main(String args[]) { 
        KnightTour kt = new KnightTour();

        kt.meta(); 
        kt.proceso(); 
        kt.resultados();
    } 
}