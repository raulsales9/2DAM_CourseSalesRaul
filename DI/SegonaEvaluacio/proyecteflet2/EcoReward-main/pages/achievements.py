import flet as ft

class Archivements(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.title = "Archivements"
        self.page.theme_mode = ft.ThemeMode.LIGHT
        self.page.padding = 0
        self.page.window_width = 430
        self.page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()

    def build(self):
        """ self.appbar = ft.AppBar( 
            title=ft.Text(
                "Eco Transports",
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
        self.page.add(self.appbar) """

        rows = []
        for i in range(1, 10, 3):
            row = ft.Row(
                [
                    ft.Container(
                        content=ft.Image(src=f"img/{i}.png", width=112, height=112),
                        on_click=lambda e: print(f"Imagen {i}"),
                    ),
                    ft.Container(
                        content=ft.Image(src=f"img/{i+1}.png", width=112, height=112),
                        on_click=lambda e: print(f"Imagen {i+1}"),
                    ),
                    ft.Container(
                        content=ft.Image(src=f"img/{i+2}.png", width=112, height=112),
                        on_click=lambda e: print(f"Imagen {i+2}"),
                    ),
                ],
                alignment=ft.MainAxisAlignment.CENTER,
            )
            rows.append(row)

        main = ft.Container(
            content=ft.Column(
                [
                    ft.Container(height=40),
                    *rows,
                    ft.Text("6/9", style=ft.TextStyle(color="black", size=48)),
                ],
                horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            ),
            height=self.page.window_height,
            width=self.page.window_width,
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit, 
            expand=True,
            alignment=ft.alignment.center,
        )
        return main

if __name__ == "__main__":
    def main(page: ft.Page):
        archivements = Archivements(page)
        page.add(archivements)

    ft.app(target=main, assets_dir="../assets/")
