import flet as ft
import json
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

class Gente(ft.UserControl):
    def __init__(self, page):
        super().__init__()
        self.page = page

    def build(self):
        data = leer_datos()
        nombre_usuario = data.get('nombre')
        container1 = ft.Container(
            ft.Column(controls=[
                ft.Container(
                    ft.Column(controls=[
                        ft.Text("Conprova les teues estadistiques: ", style={'font-size': '20px', 'font-weight': 'bold'}),
                        ft.Row([
                            ft.Text(f"Nombre del usuario:  {nombre_usuario}", style={'font-size': '16px'}),
                            ft.Image(src=f"../assets/pexels-josh-sorenson-976866.png", width=50, height=50),
                            ft.ElevatedButton("Seguir", color="primary")
                        ])
                    ]),
                    padding=5,
                    width=1800,
                    height=800,
                    bgcolor=ft.colors.WHITE,
                    margin=10,
                    col={"sm": 12, "md": 12, "xl": 12}, 
                )     
            ]),
            padding=5,
            height=850,
            bgcolor=ft.colors.GREY_300,
            margin=5,
            col={"sm": 12, "md": 12, "xl": 12},
        )


        return ft.ResponsiveRow([ container1],)
    

if __name__ == "__main__":
    def main(page: ft.Page):
        gente = Gente(page)
        page.add(gente)
    ft.app(target=main)

