import flet as ft
from passlib.hash import pbkdf2_sha256
from pages.content import leer_datos, escribir_datos, leer_eventos


class Register(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
    def hash_password(self, password):
        return pbkdf2_sha256.hash(password, rounds=20000, salt_size=16)

    def verify_password(self, password):
        return pbkdf2_sha256.verify(password, hash)

    def on_button_click(self, e):
        try:
            hashed_password = self.hash_password(self.passwd.value)
            print(f"Hashed password: {hashed_password}")
            data = leer_datos()
            new_data = {
                "id": len(data) + 1,
                "nombre": self.txt_nombre.value,
                "correo": self.txt_correo.value,
                "usuario": self.user.value,
                "contraseña": hashed_password,
                "seguidores": 0,
                "seguidos": 0,
                "posts": []
            }
            data[self.user.value] = new_data
            escribir_datos(data)

            print("Datos guardados con éxito en 'datos.json'")
            self.page.go("/kurigram")
        except Exception as e:
            print(f"Se produjo un error al intentar guardar los datos: {e}")
            return





    def build(self):
        self.txt_nombre = ft.TextField(label="Nombre", border="underline")
        self.txt_correo = ft.TextField(label="Correo", border="underline")
        self.link_registro = ft.Text("Don't have an account? Register here",) #url="#")
        self.user = ft.TextField(label="nombre de usuario")
        self.passwd = ft.TextField(label="Contraseña ", password=True, can_reveal_password=False)
        self.passwd2 = ft.TextField(label="Repite la contraseña", password=True, can_reveal_password=False)
        self.btn_send = ft.ElevatedButton("SignIn", bgcolor=ft.colors.BLUE_300, on_click=self.on_button_click)
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
        self.btn_send.on_click = self.on_button_click  

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
        register = Register(page)
        page.add(register)
    ft.app(target=main)
