import flet as ft
from flet import *

class QrScanner(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.title = "Scann Products"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 0
        self.page.window_width = 430
        self.page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()

    def build(self):
        main = ft.Container(
            content=ft.Column([
                ft.Row([
                    ft.Text("Scann products and \n      get EcoPoints", style=ft.TextStyle(color="black", size=32),
                            text_align=ft.alignment.center),
                ],alignment=ft.MainAxisAlignment.CENTER),

                ft.Image(
                    src="img/codigo_qr.png",
                    width=225,
                    height=225,
                ),

                ft.Image(
                    src="img/button.png",
                    width=70,
                    height=70,
                ),

                ft.Row([
                    ft.Text("Press the button to start \n                  or", style=ft.TextStyle(color="black", size=24),
                            text_align=ft.alignment.center),
                ], alignment=ft.MainAxisAlignment.CENTER),

                ft.Container(
                    content=ft.Text("Check Your Activity", style=ft.TextStyle(color=ft.colors.WHITE,size=32)),
                    width=311,
                    height=50,
                    bgcolor="#2A7516",
                    alignment=ft.alignment.center,
                    on_click=lambda e: print("Check Your Activity"),
                )
            ],
            horizontal_alignment=ft.CrossAxisAlignment.CENTER
            ),
            height=self.page.window_height,
            width=self.page.window_width,
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit, 
        )
        return ft.ResponsiveRow([main])

if __name__ == '__main__':
    def main(page: Page):
        qr = QrScanner(page)
        page.add(qr.build())
    ft.app(target=main, assets_dir="../assets/")
