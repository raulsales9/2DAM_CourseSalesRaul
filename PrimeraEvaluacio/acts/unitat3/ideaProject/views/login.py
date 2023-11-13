import flet as ft

def main(page: ft.Page):
    def page_resize(e):
        pw.value = f"{page.width} px"
        pw.update()

    page.on_resize = page_resize

    pw = ft.Text(bottom=50, right=50, style="displaySmall")
    page.overlay.append(pw)

    txt_nombre = ft.TextField(label="Nombre", border="underline")
    txt_correo = ft.TextField(label="Correo", border="underline")
    txt_password = ft.TextField(label="Password", password=True, border="underline")
    btn_send = ft.ElevatedButton("Login", bgcolor=ft.colors.BLUE_300)
    link_registro = ft.Text("Don't have an account? Register here",) #url="#")

    form_container = ft.Container(
        ft.Column([
            ft.Text("Come with the best Social network", size=20, weight="bold",  ),
            ft.Divider(),
            txt_correo,
            txt_nombre,
            txt_password,
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
if __name__ == "__main__":
    ft.app(target=main)
