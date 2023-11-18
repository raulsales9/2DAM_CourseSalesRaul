import flet as ft
from pages.login import Login
from pages.register import Register
from pages.gente import Gente
from pages.timeline import Timeline
from pages.kurigram import Kurigram
from assets.colors import *

# Control de rutes,  actua com a call center, i treballa les rutes amb els controls
def views_router(page):
    def on_person_click(*args):
        page.go('/gente')

    def on_menu_click(*args):
        page.go('/timeline')

    def on_kurigram_click(*args):
        page.go('/kurigram')

    appbar = ft.AppBar(
        leading=ft.Image('../../images/logos/logo_corporativo_greyscale.png', width=71, height=53),
        leading_width=50,
        bgcolor=COLOR_PRIMARY,
        actions=[
            ft.Column([
                ft.IconButton(ft.icons.HOME, on_click=on_kurigram_click),
                ft.Text('Inicio', color=COLOR_SECONDARY),
            ], ),
            ft.Column([
                ft.IconButton(ft.icons.PERSON, on_click=on_person_click),
                ft.Text('Acerca de', color=COLOR_SECONDARY),
            ],),
            ft.Column([
                ft.IconButton(ft.icons.MENU, on_click=on_menu_click),
                ft.Text('Contacto', color=COLOR_SECONDARY),
            ], ),
        ]
    )

    return{
        '/kurigram': ft.View(
            route='/kurigram',
            controls=[appbar, Kurigram(page)]
        ),
        '/register': ft.View(
            route='/register',
            controls=[Register(page)]
        ),
        '/gente': ft.View(
            route='/gente',
            controls=[appbar, Gente(page)]
        ),
        '/timeline': ft.View(
            route='/timeline',
            controls=[appbar, Timeline(page)]
        ),
        '/login': ft.View(
            route='/login',
            controls=[Login(page)]
        ),
    }
