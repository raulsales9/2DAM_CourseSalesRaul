import flet as ft

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = '#FFF'
COLOR_BACKGROUND = '#cac9c9'
COLOR_BLUE = '#1190CB'
COLOR_SEMIDARKBLUE = '#0142f3bd'

class Gente(ft.UserControl):
    def __init__(self, page):
        super().__init__()
        self.page = page

    def build(self):
        appbar = ft.AppBar(
            leading=ft.Image('../../images/logos/logo_corporativo_greyscale.png', width=71, height=53),
            leading_width=50,
            bgcolor=COLOR_PRIMARY,
            actions=[
                ft.Column([
                    ft.IconButton(ft.icons.HOME),
                    ft.Text('Inicio', color=COLOR_SECONDARY),
                ], ),
                ft.Column([
                    ft.IconButton(ft.icons.PERSON),
                    ft.Text('Acerca de', color=COLOR_SECONDARY),
                ],),
                ft.Column([
                    ft.IconButton(ft.icons.MENU),
                    ft.Text('Contacto', color=COLOR_SECONDARY),
                ], ),
            ]
        )

        container1 = ft.Container(
            ft.Column([
                ft.Container(
                    ft.Column([
                        ft.Text("Conprova les teues estadistiques: ", style={'font-size': '20px', 'font-weight': 'bold'}),
                        ft.Row([
                            ft.Text("Nombre del usuario", style={'font-size': '16px'}),
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


        return ft.Container(appbar, container1)
    

if __name__ == "__main__":
    def main(page: ft.Page):
        gente = Gente(page)
        page.add(gente)
    ft.app(target=main)

