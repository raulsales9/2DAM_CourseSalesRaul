import flet as ft

class MainPage(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.theme_mode = ft.ThemeMode.LIGHT
        self.page.padding = 50
        """ self.page.window_width = 430
        self.page.window_height = 932 """
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = ft.MainAxisAlignment.CENTER
        self.page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
        self.page.update()

    def build(self):
        settings = ft.Container(
            content=ft.Image(
                src="img/configuracion.png",
                width=55,
                height=51,
                tooltip="Configuracion",
                fit=ft.ImageFit.CONTAIN,
            ),
            padding=5,
            alignment=ft.alignment.top_right,
            on_click=lambda e: self.page.go("/settings"),
        )

        user = ft.Container(
            content=ft.Image(
                src=f"img/person.png",
                width=225,
                height=225,
            ),
            alignment=ft.alignment.top_center,
            on_click=lambda e: self.page.go("/profile"),
        )

        money = ft.Container(
            content=ft.Row([
                ft.Text("160", size=40, color="#2A7516"),
                ft.Image(
                    src=f"img/coin.png",
                    width=57,
                    height=50,
                )
            ],
                alignment=ft.MainAxisAlignment.CENTER
            ),
            padding=15,
            alignment=ft.alignment.center,
            on_click=lambda e: self.page.go("/history"),
        )

        options = ft.Column([
            ft.Row([
                ft.Container(
                    content=ft.Image(
                        src="img/icon_calendario.png",
                        width=76,
                        height=89,
                    ),
                    on_click=lambda e: self.page.go("/eventslist"),
                    alignment=ft.alignment.center
                ),
                ft.Container(
                    content=ft.Image(
                        src="img/bici_icon.png",
                        width=76,
                        height=89,
                    ),
                    on_click=lambda e: self.page.go("/transports"),
                    alignment=ft.alignment.center
                ),
                ft.Container(
                    content=ft.Image(
                        src="img/reciclaje_icon.png",
                        width=76,
                        height=89,
                    ),
                    on_click=lambda e: self.page.go("/recycling"),
                    alignment=ft.alignment.center
                ),
            ],
                alignment=ft.MainAxisAlignment.CENTER
            ),
            ft.Row([
                ft.Container(
                    content=ft.Image(
                        src="img/barras_icon.png",
                        width=76,
                        height=89,
                    ),
                    on_click=lambda e: self.page.go("/scanner"),
                    alignment=ft.alignment.center
                ),
                ft.Container(
                    content=ft.Image(
                        src="img/compra_icono.png",
                        width=76,
                        height=89,
                    ),
                    on_click=lambda e: self.page.go("/greenshop"),
                    alignment=ft.alignment.center
                ),
                ft.Container(
                    content=ft.Image(
                        src="img/arbol_icon.png",
                        width=76,
                        height=89,
                    ),
                    on_click=lambda e: self.page.go("/trees"),
                    alignment=ft.alignment.center
                ),
            ],
                alignment=ft.MainAxisAlignment.CENTER
            ),
        ], alignment=ft.alignment.center)

        main = ft.Container(
            content=ft.Column([
                settings, user, money, options
            ],
                horizontal_alignment=ft.CrossAxisAlignment.CENTER
            ),#image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit, 
            expand=True,
            alignment=ft.alignment.center,
        )

        return ft.ResponsiveRow([main])
    
        """ content= ft.Container( 
            ft.ResponsiveRow([
                main
            ]),image_src=self.bgimage.src, image_fit=self.bgimage.fit, expand=True,alignment=ft.alignment.center
        )
        return content """

if __name__ == '__main__':
    def main(page: ft.Page):
        page.title = "Views"
        main_page = MainPage(page)
        page.add(main_page.build())
    ft.app(target=main, assets_dir="../assets/")
