import flet as ft
from flet import *

class Main_app(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.window_width = 430
        self.page.window_height = 932
        self.page.bgcolor = "#F0FCE9"

    def build(self):
        self.page.title = "Views"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 50
        self.page.bgcolor = "#F0FCE9"
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()

        settings = ft.Container(
            content=Image(
            src="imagenes_main/configuracion.png",
            width=55,
            height=51,
            tooltip="Configuracion",
            fit=ImageFit.CONTAIN,
        ),
            padding=5,
            alignment=ft.alignment.top_right,
            on_click=lambda e: print("Configuracion"),
        )

        user = ft.Container(
            content=Image(
            src="imagenes_main/person.png",
                width=225,
                height=225,
        ),
            alignment=ft.alignment.top_center
        )

        money = ft.Container(
            content=ft.Row([
                Text("160", size=40, color="#2A7516"),
                Image(
                    src="imagenes_main/coin.png",
                    width=57,
                    height=50,
                )
            ],
                alignment=ft.MainAxisAlignment.CENTER
            ),
            padding=15,
            alignment=ft.alignment.center,
        )

        row1 = ft.Row([
            ft.Container(
                content=Image(
                src="imagenes_main/icon_calendario.png",
                width=76,
                height=89,
            ),
                on_click=lambda e: print("Calendario"),
                alignment=ft.alignment.center
            ),
            ft.Container(
                content=Image(
                src="imagenes_main/bici_icon.png",
                width=76,
                height=89,
            ),
                on_click=lambda e: print("Bici"),
                alignment=ft.alignment.center
            ),
            ft.Container(
                Image(
                    src="imagenes_main/reciclaje_icon.png",
                    width=76,
                    height=89,
                ),
                on_click=lambda e: print("Reciclaje"),
                alignment=ft.alignment.center
            ),
        ],
        alignment=ft.MainAxisAlignment.CENTER
        )

        row2 = ft.Row([
            ft.Container(
                Image(
                    src="imagenes_main/barras_icon.png",
                    width=76,
                    height=89,
                ),
                on_click=lambda e: print("Barras"),
                alignment=ft.alignment.center
            ),
            ft.Container(
                Image(
                    src="imagenes_main/compra_icono.png",
                    width=76,
                    height=89,
                ),
                on_click=lambda e: print("Compra"),
                alignment=ft.alignment.center
            ),
            ft.Container(
                Image(
                    src="imagenes_main/arbol_icon.png",
                    width=76,
                    height=89,
                ),
                on_click=lambda e: print("Arbol"),
                alignment=ft.alignment.center
            ),
        ],
        alignment=ft.MainAxisAlignment.CENTER
        )

        options = ft.Column([row1, row2],alignment=ft.alignment.center)
        main = ft.Container(
            content=ft.Column([
                settings, user, money, options
            ],
            horizontal_alignment=ft.CrossAxisAlignment.CENTER
            ),
            height=self.page.window_height,
            width=self.page.window_width
        )
        self.page.add(main)

if __name__ == '__main__':
    def main(page: ft.Page):
        init = Main_app(page)
        page.add(init)
    ft.app(target=main)
