# operacions a fer:
suma = lambda a, b: a + b
resta = lambda a, b: a - b
multiplicacion = lambda a, b: a * b
division = lambda a, b : a % b

#Funcio a la que se li indica en la cridada el nom dels fitxers origen-final
def Calculadora_linea(file_int, file_ext):
    try:
        #Obri el fitxer a mode de lectura y guardes el contingut a l'objecte f_int, el mateix pero per a sobrescriure amb f_ext
        with open(file_int, mode = "r", encoding="utf-8") as f_int, open(file_ext, mode = "w", encoding="utf-8") as f_ext:
            for operacio in f_int:
                try:
                    # Dividir en la linea els operadors i els signes
                    operadores = operacio.strip().split()
                    if len(operadores) != 3:
                        
                        #excepció
                        raise ValueError("Format incorrecte, massa operadors")
                    
                    #Utils
                    operador1, signe, operador2 = result
                    
                    operador1 = float(operador1)
                    operador2 = float(operador2)
                    
                    #operacions
                    if signe == "+":
                        result = suma(operador1, operador2)
                    elif signe == "-":
                        result = resta(operador1, operador2)
                    elif signe == "*":
                        result = multiplicacion(operador1, operador2)
                    elif signe == "%":
                        result = division(operador1, operador2)
                    else:
                        # Raise per utilitzat per a llançar una excepció
                        raise ValueError("no es pot operar")
                        
                        # Escriu el resultat al fitxer final, amb els operador, signes i resultats
                        f_ext.write("{operador1} {signe} {operador2} = {result}")
                except:
                    f_ext.write(f"Error: {str(e)}\n")
                    
    except FileNotFoundError:
        print("El arxiu d'entrada no 'sha trobat, o hi han errors operacionals")
    
# On volem executar:
Calculadora_linea("operacions.txt", "resultats.txt")
                    
 