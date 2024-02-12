import flet as ft
from flet import *

users_json = [
    {"nombre": "Pepe Sandía", "ruta_imagen": "img/usuario1.png", "mensaje": "Devuelveme mis EcoCoins!!!",
     "hora": "16:46"},
    {"nombre": "Juan", "ruta_imagen": "img/usuario2.png", "mensaje": "Quieres quedar este finde?", "hora": "12:00"},
    {"nombre": "Carles", "ruta_imagen": "img/usuario3.png", "mensaje": "Como estás?", "hora": "15:23"},
    {"nombre": "Eco group", "ruta_imagen": "img/usuario4.png", "mensaje": "Recordad que el lunes tenemos la quedada",
     "hora": "09:00"},
]

missatges_json = [
    {"missatge": "Hola, como estás?", "propio": 1},
    {"missatge": "Muy bien", "propio": 0},
    {"missatge": "Como estás tu?", "propio": 0},
    {"missatge": "Genial!!!!", "propio": 1},
    {"missatge": "Quieres quedar este finde?", "propio": 1},
]

class Message(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.title = "Contacts"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 20
        self.page.window_width = 430
        self.page.window_height = 932
        self.page.bgcolor = "#F0FCE9"
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    def build(self):
        user = ft.Container(
            content=ft.Row([
                ft.Image(
                    src=users_json[0]["ruta_imagen"],
                    width=110,
                    height=110,
                ),
                ft.Column([
                    ft.Text(users_json[0]["nombre"], style=ft.TextStyle(color="black", size=20)),
                    ft.Text("Última vez a las " + users_json[0]["hora"], style=ft.TextStyle(color="black", size=13)),
                ]),
            ]),
            width=341,
            height=150,
            padding=0,
            border_radius=ft.border_radius.all(10),
        )

        squares = ft.Column(expand=1, wrap=False, scroll="always")

        for item in missatges_json:
            if item["propio"] == 1:
                alignment=ft.alignment.center_left
            else:
                alignment=ft.alignment.center_right

            squares.controls.append(
                ft.Container(
                    content=ft.Container(ft.Row([
                        ft.Text(item["missatge"], style=ft.TextStyle(color=ft.colors.WHITE, size=13))
                    ], alignment=ft.MainAxisAlignment.CENTER),
                        width=174,
                        height=49,
                        padding=0,
                        bgcolor="#2A7516",
                        border_radius=ft.border_radius.all(10),
                    ),
                    alignment=alignment
                )
            )

        main = ft.Container(
            content=ft.Column([
                user, squares
            ]),
            height=self.page.window_height,
            width=self.page.window_width,
        )
        return main

if __name__ == '__main__':
    def main(page: ft.Page):
        registry = Message(page)
        page.add(registry)
    ft.app(target=main, assets_dir="../assets/")
