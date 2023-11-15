import flet as ft
from views.register import Register
from views.login import Login
from views.gente import Gente
from views.timeline import Timeline

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = 'white'

class Main:
    def __init__(self) -> None:
        pass

    # Construir appication
    def build(self, page: ft.Page):
        page.bgcolor = COLOR_SECONDARY

        page.appbar = ft.AppBar(
            bgcolor=COLOR_PRIMARY,
            leading=ft.Image('./assets/logo_kurigram_color_slogan.png', width=50),
            leading_width=50,
            actions=[
                ft.Column([
                    ft.IconButton(ft.icons.HOME),
                ]),
                ft.Column([
                    ft.IconButton(ft.icons.PERSON),
                ]),
                ft.Column([
                    ft.IconButton(ft.icons.MENU),
                ]),
            ]
        )

        container1 = ft.Container(
            ft.Column([
                ft.Container(
                    ft.Column([
                        ft.Text("Perfil", style={"color": "black"}),
                        ft.Text("Hola, ¿cómo estás?", style={"color": "black"}),
                        ft.Text("Número 1", style={"color": "black"}),
                        ft.Text("Número 2", style={"color": "black"}),
                    ])  
                )     
            ]),
            padding=5,
            height=615,
            bgcolor=ft.colors.GREY_300,
            margin=5,
            col={"sm": 12, "md": 10, "xl": 4},
        )
        container2 = ft.Container(
            ft.Column([
                ft.Container(
                    content=ft.Column([
                        ft.Text("Comentarios", style={"color": "black"}),
                        ft.Text("Escribe aquí tu comentario", style={"color": "black"}),
                        ft.Text("Enviar", style={"color": "black"}),
                    ]), 
                    bgcolor=ft.colors.GREY_300,
                    height=150,
                    width=600,
                ),
                ft.Divider(),
                ft.Container(
                    content=ft.Column([
                        ft.Text("Más comentarios", style={"color": "black"}),
                        ft.Text("Escribe aquí otro comentario", style={"color": "black"}),
                        ft.Text("Enviar", style={"color": "black"}),
                    ]), 
                    bgcolor=ft.colors.GREY_300,
                    height=430,
                    width=600,
                ),
            ]),
            padding=5,
            col={"sm": 12, "md": 10, "xl": 4},
        )

        container3 = ft.Container(
                ft.Column([
                    ft.Text("Eventos", style={"color": "black"}),
                    #ft.Image("./assets/ideaProject/assets/pexels-josh-sorenson-976866.jpg", width=150, height=100),
                    ft.Text("", style={"color": "black"}),
                ]),
            padding=5,
            margin=5,
            height=615,
            bgcolor=ft.colors.GREY_300,
            col={"sm": 12, "md": 10, "xl": 4},
        )
        page.add(
            ft.ResponsiveRow(
                [
                    container1,
                    container2,
                    container3,
                ],
            ),
        )

        page.appbar.update()

    def main():
        pass

class Usuari:
    def InfoUsuari():
        usuari = {
            "user" : "raul",
            "avatar" : "",
            "seguidors" : 13,
            "seguits" : 1
        }
        return usuari

class Events:
    def InfoEvents():
        events = {
            "user" : "raul",
            "Data" : "2024/07/23",
            "Image" : "",
            "descripcio" : "Futbol en Cullera"
        }
        return events

class Publicacions:
    def ListPublicacions():
        Publicacions = {
            "user" : "raul",
            "Data" : "2024/07/23",
            "descripcio" : "lorem ipsum dolor"
        }
        return Publicacions

    def NovaPublicacio():
        publicacio = {
            "Descripcio" : "",
        }    
     
if __name__ == "__main__":
    main = Main
    ft.app(target=main, view='web_browser')
