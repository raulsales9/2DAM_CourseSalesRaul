import flet as ft
import os.path
from views.login import Login

#from views.gente import Gente
from views.register import Register
from views.timeline import Timeline

#from views.kurigram import Kurigram
import json

import os
import json


def main(page: ft.Page):
    page.title = "Routes Example"
    login = Login(page)
    register = Register(page)
    def route_change(route_test):
        page.views.clear()
        page.views.append(login)
        page.go("/login")
        if route_test.route == "/register":
            page.views.clear()
            page.views.append(
                ft.View(
                    "/register"
                    [
                    register
                ])
            )
            page.go("/register")
        
    def view_pop(view):
        page.views.pop()
        top_view = page.views[-1]
        page.go(top_view.route)
        
    page.on_route_change = route_change
    page.on_view_pop = view_pop
    page.go(page.route)

ft.app(target=main)



