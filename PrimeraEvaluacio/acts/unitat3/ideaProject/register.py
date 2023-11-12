import flet as ft
from passlib.hash import pbkdf2_sha256
def main(page: ft.Page):
    def page_resize(e):
        pw.value = f"{page.width} px"
        pw.update()

    page.on_resize = page_resize

    pw = ft.Text(bottom=50, right=50, style="displaySmall")
    page.overlay.append(pw)

    # Crear los campos del formulario
    txt_nombre = ft.TextField(label="Nombre", border="underline")
    txt_correo = ft.TextField(label="Correo", border="underline")
    btn_send = ft.ElevatedButton("SignIn", bgcolor=ft.colors.BLUE_300)
    link_registro = ft.Text("Don't have an account? Register here",) #url="#")
    user = ft.TextField(label="nombre de usuario")
    passwd = ft.TextField(label="Contraseña ", password=True, can_reveal_password=False)
    passwd2 = ft.TextField(label="Repite la contraseña", password=True, can_reveal_password=False)

    # Agregar los campos a un contenedor
    form_container = ft.Container(
        ft.Column([
            ft.Text("Come with the best Social network", size=20, weight="bold",  ),
            ft.Divider(),
            txt_correo,
            txt_nombre,
            user,
            passwd,
            passwd2,
            btn_send,
            link_registro
        ]),
        padding=5,
        margin=80, 
        height=1200,
        #top=50
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

    def hash_password(password):
        return pbkdf2_sha256.hash(password, rounds=20000, salt_size=16)
    
    def verify_password(password):
        return pbkdf2_sha256.verify(password, hash)
    
    def on_button_click(e):
        hashed_password = hash_password(passwd.value)
        print(f"Hashed password: {hashed_password}")

    btn_send.on_click = on_button_click


ft.app(target=main)
