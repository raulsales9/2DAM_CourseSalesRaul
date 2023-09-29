# Operacions a fer:
suma = lambda a, b: a + b
resta = lambda a, b: a - b
multiplicacion = lambda a, b: a * b
division = lambda a, b: a / b  # Corregit el signe '%' per '/'

# Funció a la qual se li indica en la cridada el nom dels fitxers origen-final
def Calculadora_linea(file_int, file_ext):
    try:
        # Obri el fitxer a mode de lectura i guarda el contingut a l'objecte f_int, el mateix per a sobrescriure amb f_ext
        with open(file_int, mode="r", encoding="utf-8") as f_int, open(file_ext, mode="w", encoding="utf-8") as f_ext:
            for operacio in f_int:
                try:
                    # Dividir en la línia els operadors i els signes
                    operadores = operacio.strip().split()
                    if len(operadores) != 3:
                        # Excepció
                        raise ValueError("Format incorrecte, massa operadors")

                    # Utils
                    operador1, signe, operador2 = operadores

                    operador1 = float(operador1)
                    operador2 = float(operador2)

                    # Operacions
                    if signe == "+":
                        result = suma(operador1, operador2)
                    elif signe == "-":
                        result = resta(operador1, operador2)
                    elif signe == "*":
                        result = multiplicacion(operador1, operador2)
                    elif signe == "/":
                        result = division(operador1, operador2)
                    else:
                        # Raise utilitzat per a llançar una excepció
                        raise ValueError("no es pot operar")

                    # Escriu el resultat al fitxer final, amb els operadors, signes i resultats
                    f_ext.write(f"{operador1} {signe} {operador2} = {result}\n")

                except Exception as e:
                    # Escriu l'error al fitxer final
                    f_ext.write(f"Error: {str(e)}\n")

    except FileNotFoundError:
        print("El fitxer d'entrada no s'ha trobat, o hi ha errors operacionals")

# On volem executar:
Calculadora_linea(r"C:\2DAM_Course\first_eva\acts\unit1\ErrorsExcepcions\operacions.txt", r"C:\2DAM_Course\first_eva\acts\unit1\ErrorsExcepcions\resultats.txt")