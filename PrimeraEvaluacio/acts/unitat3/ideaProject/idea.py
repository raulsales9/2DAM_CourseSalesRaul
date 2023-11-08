import flet as ft

class App:
    def __init__(self):
        self.page = ft.Page()
        self.page.appbar = ft.AppBar(
            title="Mi aplicación",
            leading=ft.Icon(ft.icons.MENU),
            actions=[
                ft.Icon(ft.icons.SEARCH),
                ft.Icon(ft.icons.SETTINGS),
            ],
        )
        self.page.run()

if __name__ == "__main__":
    app = App()
