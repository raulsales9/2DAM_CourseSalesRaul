def factorial(n):
    if not isinstance(n, int):
        raise TypeError("El valor ha de ser un enter")
    if n < 0:
        raise ValueError("El valor no pot ser negatiu")
    if n == 0 or n == 1:
        return 1
    else:
        return n * factorial(n-1)

