import flet as ft
from passlib.hash import pbkdf2_sha256
import json
import os.path
import os
import sys

class Login(ft.UserControl):
    def __init__(self):
        #super().__init__()
        #self.page = page
        self.txt_nombre = ft.TextField(label="Nombre", border="underline")
        self.txt_correo = ft.TextField(label="Correo", border="underline")
        self.txt_password = ft.TextField(label="Contraseña", password=True, border="underline")
        self.btn_send = ft.ElevatedButton("Iniciar sesión", bgcolor=ft.colors.BLUE_300, on_click=self.on_login_click)
        self.link_registro = ft.Container(
            content=ft.Column([
                ft.Text("¿No tienes una cuenta?"),
                ft.ElevatedButton("Registrate", on_click=lambda _: self.page.go("/register"))
        ])
    )


    def verify_password(self, password, hash):
        return pbkdf2_sha256.verify(password, hash)

    def on_login_click(self, e):
        try:
            with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'r') as f:
                data = json.load(f)
            if self.txt_correo.value == data["correo"] and self.verify_password(self.txt_password.value, data["contraseña"]):
                print("Inicio de sesión GG")
            else:
                print("Correo o contraseña incorrectos")

        except Exception as e:
            print(f"Se produjo un error al intentar iniciar sesión: {e}")   

    def build(self, page: ft.Page):
        self.page = page
        form_container = ft.Container(
            ft.Column([
                ft.Text("Únete a la mejor red social", size=20, weight="bold"),
                ft.Divider(),
                self.txt_correo,
                self.txt_nombre,
                self.txt_password,
                self.btn_send,
                self.link_registro
            ]),
            padding=5,
            margin=80, 
            height=1200,
            bgcolor=ft.colors.WHITE,
            col={"sm": 12, "md": 10, "xl": 6},
        )

        self.page.add(
            ft.ResponsiveRow(
                [
                    form_container,
                    ft.Container(
                        padding=5,
                        height=1200,
                        bgcolor=ft.colors.BLUE_300,
                        col={"sm": 12, "md": 10, "xl": 6},
                    ),
                ],
            ),
        )

if __name__ == "__main__":
    main = Login()
    ft.app(target=main.build)
