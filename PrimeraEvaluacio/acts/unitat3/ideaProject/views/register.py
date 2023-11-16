import flet as ft
from passlib.hash import pbkdf2_sha256
import json
import os.path

class Register:
    def __init__(self):
        self.txt_nombre = ft.TextField(label="Nombre", border="underline")
        self.txt_correo = ft.TextField(label="Correo", border="underline")
        self.link_registro = ft.Text("Don't have an account? Register here",) #url="#")
        self.user = ft.TextField(label="nombre de usuario")
        self.passwd = ft.TextField(label="Contraseña ", password=True, can_reveal_password=False)
        self.passwd2 = ft.TextField(label="Repite la contraseña", password=True, can_reveal_password=False)
        self.btn_send = ft.ElevatedButton("SignIn", bgcolor=ft.colors.BLUE_300)

    def hash_password(self, password):
        return pbkdf2_sha256.hash(password, rounds=20000, salt_size=16)

    def verify_password(self, password):
        return pbkdf2_sha256.verify(password, hash)

    def on_button_click(self, e):
        try:
            hashed_password = self.hash_password(self.passwd.value)
            print(f"Hashed password: {hashed_password}")

            data = {
                "nombre": self.txt_nombre.value,
                "correo": self.txt_correo.value,
                "usuario": self.user.value,
                "contraseña": hashed_password
            }

            # Intentar guardar los datos en un fichero JSON
            with open(os.path.join(os.path.dirname(__file__),'datos.json'), 'w') as f:
                json.dump(data, f)

            print("Datos guardados con éxito en 'datos.json'")

        except Exception as e:
            print(f"Se produjo un error al intentar guardar los datos: {e}")

    def main(self, page: ft.Page):
        def page_resize(e):
            pw.value = f"{page.width} px"
            pw.update()

        page.on_resize = page_resize

        pw = ft.Text(bottom=50, right=50, style="displaySmall")
        page.overlay.append(pw)

        form_container = ft.Container(
            ft.Column([
                ft.Text("Come with the best Social network", size=20, weight="bold",  ),
                ft.Divider(),
                self.txt_correo,
                self.txt_nombre,
                self.user,
                self.passwd,
                self.passwd2,
                self.btn_send,
                self.link_registro
            ]),
            padding=5,
            margin=80, 
            height=1200,
            bgcolor=ft.colors.WHITE,
            col={"sm": 12, "md": 10, "xl": 6},
        )

        page.add(
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

        self.btn_send.on_click = self.on_button_click

if __name__ == "__main__":
    main = Register()
    ft.app(target=main.main)
