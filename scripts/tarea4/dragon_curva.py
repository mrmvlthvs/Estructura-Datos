import turtle
import _tkinter

class DragonLSystem:
    def __init__(self):
        self.orden = 0
        self.longitud = 0.0
        self.t = None
        self.colores = ["blue", "red", "green", "black", "yellow", "magenta"]

    def meta(self):
        print("=============== FRACTAL: DRAGON POR SISTEMA-L ===============")
        print("Programa que genera la Curva del Dragon mediante la evolucion")
        print("de cadenas (Gramatica L-System) e interpretacion grafica.")
        print("=============================================================")

    def data(self):
        print("\n--- Captura de Parametros Base ---")
        prompt_o = "Ingrese el numero de evoluciones/orden (0-16): "
        self.orden = self.capturar_entero(prompt_o, 0, 16)
        
        if self.orden <= 7:
            self.longitud = 15.0
        else:
            # Límite mínimo de 1.0 para mantener legibilidad en resoluciones altas
            self.longitud = max(1.0, 300.0 / (1.4142 ** self.orden))
            
        try:
            if self.t is None:
                turtle.setup(900, 700)
                self.t = turtle.Turtle()
            else:
                self.t.hideturtle()
        except (_tkinter.TclError, turtle.Terminator):
            turtle.clearscreen()
            turtle.setup(900, 700)
            self.t = turtle.Turtle()
            
        turtle.tracer(0, 0)
        self.t.speed(0)
        self.t.hideturtle()
        print("Componentes graficos listos.")

    def capturar_entero(self, prompt, minimo, maximo):
        while True:
            print(prompt, end="")
            aux = input()
            try:
                valor = int(aux)
                if valor >= minimo and valor <= maximo:
                    return valor
                print(f"Error: Rango permitido entre {minimo} y {maximo}.")
            except ValueError:
                print("Error: Ingrese un numero entero valido.")

    def proceso(self):
        print("\n--- Procesando Gramatica L-System ---")
        c_acu = "FX"
        
        for i in range(self.orden):
            c_acu = self.dragon_evolution(c_acu)
            
        print(f"Evolucion completada. Tamaño: {len(c_acu)} caracteres.")
        print("Trazando en ventana grafica...")
        
        fr_d = self.longitud * (1.4142 ** self.orden) 
        origen_x = - (fr_d / 3.5)
        origen_y = (fr_d / 4.0) if self.orden % 2 == 0 else -(fr_d / 12.0)
        
        if self.orden < 6:
            origen_x, origen_y = -50, 0
            
        self.t.penup()
        self.t.goto(origen_x, origen_y)
        self.t.pendown()
        
        self.interpret_dragon(c_acu)        
        turtle.update()

    def dragon_evolution(self, cadena_actual):
        resultado = []
        for caracter in cadena_actual:
            if caracter == 'X':
                resultado.append("X+YF+") 
            elif caracter == 'Y':
                resultado.append("-FX-Y") 
            else:
                resultado.append(caracter) 
        return "".join(resultado)

    def interpret_dragon(self, cadena_final):
        dr = 0        
        t_left = self.t.left
        t_right = self.t.right
        t_forward = self.t.forward
        t_pencolor = self.t.pencolor
        t_pencolor(self.colores[dr])        
        pasos_mismo_color = 0
        limite_bloque_color = 32 

        for caracter in cadena_final:
            if caracter == '+':
                t_left(90)  
            elif caracter == '-':
                t_right(90)
            elif caracter == 'F':
                t_forward(self.longitud)
                pasos_mismo_color += 1
                
                if pasos_mismo_color >= limite_bloque_color:
                    dr = (dr + 1) % len(self.colores)
                    t_pencolor(self.colores[dr])
                    pasos_mismo_color = 0

    def resultados(self):
        print("\nFractal generado de manera exitosa.")
        print("Puede visualizar el patron de colores en la ventana.")

    def navegabilidad(self):
        self.meta() 
        bandera_cierre = False
        
        while not bandera_cierre:
            self.data()
            self.proceso()
            self.resultados() 
            valido = False
            while not valido:
                print("\n¿Desea generar otra evolucion del fractal? (S/N): ", 
                        end="")
                respuesta = input().strip().upper()
                
                if respuesta == "S":
                    valido = True
                    
                    try:
                        self.t.reset()
                        self.t.hideturtle()
                    except (_tkinter.TclError, turtle.Terminator):
                        pass
                elif respuesta == "N":
                    valido = True
                    bandera_cierre = True
                else:
                    print("Error: Ingrese 'S' o 'N'.")
                    
        print("\nCerrando modulo de fractales...")
        try:
            turtle.done()
        except (_tkinter.TclError, turtle.Terminator):
            pass

if __name__ == "__main__":
    app = DragonLSystem()
    
    app.navegabilidad()