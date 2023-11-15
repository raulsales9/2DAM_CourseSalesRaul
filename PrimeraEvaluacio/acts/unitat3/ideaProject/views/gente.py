import flet as ft

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = '#FFF'
COLOR_BACKGROUND = '#cac9c9'
COLOR_BLUE = '#1190CB'
COLOR_SEMIDARKBLUE = '#0142f3bd'

class Gente:
    def __init__(self, page):
        pass
    def main(self, page: ft.Page):
        page.bgcolor = COLOR_BACKGROUND

        page.appbar = ft.AppBar(
            leading=ft.Image('../../images/logos/logo_corporativo_greyscale.png', width=71, height=53),
            leading_width=50,
            bgcolor=COLOR_PRIMARY,
            actions=[
                ft.Column([
                    ft.IconButton(ft.icons.HOME, color=COLOR_SECONDARY),
                    ft.Text('Inicio', color=COLOR_SECONDARY),
                ], ),
                ft.Column([
                    ft.IconButton(ft.icons.PERSON, color=COLOR_SECONDARY),
                    ft.Text('Acerca de', color=COLOR_SECONDARY),
                ],),
                ft.Column([
                    ft.IconButton(ft.icons.MENU, color=COLOR_SECONDARY),
                    ft.Text('Contacto', color=COLOR_SECONDARY),
                ], ),
            ]
        )

        page.add(ft.ResponsiveRow([
            
        ]))

    

if __name__ == "__main__":
    def main():
        pass
    idea=Gente(ft.Page)
    ft.app(target=idea.main, view='web_browser')

