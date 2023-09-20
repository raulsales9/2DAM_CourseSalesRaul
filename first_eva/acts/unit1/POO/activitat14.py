from abc import ABC, abstractmethod
import math

class Figura(ABC):
    @abstractmethod
    def area(self) -> float:
        pass

    def perimetre(self) -> float:
        pass

class Cercle(Figura):
    def __init__(self, radi: float):
        self.radi = radi

    def area(self) -> float:
        return math.pi * self.radi ** 2

    def perimetre(self) -> float:
        return 2 * math.pi * self.radi

class Triangle(Figura):
    def __init__(self, costat: float):
        self.costat = costat

    def area(self) -> float:
        return (math.sqrt(3) / 4) * self.costat

    def perimetre(self) -> float:
        return 3 * self.costat

class Rectangle(Figura):
    def __init__(self, base: float, altura: float):
        self.base = base 
        self.altura = altura

    def area(self) -> float:
        return self.base * self.altura

    def perimetre(self) -> float:
        return 2 * (self.base + self.altura)
    
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