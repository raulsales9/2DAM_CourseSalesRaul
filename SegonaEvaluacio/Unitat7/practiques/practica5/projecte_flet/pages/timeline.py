import json
import flet as ft
import os.path
from pages.content import leer_datos, escribir_datos, leer_eventos
from assets.colors import *

class Timeline(ft.UserControl):
    def __init__(self, page : ft.Page ):
        super().__init__()
        self.page= page

    def build(self):
        data = leer_datos()

        user_controls = []
        for username, user_data in data.items():
            nombre_usuario = user_data.get('nombre')
            seguidores = user_data.get('seguidores')
            seguidos = user_data.get('seguidos')

            # Get posts of the user
            posts = user_data.get('posts', [])

            post_controls = []
            for post in posts:
                if 'usuario' in post:
                    post_controls.append(ft.Text(f"Publicado por: {post['usuario']}", style={'font-size': '18px', 'font-weight': 'bold'}))
                if 'titulo' in post:
                    post_controls.append(ft.Text(f"Titulo: {post['titulo']}", style={'font-size': '18px', 'font-weight': 'bold'}))
                if 'contenido' in post:
                    post_controls.append(ft.Text(f"Contenido: {post['contenido']}", style={'font-size': '16px'}))
                if 'fecha' in post:
                    post_controls.append(ft.Text(f"Fecha: {post['fecha']}", style={'font-size': '14px', 'color': COLOR_BLUE}))
                post_controls.append(ft.Divider())

            user_controls.append(
                ft.Container(
                    ft.Column(controls=[
                        ft.Text(f"Nombre del usuario: {nombre_usuario}", style={'font-size': '18px', 'font-weight': 'bold'}),
                        ft.Text(f"Seguidores: {seguidores}", style={'font-size': '16px'}),
                        ft.Text(f"Seguidos: {seguidos}", style={'font-size': '16px'}),
                        ft.Image(src=f"../assets/pexels-josh-sorenson-976866.png", width=50, height=50),
                        ft.ElevatedButton("Seguir", color="primary"),
                        ft.Row(controls=post_controls),
                        ft.Divider(),
                    ]),
                    padding=10,
                    width=1800,
                )
            )

        return ft.ResponsiveRow(user_controls)

if __name__ == "__main__":
    def main(page: ft.Page):
        timeline = Timeline(page)
        page.add(timeline)
    ft.app(target=main)
