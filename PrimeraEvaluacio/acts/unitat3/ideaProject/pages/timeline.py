import json
import flet as ft
import os.path

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = '#FFF'
COLOR_BACKGROUND = '#cac9c9'
COLOR_BLUE = '#1190CB'
COLOR_SEMIDARKBLUE = '#0142f3bd'

def leer_datos():
    with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'r') as f:
        data = json.load(f)
    return data

class Timeline(ft.UserControl):
    def __init__(self, page : ft.Page ):
        super().__init__()
        self.page= page

    def build(self):
        data = leer_datos()
        nombre_usuario = data.get('nombre')
        seguidores = data.get('seguidores')
        seguidos = data.get('seguidos')
        posts = data.get('posts', [])

        post_controls = []
        for post in posts:
            post_controls.append(ft.Text(f"Titulo: {post['titulo']}", style={'font-size': '18px', 'font-weight': 'bold'}))
            post_controls.append(ft.Text(f"Contenido: {post['contenido']}", style={'font-size': '16px'}))
            post_controls.append(ft.Text(f"Fecha: {post['fecha']}", style={'font-size': '14px', 'color': COLOR_BLUE}))
            post_controls.append(ft.Divider())

        container1 = ft.Container(
            ft.Column(controls=[
                ft.Container(
                    ft.Column(controls=[
                        ft.Text("Conprova les teues estadistiques: ", style={'font-size': '24px', 'font-weight': 'bold', 'color': COLOR_PRIMARY}),
                        ft.Container(
                            ft.Row(controls=[
                                ft.Text(f"Nombre del usuario: {nombre_usuario}", style={'font-size': '18px', 'font-weight': 'bold'}),
                                ft.Text(f"Seguidores: {seguidores}", style={'font-size': '16px'}),
                                ft.Text(f"Seguidos: {seguidos}", style={'font-size': '16px'}),
                                ft.Image(src=f"../assets/pexels-josh-sorenson-976866.png", width=50, height=50),
                                ft.ElevatedButton("Seguir", color="primary")
                            ]),
                            ft.Divider(),
                            padding=10,
                            width=1800,
                            height=500,
                            bgcolor=COLOR_BACKGROUND,
                            margin=10,
                            col={"sm": 12, "md": 12, "xl": 12},
                        ) 
                    ]),
                    padding=10,
                    width=1800,
                ),
            ]),
            padding=10,
            width=1800,
            height=600,
            bgcolor=COLOR_BACKGROUND,
            margin=10,
            col={"sm": 12, "md": 12, "xl": 12},
        )

        return ft.ResponsiveRow(
            [
                container1,
                ft.Container(
                    ft.Column(controls=post_controls),
                    padding=10,
                    width=1800,
                    height=600,
                    bgcolor=COLOR_BACKGROUND,
                    margin=50,
                    col={"sm": 12, "md": 12, "xl": 12},
                )
            ],
        )

if __name__ == "__main__":
    def main(page: ft.Page):
        timeline = Timeline(page)
        page.add(timeline)
    ft.app(target=main)
