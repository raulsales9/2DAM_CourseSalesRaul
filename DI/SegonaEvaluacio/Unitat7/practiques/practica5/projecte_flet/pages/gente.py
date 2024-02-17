import flet as ft
import json
import os.path
from pages.content import leer_datos, escribir_datos, leer_eventos
from assets.colors import *

class Gente(ft.UserControl):
    def __init__(self, page):
        super().__init__()
        self.page = page

    def build(self):
        data = leer_datos()
        user_controls = []
        #user_controls = ft.ListView(auto_scroll=True)
        for username, user_data in data.items():
            nombre_usuario = user_data.get('nombre')
            seguidores = user_data.get('seguidores')
            seguidos = user_data.get('seguidos')

            user_controls.append(
                ft.Container(
                    ft.Column(controls=[
                        ft.Text(f"Nombre del usuario: {nombre_usuario}", style={'font-size': '18px', 'font-weight': 'bold'}),
                        ft.Text(f"Seguidores: {seguidores}", style={'font-size': '16px'}),
                        ft.Text(f"Seguidos: {seguidos}", style={'font-size': '16px'}),
                        ft.Image(src=f"../assets/pexels-josh-sorenson-976866.png", width=50, height=50),
                        ft.ElevatedButton("Seguir", color="primary"),
                        ft.Divider(),
                    ]),
                    padding=10,
                    width=1800,
                )
            )

        return ft.ResponsiveRow(user_controls)

if __name__ == "__main__":
    def main(page: ft.Page):
        gente = Gente(page)
        page.add(gente)
    ft.app(target=main)
