import flet as ft
import requests

class Registry(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)

    def on_registry_validated(self, e):
        username = self.txt_name.value
        email = self.txt_email.value
        password = self.passwd.value
        password2 = self.passwd2.value

        if password != password2:
            print("Las contraseñas no coinciden")
            return

        url = "http://localhost:8000/tasks/signup/"
        data = {
            "username": username,
            "email": email,
            "password": password,
        }
        print(data)
        response = requests.post(url, json=data)
        if response.status_code == 200:
            print("Usuario registrado con éxito")
            token = response.json().get('token')
            if token:
                self.token = token
                self.page.session.set("token",token)
            else:
                print("No se recibió ningún token del servidor")
            self.page.go("/main")
            print(token)
        elif response.status_code == 400:
            print(f"Error al registrar el usuario: {response.json()}")
        else:
            print(f"Respuesta inesperada de la API: {response.content}")

    def on_social_registry(self, e):
        pass
       
    def build(self):
        self.jumbotron = ft.Image(src=f"img/img_login.png", fit=ft.ImageFit.FILL)
        self.image_user = ft.Container(ft.Image(src=f"img/img_user.png"),bgcolor="#2D7516", height=40, width=40)
        self.txt_name = ft.TextField(label="User", border="underline")
        self.row_name = ft.Row([self.image_user,self.txt_name])
        self.image_email = ft.Container(ft.Image(src=f"img/img_email.png"),bgcolor="#2D7516", height=40, width=40)
        self.txt_email = ft.TextField(label="Email", border="underline")
        self.row_email = ft.Row([self.image_email,self.txt_email])
        self.image_passwd = ft.Container(ft.Image(src=f"img/img_passwd.png"),bgcolor="#2D7516", height=40, width=40, )
        self.passwd = ft.TextField(label="Password", border="underline", password=True, can_reveal_password=True)
        self.row_passwd = ft.Row([self.image_passwd,self.passwd])
        self.passwd2 = ft.TextField(label="Password", border="underline", password=True, can_reveal_password=True)
        self.row_passwd2 = ft.Row([self.image_passwd,self.passwd2])
        self.btn_registry = ft.ElevatedButton("Register", bgcolor=ft.colors.GREEN_200, on_click=self.on_registry_validated)
        self.btn_registry_container = ft.Container(self.btn_registry, alignment=ft.alignment.center)
        jumbotron = ft.Container(ft.Column(controls=[self.jumbotron]), bgcolor="#BDF3A3")
        form = ft.Container(
            ft.Column(
                controls=[
                    ft.Text("Welcome back!", size=20),
                    ft.Divider(),
                    self.row_name,
                    self.row_email,
                    self.row_passwd,
                    self.row_passwd2,
                    ft.Container(height=35),
                    self.btn_registry_container,
                ]
            ),
            margin=20,
            height=600,
            padding=10,
            image_src=self.bgimage.src, 
            image_fit=self.bgimage.fit
        )
        content= ft.Container(
            ft.ResponsiveRow([
                jumbotron,
                form
            ],
            alignment=ft.alignment.center),
            image_src=self.bgimage.src, image_fit=self.bgimage.fit, expand=True,alignment=ft.alignment.center
        )

        return content
        
    
if __name__ == "__main__":
    def main(page: ft.Page):
        registry = Registry(page)
        page.add(registry)
    ft.app(target=main, assets_dir="../assets/")
