import math


def es_primo(numero):
    """
   Función para determinar si un número es primo:


   Entrada:
   - entero: número entero a determinar si es primo


   Salida:
   - True/False: en caso de ser primo o no
   """

    if numero < 2:
        return False

    for n in range(2, math.floor(math.sqrt(numero) + 1)):
        if numero % n == 0:
            return False

    return True

if __name__=='__main__':
    for i in range(2,100):
        print(f'{i} -> {es_primo(i)}')