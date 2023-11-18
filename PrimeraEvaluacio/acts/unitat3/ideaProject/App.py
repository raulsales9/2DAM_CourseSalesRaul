import flet as ft
import os.path
from pages.login import Login
#from views.gente import Gente
from pages.register import Register
#from views.timeline import Timeline
from pages.kurigram import Kurigram
from pages.routeroutlet import views_router
import json
import os
import json


def main(page: ft.Page):
    def route_change(route):
        page.views.clear()
        page.views.append(
            views_router(page)[page.route]
        )
        
    page.on_route_change = route_change
    page.go("/login")

ft.app(target=main)



