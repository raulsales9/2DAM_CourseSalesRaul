import flet as ft
from flet import *
import flet as ft
from flet import *

class Event(ft.UserControl):
    def __init__(self, page):
        super().__init__()
        self.page = page
        
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Events",
                style=ft.TextStyle(
                    color="white",
                    size=36,
                ),
            ),
            bgcolor="#63d138",
            automatically_imply_leading=False,
            toolbar_height=100,
            center_title=True,
        )
        self.page.add(self.appbar)

    def build(self):
        self.page.title = "Views"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 0
        self.page.window_width = 430
        self.page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.page.vertical_alignment = MainAxisAlignment.CENTER
        self.page.horizontal_alignment = CrossAxisAlignment.CENTER
        self.page.update()

        content_appbar = Container(
            content=ft.Column([
                ft.Text("Event", size=48, color="#FFFFFF"),
            ], alignment=ft.MainAxisAlignment.CENTER),
            alignment=ft.alignment.center
        )

        app_bar = AppBar(
            title=content_appbar,
            bgcolor="#63d138",
            automatically_imply_leading=False,
            toolbar_height=150
        )
        self.page.appbar = app_bar

        resumen_evento = """
        ¡Te invitamos a un evento único en el Bosque Esmeralda, a las afueras de nuestra querida comunidad! En nuestro "Plantatón Comunitario", nos sumergiremos en la belleza natural de este bosque y nos uniremos para plantar árboles, creando juntos un pulmón verde para las generaciones futuras.

        Sumérgete en una experiencia donde la conexión con la naturaleza se combina con la acción concreta contra el cambio climático. Exploraremos los rincones del Bosque Esmeralda mientras aprendemos sobre la importancia vital de cada árbol en nuestro ecosistema.
        """

        main_container = Container(
            content=ft.Column([
                ft.Text("Plantatón Comunitario", size=36, text_align=ft.TextAlign.CENTER),
                Image(src="imagenes_event/localizacion.png", width=385, height=181),
                ft.Text(resumen_evento, size=13),
                ft.Row([
                    Image(src="img/hora.png", width=40, height=40),
                    ft.Text("12:00 - 13:00", size=16),
                ],alignment=ft.MainAxisAlignment.CENTER)
            ], horizontal_alignment=CrossAxisAlignment.CENTER),

            height=self.page.window_height,
            width=self.page.window_width,
        )

        self.page.add(main_container)

if __name__ == '__main__':
    def main(page: Page):
        event = Event(page)
        event.build()
    ft.app(target=main)
