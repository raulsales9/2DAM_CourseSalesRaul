import flet as ft

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = 'white'


class Main:
    def __init__(self, usuari, events, publicacions):
        self.usuari = usuari
        self.events = events
        self.publicacions = publicacions

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
                        ft.Text("Conprova les teues estadistiques: "),
                        ft.Text(f"hola,{self.usuari['user']}", style={"color": "black"}),
                        ft.Image({self.usuari['avatar']}, width=30, height=30),
                        ft.Text(f"Tens aquestos seguidors: {self.usuari['seguidors']}", style={"color": "black"}),
                        ft.Text(f"Tens aquestos seguidors: {self.usuari['seguits']}", style={"color": "black"}),
                    ]),
                    padding=5,
                    height=500,
                    bgcolor=ft.colors.WHITE,
                    margin=10,
                    col={"sm": 12, "md": 10, "xl": 4}, 
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
                        ft.Text("Más comentarios", style={"color": "black"}),
                        ft.TextField(label="Contraseña ", expand=1),
                        ft.FilledButton("Enviar")
                    ]), 
                    margin=5,
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
                    margin=5,
                    bgcolor=ft.colors.GREY_300,
                    height=415,
                    width=600,
                ),
            ]),
            padding=5,
            col={"sm": 12, "md": 10, "xl": 4},
        )

        container3 = ft.Container(
            ft.Column([
                ft.Container(
                    ft.Column([
                        ft.Text("Conprova els events : "),
                        ft.Text(f"hola,{self.usuari['user']}", style={"color": "black"}),
                        ft.Image({self.usuari['avatar']}, width=30, height=30),
                        ft.Text(f"Tens aquestos seguidors: {self.usuari['seguidors']}", style={"color": "black"}),
                        ft.Text(f"Tens aquestos seguidors: {self.usuari['seguits']}", style={"color": "black"}),
                    ]),
                    padding=5,
                    height=500,
                    bgcolor=ft.colors.WHITE,
                    margin=10,
                    col={"sm": 12, "md": 12, "xl": 12}, 
                )     
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

if __name__ == "__main__":
    usuari = {
        "user" : "raul",
        "avatar" : "",
        "seguidors" : 13,
        "seguits" : 1
    }
    events = {
        "user" : "raul",
        "Data" : "2024/07/23",
        "Image" : "",
        "descripcio" : "Futbol en Cullera"
    }
    publicacions = {
        "user" : "raul",
        "Data" : "2024/07/23",
        "descripcio" : "lorem ipsum dolor"
    }

    main = Main(usuari, events, publicacions)
    ft.app(target=main.build, view='web_browser')
