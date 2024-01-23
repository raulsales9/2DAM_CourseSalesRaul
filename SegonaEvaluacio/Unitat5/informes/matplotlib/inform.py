import matplotlib.pyplot as plt
import os.path
import json

# os.path al  json
json_path = os.path.join(os.path.dirname(__file__), "data.json")

# llegir el arxiu JSON
with open(json_path, 'r') as archivo_json:
    datos_json = json.load(archivo_json)

# recorrem les dados del json
meses = datos_json["meses"]
for producto in datos_json["productos"]:
    plt.plot(meses, producto["ventas_mensuales"], label=producto["nombre"])

plt.title("Ventas Mensuales por Producto")
plt.xlabel("Mes")
plt.ylabel("Ventas")
plt.legend()
plt.grid(True)

plt.show()
