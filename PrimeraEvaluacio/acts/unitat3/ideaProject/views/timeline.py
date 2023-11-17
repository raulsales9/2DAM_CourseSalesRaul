import json
import flet as ft
import os.path

COLOR_PRIMARY = '#0E2439'
COLOR_SECONDARY = '#FFF'
COLOR_BACKGROUND = '#cac9c9'
COLOR_BLUE = '#1190CB'
COLOR_SEMIDARKBLUE = '#0142f3bd'

class User:
    def __init__(self, user_data):
        self._data = user_data

    @property
    def username(self):
        return self._data.get('username')

    @property
    def followers(self):
        return self._data.get('followers')

    @property
    def following(self):
        return self._data.get('following')

class Timeline:
    def __init__(self, users):
        self.users = users

    def build(self, page):
        self.page = page
        self.page.bgcolor = COLOR_BACKGROUND

        self.page.appbar = ft.AppBar(
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
                        *[ft.Container(
                            ft.Column([
                                ft.Text(f"Nombre del usuario: {user.username}", style={'font-size': '16px'}),
                                ft.Text(f"Seguidores: {user.followers}", style={'font-size': '16px'}),
                                ft.Text(f"Seguidos: {user.following}", style={'font-size': '16px'}),
                                ft.Image(src=f"../assets/pexels-josh-sorenson-976866.png", width=50, height=50),
                                ft.ElevatedButton("Seguir", color="primary")
                            ]),
                            padding=5,
                            width=1800,
                            height=600,
                            bgcolor=ft.colors.WHITE,
                            margin=10,
                            col={"sm": 12, "md": 12, "xl": 12},
                        ) for user in self.users]
                    ]),
                    padding=5,
                    width=1800,
                ),
            ]),
            padding=5,
            width=1800,
            height=600,
            bgcolor=ft.colors.WHITE,
            margin=10,
            col={"sm": 12, "md": 12, "xl": 12},
        )
        self.page.add(ft.ResponsiveRow([
            container1
        ]))


        with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'r') as f:
            user_data_list = json.load(f)
        
        users = [User(user_data) for user_data in user_data_list]
        
        timeline = Timeline(users)
         # Crear una instancia de Page
        timeline.build(page)

if __name__ == "__main__":
    # Crear una instancia de Timeline con los usuarios
    app = Timeline(users)
    
    # Pasar la función build de la instancia de Timeline a ft.app
    ft.app(target=app.build)
