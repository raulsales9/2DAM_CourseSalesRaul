import flet as ft
from flet import *
from pages.login import Login
from pages.initialpage import InitialPage
from pages.otherprofiles import OtherProfile
from pages.achievements import Archivements
from pages.profile import Profile
from pages.qr_scanner import QrScanner
from pages.recycling import Recycling
from pages.registry import Registry
from pages.settings import Settings
from pages.terms_a_conditions import TermsAndConditions
from pages.politicofprivacity import PoliticOfPrivacity
from pages.trees import Trees
from pages.greenshop import Greenshop
from pages.history import History
from pages.transports import Transports
from pages.events_list import Events_list
from pages.event import Event
from pages.contacts import Conctactlist
from pages.missatges import Message
from pages.main import MainPage
import os.path


def views_router(page):
    def on_person_click(*args):
        page.go('/')

    appbar = ft.AppBar(
        title=ft.Text(
            "Ecoreward by F&R",
            style=ft.TextStyle(
                color="white",
                size=36,
            ),
        ),
        bgcolor="#63d138",
        automatically_imply_leading=False,
        toolbar_height=100,
        center_title=True
    )

    directorio_actual = os.path.dirname(__file__)

    ruta_image_home = os.path.join(directorio_actual, 'imagenes_main', 'home.png')

    ruta_image_messages = os.path.join(directorio_actual, 'imagenes_main', 'messages.png')

    def cambio(evento):
        if evento.control.selected_index == 0:
            page.go('/main')

        elif evento.control.selected_index == 1:
            page.go('/contactlist')

    navigation_bar = ft.NavigationBar(
            destinations=[
                ft.NavigationDestination(icon_content=Image(
                    src=ruta_image_home,
                    width=76,
                    height=89,
                )),
                ft.NavigationDestination(icon_content=Image(
                    src=ruta_image_messages,
                    width=76,
                    height=89,
                )),
            ],
            bgcolor="#2A7516",
            height=120,
            indicator_color="transparent",
            on_change=cambio
        )


    return{
        '/': ft.View(
            route='/',
            controls=[InitialPage(page)]
        ),
        '/main': ft.View(
            route='/main',
            controls=[appbar, MainPage(page), navigation_bar]
        ),
        '/register': ft.View(
            route='/register',
            controls=[Registry(page)]
        ),
        '/login': ft.View(
            route='/login',
            controls=[Login(page)]
        ),
        '/greenshop': ft.View(
            route='/greenshop',
            controls=[appbar, Greenshop(page), navigation_bar]
        ),
        '/history': ft.View(
            route='/history',
            controls=[appbar, History(page), navigation_bar]
        ),
        '/politicofprivacity': ft.View(
            route='/politicofprivacity',
            controls=[appbar, PoliticOfPrivacity(page), navigation_bar]
        ),
        '/termsandconditions': ft.View(
            route='/termsandconditions',
            controls=[appbar,TermsAndConditions(page), navigation_bar]
        ),
        '/history' : ft.View(
            route='/history',
            controls=[appbar, History(page), navigation_bar]
        ),
        '/achievements' : ft.View(
            route='/archivements',
            controls=[appbar, Archivements(page), navigation_bar]
        ),
        '/othprofile' : ft.View(
            route='/othprofile',
            controls=[appbar,OtherProfile(page), navigation_bar]
        ),
        '/profile' : ft.View(
            route='/profile',
            controls=[appbar,Profile(page), navigation_bar]
        ),
        '/scanner' : ft.View(
            route='/scanner',
            controls=[appbar,QrScanner(page), navigation_bar]
        ),
        '/trees' : ft.View(
            route='/trees',
            controls=[appbar,Trees(page), navigation_bar]
        ),
        '/settings' : ft.View(
            route='/settings',
            controls=[appbar,Settings(page), navigation_bar]
        ),
        '/recycling' : ft.View(
            route='/recycling',
            controls=[appbar,Recycling(page), navigation_bar]
        ),
        '/transports' : ft.View(
            route='/transports',
            controls=[appbar,Transports(page), navigation_bar]
        ),
        '/eventslist' : ft.View(
            route='/eventslist',
            controls=[appbar,Events_list(page), navigation_bar]
        ),
        '/message' : ft.View(
            route='/message',
            controls=[appbar,Message(page), navigation_bar]
        ),
        '/contactlist' : ft.View(
            route='/contactlist',
            controls=[appbar,Conctactlist(page), navigation_bar]
        ),
        '/event' : ft.View(
            route='/event',
            controls=[appbar,Event(page), navigation_bar]
        ),
    }