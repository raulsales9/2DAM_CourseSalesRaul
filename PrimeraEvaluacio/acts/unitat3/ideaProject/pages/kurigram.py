import flet as ft
from pages.content import leer_datos, escribir_datos, leer_eventos
from assets.colors import *


class Kurigram(ft.UserControl):
    def __init__(self, page):
        super().__init__()
        self.page = page

    def build(self):
        titulo_field = ft.TextField(label="Título", expand=2)
        data = leer_datos()
        event = leer_eventos()
        self.usuari = data.get('nombre')
        self.seguidores = data.get('seguidores')
        self.seguidos = data.get('seguidos')
        all_posts = []
        for usuario in data:
            all_posts.extend(data[usuario].get('posts', []))
        events = event.get('events', [])
        #per a agafar els post : posts = data.get('posts', [])

        post_controls = []
        for post in all_posts:
            post_controls.append(ft.Text(f"Titulo: {post['titulo']}", style={'font-size': '18px', 'font-weight': 'bold'}))
            post_controls.append(ft.Text(f"Contenido: {post['contenido']}", style={'font-size': '16px'}))
            post_controls.append(ft.Text(f"Fecha: {post['fecha']}", style={'font-size': '14px', 'color': COLOR_PRIMARY}))
            post_controls.append(ft.Divider())

        events_controls = []
        for event in events:
            events_controls.append(ft.Text(f"Event: {event['date']}", style={'font-size': '18px', 'font-weight': 'bold'}))
            events_controls.append(ft.Text(f"Event: {event['description']}", style={'font-size': '18px', 'font-weight': 'bold'}))
            #events_controls.append(ft.Image(src="{event['image']}"))
            events_controls.append(ft.Divider())

        
        titulo_field = ft.TextField(label="Título", expand=2)

        def on_submit(event, titulo_field=titulo_field):
            titulo = titulo_field.value
            print(f"Valor recogido del campo de título: {titulo}") 
            data = leer_datos()
            if not titulo: 
                print("El título no puede estar vacío.")
                print(f"Valor del campo de título: {titulo_field.value}") 
                return
            contenido = "" 
            fecha = ""  

            new_post = {
                "idPosts" : len(data[self.usuari]['posts']) + 1, 
                "idUser" : data[self.usuari]['id'],  
                "titulo": titulo, 
                "contenido": contenido, 
                "fecha" : fecha
            }
            data[self.usuari]['posts'].append(new_post)
            escribir_datos(data)

        container1 = ft.Container(
            ft.Column( controls=[
                ft.Container(
                    ft.Column([
                        ft.Text("Comprova les teues estadistiques: "),
                        ft.Text(f"Hola, {self.usuari}", style={"color": "black"}),
                        ft.Text(f"Tienes estos seguidores: {self.seguidores}", style={"color": "black"}),
                        ft.Text(f"Estás siguiendo a: {self.seguidos}", style={"color": "black"}),
                    ]),
                    padding=5,
                    height=500,
                    bgcolor=ft.colors.WHITE,
                    margin=10,
                    col={"sm": 12, "md": 10, "xl": 4}, 
                )     
            ]),
            padding=5,
            height=615,
            bgcolor=ft.colors.GREY_300,
            margin=5,
            col={"sm": 12, "md": 10, "xl": 3},
        )

        container2 = ft.Container(
            ft.Column(controls=[
                ft.Container(
                    content=ft.Column(controls=[
                        ft.Text("Crea un post ahora!", style={"color": "black"}),
                        ft.Divider(),
                        ft.Container( 
                            ft.Row([
                                titulo_field,
                                ft.FilledButton("Enviar", on_click=on_submit(event))
                            ]),
                            margin=15,
                        ),
                    ]), 
                    margin=5,
                    bgcolor=ft.colors.GREY_300,
                    height=150,
                    width=1000,
                ),
                ft.Divider(),
                ft.Container(
                    content=ft.Column(post_controls),
                    margin=5,
                    bgcolor=ft.colors.GREY_300,
                    height=415,
                    width=1000,
                ),
            ]),
            padding=5,
            col={"sm": 12, "md": 10, "xl": 6},
        )

        container3 = ft.Container(
            ft.Column(controls=[
                ft.Container(
                    content=ft.Column(events_controls),
                    padding=5,
                    height=500,
                    bgcolor=ft.colors.WHITE,
                    margin=10,
                    col={"sm": 12, "md": 12, "xl": 12}, 
                )     
            ]),
            padding=5,
            margin=5,
            height=615,
            bgcolor=ft.colors.GREY_300,
            col={"sm": 12, "md": 10, "xl": 3},
        )


        return ft.ResponsiveRow(
                [
                    container1,
                    container2,
                    container3,
                ],
            )

if __name__ == "__main__":
    def main(page: ft.Page):
        kurigram = Kurigram(page)
        page.add(kurigram)
    ft.app(target=main)