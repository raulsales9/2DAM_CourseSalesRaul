import flet as ft
from flet import *

class Settings(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.title = "Terms & conditions"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 20
        self.page.window_width = 430
        self.page.window_height = 932
        self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()

    def build(self):
        pb = ft.PopupMenuButton(
            content=ft.Row([
                ft.Text("English"),
                ft.Image(src="img/english.png", width=40, height=26),
            ], alignment=ft.alignment.center),
        )

        def change_language(language, event):
            if language:
                pb.content = ft.Row([
                    ft.Text("Spanish"),
                    ft.Image(src="img/spanish.jpg", width=40, height=26),
                ], alignment=ft.alignment.center)
                print("hola")
            else:
                pb.content = ft.Row([
                    ft.Text("English"),
                    ft.Image(src="img/english.png", width=40, height=26),
                ], alignment=ft.alignment.center)
            print(language)
            self.page.update()

        pb.items = [
            ft.PopupMenuItem(
                content=ft.Container(
                    content=ft.Row([
                        ft.Text("Spanish"),
                        ft.Image(src="img/spanish.jpg", width=40, height=26),
                    ], alignment=ft.alignment.center)
                ),
                on_click=lambda e: change_language(True, e)
            ),
            ft.PopupMenuItem(
                content=ft.Container(
                    content=ft.Row([
                        ft.Text("English"),
                        ft.Image(src="img/english.png", width=40, height=26),
                    ], alignment=ft.alignment.center)
                ),
                on_click=lambda e: change_language(False, e)
            ),
        ]

        self.page.appbar = ft.AppBar(
            title=ft.Text(
                "Terms & conditions",
                style=ft.TextStyle(
                    color="white",
                    size=36,
                ),
            ),
            bgcolor="#63d138",
            automatically_imply_leading=False,
            toolbar_height=151,
            center_title=True
        )

        main = ft.Container(
            content=ft.Column([
                pb,
                ft.Container(
                    content=ft.Text(
                        "Politics of privacy",
                        style=ft.TextStyle(
                            color="#2D7516",
                            size=20,
                        ),
                    ),
                    on_click=lambda e: self.page.go("/politicofprivacity"),
                    padding=15

                ),
                ft.Container(
                    content=ft.Text(
                        "Terms & conditions",
                        style=ft.TextStyle(
                            color="#2D7516",
                            size=20,
                        ),
                    ),
                    on_click=lambda e: self.page.go("/termsandconditions"),
                    padding=20

                ),
                ft.Container(
                    content=ft.Text(
                        "Authors: Fernando y Raul",
                        style=ft.TextStyle(
                            color="black",
                            size=20,
                        ),
                    ),
                    padding=20
                ),
                ft.Container(
                    content=ft.Text(
                        "Do you need anything?",
                        style=ft.TextStyle(
                            color="black",
                            size=20,
                        ),
                    ),
                    padding=10
                ),
                ft.Container(
                    content=ft.Row([
                        ft.Container(
                            content=
                            ft.Text("Do you have any questions?", style=ft.TextStyle(color="black"), size=12),
                            width=279,
                            height=44,
                            bgcolor="grey",
                            alignment=ft.alignment.center,
                            on_click=lambda e: print("chat")),
                        ft.Image(src="img/chatgpg.png", width=63, height=51)
                    ],
                        alignment=ft.MainAxisAlignment.SPACE_BETWEEN
                    ),
                    padding=10
                ),
                ft.Container(
                    content=ft.Text("Log Out", style=ft.TextStyle(color="#D9D9D9")),
                    width=170,
                    height=47,
                    bgcolor="#C0496E",
                    opacity=0.54,
                    alignment=ft.alignment.center,
                    on_click=lambda e: print("log out"),
                )
            ], horizontal_alignment=ft.CrossAxisAlignment.CENTER),
            padding=15,
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit
        )

        return ft.ResponsiveRow([main])

if __name__ == '__main__':
    def main(page: ft.Page):
        settings = Settings(page)
        page.add(settings)
    ft.app(target=main, assets_dir="../assets/")
