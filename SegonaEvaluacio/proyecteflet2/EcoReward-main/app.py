import flet as ft
from pages.routeroutlet import views_router

def main(page: ft.Page):
    def route_change(route):
        page.views.clear()
        page.views.append(
            views_router(page)[page.route]
        )
        
    page.on_route_change = route_change
    page.go("/")

ft.app(target=main, assets_dir="assets/")
