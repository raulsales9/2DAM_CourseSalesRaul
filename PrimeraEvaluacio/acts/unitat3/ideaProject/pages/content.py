import flet as ft
import os.path
import json

def leer_datos():
    with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'r') as f:
        data = json.load(f)
    return data

def escribir_datos(data):
    with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'w') as f:
        json.dump(data, f)

def leer_eventos():
    with open(os.path.join(os.path.dirname(__file__),'events.json'), 'r') as f:
        data = json.load(f)
    return data

