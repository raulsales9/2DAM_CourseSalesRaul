import flet as ft
from passlib.hash import pbkdf2_sha256
#import os.path

def main(page: ft.Page):
    def page_resize(e):
        pw.value = f"{page.width} px"
        pw.update()

    page.on_resize = page_resize

    pw = ft.Text(bottom=50, right=50, style="displaySmall")
    page.overlay.append(pw)
    txt_nombre = ft.TextField(label="Nombre", border="underline")
    txt_correo = ft.TextField(label="Correo", border="underline")
    link_registro = ft.Text("Don't have an account? Register here",) #url="#")
    user = ft.TextField(label="nombre de usuario")
    passwd = ft.TextField(label="Contraseña ", password=True, can_reveal_password=False)
    passwd2 = ft.TextField(label="Repite la contraseña", password=True, can_reveal_password=False)
    btn_send = ft.ElevatedButton("SignIn", bgcolor=ft.colors.BLUE_300, disabled=True)
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

    def validate_fields():
        if not txt_nombre.value or not txt_correo.value or not user.value or not passwd.value or not passwd2.value:
            print("Todos los campos deben estar rellenados.")
            return False

        if not (txt_nombre.value[0].isalpha() and txt_nombre.value.replace(" ", "").isalnum()):
            print("El nombre debe comenzar con una letra y no contener espacios ni caracteres extraños.")
            return False
        
        if passwd.value != passwd2.value:
            print("Las contraseñas no coinciden.")
            return False

        btn_send.disabled = False
        return True
    # def onclicked():
    #     if validate_fields():
    #         btn_submit.disabled = False
    #         try:
    #             if not os.path.exists('status'):
    #                 os.makedirs('status')

    #             with open('status/data.txt', 'w') as f:
    #                 f.write(f"Nombre: {txt_nombre.value}\n")
    #                 f.write(f"Correo: {txt_correo.value}\n")
    #                 f.write(f"Nombre de usuario: {user.value}\n")
    #                 f.write(f"Contraseña hasheada: {hash_password(passwd.value)}\n")
    #         except Exception as e:
    #             print(f"Error al escribir en el archivo: {e}")
    #         return True
    #     else:
    #         btn_submit.disabled = True
    #         return False
        
    # btn_submit = ft.FilledButton(
    #                 "Enviar", on_click=onclicked,disabled=True,
    #                 style=ft.ButtonStyle(
    #                     shape=ft.RoundedRectangleBorder(radius=10),
    #                 )
    #             )

    # def test_data():
    #     try:
    #         with open('status/data.txt', 'r') as f:
    #             data = f.read()
    #             print(f"Datos en 'data.txt':\n{data}")
    #     except Exception as e:
    #         print(f"Error al leer el archivo: {e}")

    # btn_send.on_click = lambda e: (on_button_click(e), test_data())

if __name__ == "__main__":
    ft.app(target=main)