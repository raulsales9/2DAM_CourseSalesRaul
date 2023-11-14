import flet as ft

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = 'white'

class Idea:
    def __init__(self, container1_content, container2_content, container3_content) -> None:
        self.container1_content = container1_content
        self.container2_content = container2_content
        self.container3_content = container3_content
        
    def main(self,page: ft.Page):
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
            content=self.container1_content,
            padding=5,
            height=615,
            bgcolor=ft.colors.GREY_300,
            margin=5,
            col={"sm": 12, "md": 10, "xl": 4},
        )
        container2 = ft.Container(
            content=self.container2_content,
            padding=5,
            col={"sm": 12, "md": 10, "xl": 4},
        )

        container3 = ft.Container(
            content=self.container3_content,
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
    container1_content = ft.Column([
        ft.Text("Perfil", style={"color": "black"}),
        ft.Text("Hola, ¿cómo estás?", style={"color": "black"}),
        ft.Text("Número 1", style={"color": "black"}),
        ft.Text("Número 2", style={"color": "black"}),
    ])

    container2_content = ft.Column([
        ft.Container(
            content=ft.Column([
                ft.Text("Comentarios", style={"color": "black"}),
                ft.Text("Escribe aquí tu comentario", style={"color": "black"}),
                ft.Text("Enviar", style={"color": "black"}),
            ]), 
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
            bgcolor=ft.colors.GREY_300,
            height=430,
            width=600,
        ),
    ])

    container3_content = ft.Column([
        ft.Text("Eventos", style={"color": "black"}),
        ft.Image("./assets/ideaProject/assets/pexels-josh-sorenson-976866.jpg", width=150, height=100),
        ft.Text("", style={"color": "black"}),
    ])
    idea = Idea(container1_content, container2_content, container3_content)
    ft.app(target=idea.main, view='web_browser')
