import flet as ft
import datetime

class Transports(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.theme_mode = ft.ThemeMode.LIGHT
        self.page.padding = 50
        self.page.window_width = 430
        self.page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()
        
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Transport",
                style=ft.TextStyle(
                    color="white",
                    size=36,
                ),
            ),
            bgcolor="#63d138",
            automatically_imply_leading=False,
            toolbar_height=100,
            center_title=True,
        )
        self.page.add(self.appbar)

        self.products = [
            {"date": "03/08/2003", "kms": 16},
            {"date": "04/08/2003", "kms": 20},
        ]
        self.kms_input = ft.TextField(label="Introduce los kilómetros")

    def build(self):
        squares = ft.Column(expand=1, wrap=False, scroll="always")

        for product in self.products:
            squares.controls.append(
                ft.Container(
                    content=ft.Row([
                        ft.Text(" ", style=ft.TextStyle(color="black", size=16)),
                        ft.Container(
                            content=ft.Image(src="imagenes_transport/bici_verde_icono.png", width=50, height=42),
                        ),
                        ft.Text(product["date"] + "   kms: " + str(product["kms"]), style=ft.TextStyle(color="black", size=16)),
                    ]),
                    width=311,
                    height=42,
                    padding=0,
                    bgcolor="#D9D9D9",
                    border_radius=ft.border_radius.all(10),
                )
            )

        main = ft.Container(
            content=ft.Column([
                ft.Row([
                    ft.Text("Total kms:", style=ft.TextStyle(color="black", size=48),
                            text_align=ft.alignment.center),
                ], alignment=ft.MainAxisAlignment.CENTER),

                ft.Row([
                    ft.Text(str(sum(product["kms"] for product in self.products)) + " km", style=ft.TextStyle(color="black", size=38),
                            text_align=ft.alignment.center),
                ], alignment=ft.MainAxisAlignment.CENTER),

                ft.Row([
                    ft.Text("  History:", style=ft.TextStyle(color="black", size=24),
                            text_align=ft.alignment.center_left),
                ], alignment=ft.MainAxisAlignment.START),

                ft.Container(
                    content=squares,
                    height=140,
                ),

                ft.Container(
                    height=5
                ),

                ft.Row([
                    self.kms_input,
                ]),

                ft.Row([

                    ft.Container(
                        content=ft.Text("Check Your Activity", style=ft.TextStyle(color=ft.colors.WHITE, size=32)),
                        width=311,
                        height=50,
                        bgcolor="#2A7516",
                        alignment=ft.alignment.center,
                        on_click=self.add_recycling,

                    )
                ],alignment=ft.alignment.center,)
            ],
            horizontal_alignment=ft.CrossAxisAlignment.CENTER
            ),
            height=self.page.window_height,
            width=self.page.window_width,
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit
        )
        return main

    def add_recycling(self, sender):
        kms = self.kms_input.value
        if kms:
            new_recycling = {"date": datetime.datetime.now().strftime("%d/%m/%Y"), "kms": int(kms)}
            self.products.append(new_recycling)
            self.page.clean()
            self.kms_input.value = ""
            self.page.add(self)


if __name__ == '__main__':
    def main(page: ft.Page):
        transports = Transports(page)
        page.add(transports.build())
    ft.app(target=main, assets_dir="../assets/")
