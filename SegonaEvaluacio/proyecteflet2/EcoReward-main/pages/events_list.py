import flet as ft
from flet import *


class Events_list(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.products = [
            {
                "title": "Feria de Reciclaje",
                "description": "Un evento comunitario para promover la conciencia sobre el reciclaje y la sostenibilidad. Habrá talleres, charlas y actividades para todas las edades.",
                "ubicacion": "Parque Central",
                "hora": "10:00-15:00",
            },
            {
                "title": "Taller de Compostaje",
                "description": "Aprende a hacer tu propio compost y a reducir los residuos de tu hogar en este taller práctico.",
                "ubicacion": "Centro Comunitario",
                "hora": "09:00-11:00",
            },
            {
                "title": "Caminata por la Naturaleza",
                "description": "Disfruta de un paseo guiado por el bosque local y aprende sobre la flora y fauna autóctona.",
                "ubicacion": "Bosque Local",
                "hora": "10:00-13:00",
            },
            {
                "title": "Mercado de Agricultores",
                "description": "Compra productos locales y orgánicos directamente de los agricultores de tu comunidad.",
                "ubicacion": "Plaza del Pueblo",
                "hora": "08:00-14:00",
            },
            {
                "title": "Día de Limpieza de la Playa",
                "description": "Ayuda a mantener nuestras playas limpias y seguras para la vida marina participando en este evento de limpieza.",
                "ubicacion": "Playa Principal",
                "hora": "10:00-12:00",
            },
            {
                "title": "Festival de la Bicicleta",
                "description": "Celebra el transporte sostenible en este festival lleno de actividades relacionadas con la bicicleta.",
                "ubicacion": "Parque de la Ciudad",
                "hora": "11:00-16:00",
            },
            {
                "title": "Charla sobre Energías Renovables",
                "description": "Aprende sobre las últimas innovaciones en energías renovables en esta charla informativa.",
                "ubicacion": "Biblioteca Municipal",
                "hora": "18:00-20:00",
            },
            {
                "title": "Jornada de Reforestación",
                "description": "Participa en la plantación de árboles para ayudar a reforestar áreas locales.",
                "ubicacion": "Monte Verde",
                "hora": "09:00-13:00",
            },
            {
                "title": "Exposición de Arte Reciclado",
                "description": "Descubre cómo los artistas locales transforman los materiales reciclados en obras de arte en esta exposición única.",
                "ubicacion": "Galería de Arte Local",
                "hora": "16:00-19:00",
            },
        ]
        

    def build(self):
        rows = []
        self.title_one= ft.Container(ft.Text("Events :", size=30,weight='bold'), alignment=ft.alignment.center)
        rows = []
        for product in self.products:
            image_container = ft.Container(
                width=20, height=200, bgcolor="#2A7516", border_radius=10
            )

            title_row = ft.Row(
                [ft.Container(ft.Text(product["title"], size=20), width=200, height=30)]
            )
            description_row = ft.Row(
                [ft.Container(ft.Text(product["description"], size=15), width=300, height=60)]
            )
            location_row = ft.Row(
                [ft.Image(src=f"img/img_ubi.png",width=20,  height=20), ft.Container(ft.Text(product["ubicacion"], size=18), width=150, height=30)]
            )
            time_row = ft.Row(
                [ft.Image(src=f"img/img_time.png",width=20,  height=20), ft.Container(ft.Text(product["hora"], size=18), width=150, height=30)]
            )
            info_container = ft.Container(
                ft.Column([title_row, description_row, location_row, time_row]),
                width=350,
                height=180,
            )
            main_container = ft.Container(
                ft.Row([image_container,info_container]),
                width=400,
                height=180,
                margin=10,
                bgcolor="#C0C0C0",
            )
            rows.append(main_container)
        self.content = ft.Container(
            ft.Column([self.title_one] + rows, scroll="always", width=400, height=680),
            width=400,
            height=630,
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit, 
            expand=True,
            alignment=ft.alignment.center,
        )
        return ft.ResponsiveRow([self.content])
if __name__ == "__main__":

    def main(page: ft.Page):
        registry = Events_list(page)
        page.add(registry)

    ft.app(target=main, assets_dir="../assets/")