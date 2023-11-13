import flet as ft

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = '#FFF'
COLOR_BACKGROUND = '#cac9c9'
COLOR_BLUE = '#1190CB'
COLOR_SEMIDARKBLUE = '#0142f3bd'

class App:
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
            self.sidebar(),
            self.posts(),
            self.event(),
        ]))

    def sidebar(self):
        sidebar_container = ft.Container(
            bgcolor=COLOR_BACKGROUND, 
            col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
        )
        return sidebar_container

    def posts(self):
        post_containers = []

        for post in self.get_post_data():
            post_container = ft.Container(
                ft.Text(post),
                bgcolor=COLOR_BACKGROUND,
                padding=16,
                margin={"bottom": 16},
            )
            post_containers.append(post_container)
        return post_containers

    def event(self):
        event_container = ft.Container(
            ft.Text('Eventos'),
            ft.Image('', alt='Image_event_example'),
            ft.Text('Hola'),
            bgcolor=COLOR_BACKGROUND,
            padding=16,
            margin={"bottom": 16},
        )
        return event_container

    def get_post_data(self):
        # Aquí puedes reemplazar con los datos de tus publicaciones
        return ['Post 1', 'Post 2', 'Post 3']

if __name__ == "__main__":
    app = App()

