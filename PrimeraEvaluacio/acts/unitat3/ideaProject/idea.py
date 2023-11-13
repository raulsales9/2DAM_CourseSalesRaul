import flet as ft

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = 'white'

def main(page: ft.Page):
    page.bgcolor = COLOR_SECONDARY

    page.appbar = ft.AppBar(
        leading=ft.Image('./assets/logo_kurigram_color_slogan.png', width=50),
        leading_width=50,
        bgcolor=COLOR_PRIMARY,
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
        content=ft.Column([
            ft.Text("Perfil", style={"color": "black"}),
            ft.Text("Hola, ¿cómo estás?", style={"color": "black"}),
            ft.Text("Número 1", style={"color": "black"}),
            ft.Text("Número 2", style={"color": "black"}),
        ]),
        padding=5,
        height=615,
        bgcolor=ft.colors.GREY_300,
        margin=5,
        col={"sm": 12, "md": 10, "xl": 4},
    )
    container2 = ft.Container(
        content=ft.Column([
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
        ]),
        padding=5,
        col={"sm": 12, "md": 10, "xl": 4},
    )

    container3 = ft.Container(
        content=ft.Column([
            ft.Text("Eventos", style={"color": "black"}),
            ft.Text("Próximos eventos", style={"color": "black"}),
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

if __name__ == "__main__":
    ft.app(target=main)
