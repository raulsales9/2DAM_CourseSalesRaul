import flet as ft
from passlib.hash import pbkdf2_sha256
import json
import os.path

class Login(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page

    def verify_password(self, password, hash):
         return pbkdf2_sha256.verify(password, hash)
    
    def on_login_click(self, e):
        try:
            with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'r') as f:
                data = json.load(f)
            for usuario in data:
                if self.txt_correo.value == data[usuario]["correo"] and self.verify_password(self.txt_password.value, data[usuario]["contraseña"]):
                    print("Inicio de sesión GG")
                    self.page.go("/kurigram")
                    return  
            print("Correo o contraseña incorrectos")
        except Exception as e:
            print(f"Se produjo un error al intentar iniciar sesión: {e}")
  

    def build(self):
        self.txt_nombre = ft.TextField(label="Nombre", border="underline")
        self.txt_correo = ft.TextField(label="Correo", border="underline")
        self.txt_password = ft.TextField(label="Contraseña", password=True, border="underline")
        self.btn_send = ft.ElevatedButton("Iniciar sesión", bgcolor=ft.colors.BLUE_300, on_click=self.on_login_click)
        self.link_registro =ft.ElevatedButton("Registrate", on_click=lambda _: self.page.go("/register"))      
        form_container = ft.Container(
            ft.Column(
                 controls=[
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

    
        return ft.ResponsiveRow([
                    form_container,
                    ft.Container(
                         padding=5,
                         height=1200,
                         bgcolor=ft.colors.BLUE_300,
                         col={"sm": 12, "md": 10, "xl": 6},
                    ),
        ])


if __name__ == "__main__":
    def main(page: ft.Page):
        login = Login(page)
        page.add(login)
    ft.app(target=main)
