/* Direccion: \NetBeansProjects\EstructuraDatos\src\main\C/VariablesPrimitivas.c
Tecnológico Nacional de México
Instituto Tecnológico de León, Campus 1
Carrera: Ingeniería en Sistemas Computacionales
Materia: Estructura de Datos (Grupo B)
Horario:
    Lunes y miércoles de 8:45 a 10:25
    Viernes de 8:45 a 9:35
Alumno: Marlene Inés Moreno Velázquez
Ejercicio #11
Fecha: 4 de marzo de 2026.

----- DATOS PRIMITIVOS C -----
*/
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <float.h>

void meta() {
    printf("=== MAXIMOS Y MINIMOS EN C ===\n");
    printf("Programa que muestra informacion de tipos de datos primitivos.\n");
}

void byteD(){
    printf("\n=== BYTE (char en C) ===\n");
    printf("Espacio en bits: %d\n", CHAR_BIT);
    printf("Tamano en bytes: %d\n", (int)sizeof(char));
    printf("Valor minimo: %d\n", CHAR_MIN);
    printf("Valor maximo: %d\n", CHAR_MAX);
}

void shortD(){
    printf("\n=== SHORT ===\n");
    printf("Espacio en bits: %d\n", (int)(sizeof(short) * CHAR_BIT));
    printf("Tamano en bytes: %d\n", (int)sizeof(short));
    printf("Valor minimo: %d\n", SHRT_MIN);
    printf("Valor maximo: %d\n", SHRT_MAX);
}

void intD(){
    printf("\n=== INT ===\n");
    printf("Espacio en bits: %d\n", (int)(sizeof(int) * CHAR_BIT));
    printf("Tamano en bytes: %d\n", (int)sizeof(int));
    printf("Valor minimo: %d\n", INT_MIN);
    printf("Valor maximo: %d\n", INT_MAX);
}

void longD(){
    printf("\n=== LONG ===\n");
    printf("Espacio en bits: %d\n", (int)(sizeof(long) * CHAR_BIT));
    printf("Tamano en bytes: %d\n", (int)sizeof(long));
    printf("Valor minimo: %ld\n", LONG_MIN);
    printf("Valor maximo: %ld\n", LONG_MAX);
}

void floatD(){
    printf("\n=== FLOAT ===\n");
    printf("Espacio en bits: %d\n", (int)(sizeof(float) * CHAR_BIT));
    printf("Tamano en bytes: %d\n", (int)sizeof(float));
    printf("Valor minimo: %e\n", FLT_MIN);
    printf("Valor maximo: %e\n", FLT_MAX);
}

void doubleD(){
    printf("\n=== DOUBLE ===\n");
    printf("Espacio en bits: %d\n", (int)(sizeof(double) * CHAR_BIT));
    printf("Tamano en bytes: %d\n", (int)sizeof(double));
    printf("Valor minimo: %e\n", DBL_MIN);
    printf("Valor maximo: %e\n", DBL_MAX);
}

void charD(){
    printf("\n=== CHAR ===\n");
    printf("Espacio en bits: %d\n", CHAR_BIT);
    printf("Tamano en bytes: %d\n", (int)sizeof(char));
    printf("Valor minimo: %d\n", CHAR_MIN);
    printf("Valor maximo: %d\n", CHAR_MAX);
}

void blnD(){
    printf("\n=== BOOLEAN (simulado en C) ===\n");
    printf("Se usa int: 0 = false, distinto de 0 = true.\n");
}

void proceso(int opcion) {
    switch(opcion) {
        case 0: byteD(); break;
        case 1: shortD(); break;
        case 2: intD(); break;
        case 3: longD(); break;
        case 4: floatD(); break;
        case 5: doubleD(); break;
        case 6: charD(); break;
        case 7: blnD(); break;
        case -1: break;
        default: printf("\nOpcion invalida.\n"); break;
    }
}

void resultado(int opcion) {
    if (opcion == -1) 
        printf("\nSaliendo del programa...\n");
}
// Limpia el buffer de entrada
void limpiarBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

// Validacion
int leerEntero(const char* mensaje, int min, int max, int esSalida) {
    int valor;
    char buffer[100];
    int entradaValida;

    do {
        printf("%s", mensaje);
        if (fgets(buffer, sizeof(buffer), stdin) == NULL) 
            return -1;
        
        int vacio = 1;
        
        for (int i = 0; buffer[i] != '\0'; i++) 
            if (buffer[i] != ' ' && buffer[i] != '\n' && buffer[i] != '\t') {
                vacio = 0;
                break;
            }
        if (vacio) {
            printf("Entrada vacía. Intente de nuevo.\n");
            continue;
        }
        char *endptr;
        valor = strtol(buffer, &endptr, 10);

        if (*endptr != '\n' && *endptr != '\0') {
            printf("Error: Ingrese solo números enteros.\n");
            continue;
        }
        if (min != max || esSalida) 
            if (valor < min || valor > max) {
                printf("Error: El valor debe estar entre %d y %d.\n", min, max);
                continue;
            }
        entradaValida = 1;
    } while (!entradaValida);
    return valor;
}

int navegabilidad() {
    int opcion;
    const char* menu = 
        "\nMENU DE OPCIONES:\n"
        "0. Byte (char)\n"
        "1. Short\n"
        "2. Int\n"
        "3. Long\n"
        "4. Float\n"
        "5. Double\n"
        "6. Char\n"
        "7. Boolean\n"
        "-1. SALIR\n"
        "Ingrese una opcion: ";
    
    opcion = leerEntero(menu, -1, 7, 1); // rango -1 a 7
    return opcion;
}

int main(int argc, char** argv) {
    int opcion = 0;
    meta();

    do {
        opcion = navegabilidad();
        proceso(opcion);
        resultado(opcion);
    } while(opcion != -1);

    return 0;
}