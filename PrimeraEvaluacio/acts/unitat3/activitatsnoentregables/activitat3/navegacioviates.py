'''Exemple de navegació entre vistes de Flet'''
import flet as ft

def main(page: ft.Page):
    '''Funció principal'''
    page.title = "Routes Example"

    def route_change(route):
        page.views.clear()
        page.views.append(
            ft.View(
                "/",
                [
                    ft.AppBar(title=ft.Text("Flet app"), bgcolor=ft.colors.SURFACE_VARIANT),
                    ft.ElevatedButton("Visit Store", on_click=lambda _: page.go("/store")),
                ],
            )
        )
        if page.route == "/store":
            page.views.append(
                ft.View(
                    "/store",
                    [
                        ft.AppBar(title=ft.Text("cart"), bgcolor=ft.colors.SURFACE_VARIANT),
                        ft.ElevatedButton("cart", on_click=lambda _: page.go("/cart")),
                        ft.ElevatedButton("go home", on_click=lambda _: page.go("/")),
                    ]
                )
            )
        if page.route == "/cart":
            page.views.append(
                ft.View(
                    "/cart",
                    [
                        ft.AppBar(title=ft.Text("visit store"), bgcolor=ft.colors.SURFACE_VARIANT),
                        ft.ElevatedButton("visit store", on_click=lambda _: page.go("/store")),
                        ft.ElevatedButton("go home", on_click=lambda _: page.go("/")),
                    ]
                )
            )
        page.update()

    def view_pop(view):
        page.views.pop()
        top_view = page.views[-1]
        page.go(top_view.route)

    page.on_route_change = route_change
    page.on_view_pop = view_pop
    page.go(page.route)


ft.app(target=main, view='web_browser', port=8080)