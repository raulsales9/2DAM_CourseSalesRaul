def suma_enteros(sumando1, sumando2):
    if not isinstance(sumando1, int) or not isinstance(sumando2, int):
        raise TypeError('Proporciona solo enteros')
    else:
        resultado = sumando1 + sumando2
    return resultado