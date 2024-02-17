import flet as ft
import requests
import json

class Login(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        page.window_width = 430
        page.window_height = 932
        self.build()
        
    def build(self):
        self.jumbotron = ft.Container(ft.Image(src=f"img/img_login.png", fit=ft.ImageFit.FILL), alignment=ft.alignment.center)
        self.txt_name_field = ft.TextField(label="User", border="underline")
        self.txt_name = ft.Container(self.txt_name_field, alignment=ft.alignment.center)
        self.passwd_field = ft.TextField(label="Password", border="underline",password=True, can_reveal_password=True)
        self.passwd = ft.Container(self.passwd_field, alignment=ft.alignment.center)
        self.btn_login = ft.ElevatedButton("Login", bgcolor=ft.colors.GREEN_200, disabled=False)
        self.btn_login.on_click = self.on_login_clicked 
        self.btn_login_container = ft.Container(self.btn_login, alignment=ft.alignment.center)
        self.reminder = ft.Checkbox(label="Reminder")
        self.forgot_passwd = ft.Text("Forgot password", text_align="RIGHT",  color=ft.colors.BLUE_600)
        self.reminder_container = ft.Container(self.reminder, alignment=ft.alignment.center)
        self.forgot_passwd_container = ft.Container(self.forgot_passwd, alignment=ft.alignment.center)
        self.options_account = ft.Container(ft.Row([self.reminder_container, self.forgot_passwd_container]), alignment=ft.alignment.center)
        self.alternative = ft.Container(ft.Text("Or login with: "), alignment=ft.alignment.center)
        self.image_instagram = ft.Container(ft.Image(src=f"img/icons8-instagram-50.png"),bgcolor="#2D7516", height=60, width=60, alignment=ft.alignment.center)
        self.image_facebook = ft.Container(ft.Image(src=f"img/icons8-facebook-50.png"),bgcolor="#2D7516", height=60, width=60, alignment=ft.alignment.center)
        self.image_google = ft.Container(ft.Image(src=f"img/icons8-google-plus-circled-50.png"),bgcolor="#2D7516", height=60, width=60, alignment=ft.alignment.center)
        self.social_login = ft.Row([self.image_instagram, self.image_facebook, self.image_google], alignment=ft.alignment.center)
        self.social_login_container = ft.Container(self.social_login, alignment=ft.alignment.center)    
        self.message_signin = ft.Text("Dont have an account? " )
        self.signin_link =ft.ElevatedButton("Sign in", on_click=lambda _: self.page.go("/register"),color=ft.colors.BLUE_600)
        self.signin_message = ft.Row([self.message_signin, self.signin_link], alignment=ft.alignment.center)
        self.signin_message_container = ft.Container(self.signin_message, alignment=ft.alignment.center)
        jumbotron = ft.Container(ft.Column(controls=[self.jumbotron]), bgcolor="#BDF3A3")
        form = ft.Container(
            ft.Column(
                controls=[
                    ft.Container(ft.Text("Welcome back!", size=20), alignment=ft.alignment.center),
                    ft.Container(ft.Divider(), alignment=ft.alignment.center),
                    self.txt_name,
                    self.passwd,
                    ft.Container(height=15),
                    self.btn_login_container,
                    self.options_account,
                    self.alternative,
                    self.social_login_container,
                    self.signin_message_container,
                ],
                horizontal_alignment=ft.alignment.center
            ),
            height=600,
            padding=10
        )
        content = ft.Container(
        ft.ResponsiveRow([
            jumbotron,
            form
        ],
        alignment=ft.alignment.center),

        image_src=self.bgimage.src, image_fit=self.bgimage.fit, expand=True,alignment=ft.alignment.center
        )

        return content
        
    def validate_fields(self):
        if self.txt_name_field.value and self.passwd_field.value:
            self.btn_login.disabled = False
            self.page.update()
            return True
        else:
            self.btn_login.disabled = True
            ft.AlertDialog("Por favor, rellena todos los campos")

    def on_login_clicked(self, event):
        if self.validate_fields():
            url_signin = "http://localhost:8000/tasks/signin/"
            data = {
                "username": self.txt_name_field.value,
                "password": self.passwd_field.value,
            }

            print("Datos de la solicitud:", data)

            response = requests.post(url_signin, json=data)

            print("Respuesta del servidor:", response.text)

            if response.status_code == 200:
                token = response.json().get('token', '')
                if token:
                    self.token = token
                    self.page.session.set("token", token)
                    self.page.go("/main")
                    print("Usuario autenticado con éxito, redirigiendo a la tienda")
                    print("Token: " + token)
                else:
                    print("El servidor no devolvió un token válido")
            else:
                try:
                    error_message = response.json().get('error', 'Error desconocido al autenticar el usuario')
                except json.decoder.JSONDecodeError:
                    error_message = f"Error al decodificar la respuesta JSON: {response.text}"
                print(f"Error al autenticar el usuario: {error_message}")


if __name__ == "__main__":
    def main(page: ft.Page):
        login = Login(page)
        login.btn_login.on_click = login.on_login_clicked
        page.add(login)
    ft.app(target=main, assets_dir="../assets/")