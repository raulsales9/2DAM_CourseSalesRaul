import flet as ft
from flet import *

class Conctactlist(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        self.users_json = [
            {"nombre": "Pepe Sandía", "ruta_imagen": "img/usuario1.png", "mensaje": "Devuelveme mis EcoCoins!!!", "hora": "16:46"},
            {"nombre": "Juan", "ruta_imagen": "img/usuario2.png", "mensaje": "Quieres quedar este finde?", "hora": "12:00"},
            {"nombre": "Carles", "ruta_imagen": "img/usuario3.png", "mensaje": "Como estás?", "hora": "15:23"},
            {"nombre": "Eco group", "ruta_imagen": "img/usuario4.png", "mensaje": "Recordad que el lunes tenemos la quedada", "hora": "09:00"},
        ]

    def build(self):
        user_containers = []

        for user in self.users_json:
            image_container = ft.Container(content=ft.Image(src=user["ruta_imagen"],width=110,height=110,),width=110,height=110,on_click=lambda e: self.page.go("/othprofile"),)
            name_text = ft.Text(user["nombre"], style=ft.TextStyle(color="black", size=20))
            message_text = ft.Text(user["mensaje"], style=ft.TextStyle(color="black", size=13))
            time_text = ft.Text(user["hora"], style=ft.TextStyle(color="black", size=13))
            user_info_container = ft.Column([name_text, message_text, time_text])

            message_button = ft.ElevatedButton("Go to Message", on_click=lambda e: self.page.go("/message"))
            main_container = ft.Container(
                content=ft.Column([ft.Row([image_container, user_info_container]), message_button]),
                width=341,
                height=150,
                padding=0,
                border_radius=ft.border_radius.all(10),
                bgcolor="#C0C0C0",
                margin=10,
            )
            user_containers.append(main_container)

        content_container = ft.Container(
            content=ft.Column(user_containers, scroll="always", width=400, height=630),
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit, 
            expand=True,
            alignment=ft.alignment.center,
        )
        return ft.ResponsiveRow([content_container])

if __name__ == '__main__':
    def main(page: ft.Page):
        contact = Conctactlist(page)
        page.add(contact)
    ft.app(target=main, assets_dir="../assets/")
