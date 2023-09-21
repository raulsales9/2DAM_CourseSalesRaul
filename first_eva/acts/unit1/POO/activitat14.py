from abc import ABC, abstractmethod
import math

class Figura(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

    @abstractmethod
    def perimetre(self) -> float:
        pass

class Cercle(Figura):
    def __init__(self, radi: float):
        self._radi = radi

    @property
    def radi(self):
        return self._radi

    @radi.setter
    def radi(self, value):
        if value < 0:
            raise ValueError("El radi no pot ser negatiu")
        self._radi = value

    def area(self) -> float:
        return math.pi * self._radi ** 2

    def perimetre(self) -> float:
        return 2 * math.pi * self._radi

class Triangle(Figura):
    def __init__(self, costat: float):
        self._costat = costat

    @property
    def costat(self):
        return self._costat

    @costat.setter
    def costat(self, value):
        if value < 0:
            raise ValueError("El costat no pot ser negatiu")
        self._costat = value

    def area(self) -> float:
        return (math.sqrt(3) / 4) * self._costat

    def perimetre(self) -> float:
        return 3 * self._costat

class Rectangle(Figura):
    def __init__(self, base: float, altura: float):
        self._base = base
        self._altura = altura

    @property
    def base(self):
        return self._base

    @base.setter
    def base(self, value):
        if value < 0:
            raise ValueError("La base no pot ser negativa")
        self._base = value

    @property
    def altura(self):
        return self._altura

    @altura.setter
    def altura(self, value):
        if value < 0:
            raise ValueError("L'altura no pot ser negativa")
        self._altura = value

    def area(self) -> float:
        return self._base * self._altura

    def perimetre(self) -> float:
        return 2 * (self._base + self._altura)

class Quadrat(Rectangle):
    def __init__(self, costat: float):
        super().__init__(costat, costat)
        
# donem valors per al calcul
cercle = Cercle(8)
triangle = Triangle(10)
rectangle = Rectangle(7, 4)
quadrat = Quadrat(4)

# \n per a imprimir per consola salto linea
print("\nCercle: ")
print("Area: " + str(cercle.area()))
print("Perimetre: " + str(cercle.perimetre()))

print("\nTriangle: ")
print("Area: " + str(triangle.area()))
print("Perimetre: " + str(triangle.perimetre()))

print("\nRectangle: ")
print("Area: " + str(rectangle.area()))
print("Perimetre: " + str(rectangle.perimetre()))

print("\nQuadrat: ")
print("Area: " + str(quadrat.area()))
print("Perimetre: " + str(quadrat.perimetre()))
