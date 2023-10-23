import flet as ft

def main(page):
    fieldset = ft.Container(ft.Text(value="Datos Personas", expand=1))
    textName = ft.Text(value="Nombre: ", expand=1)
    name = ft.TextField(label="name", expand=3)
    textDir = ft.Text("Direccion: ", expand=1)
    dir = ft.TextField(label="direccio", expand=3)
    fieldset2 = ft.Text(value="Datos de acceso", expand=1)
    passwd = ft.TextField(label="Contraseña ", password=True,expand=3, can_reveal_password=True)
    passwd2 = ft.TextField(label="Repite la contraseña", password=True,expand=3, can_reveal_password=True)
    color_dropdown = ft.Dropdown(
        label = "Provincia",
        hint_text= "Selecciona tu provincia",
        width=100,
        options=[
            ft.dropdown.Option("ComboBox"),
            ft.dropdown.Option("Green"),
            ft.dropdown.Option("Blue"),
        ],
        expand=3
    )

    def checkbox_changed():
        return True
    
    def onclicked():
        if(passwd == passwd2):
            return True
        else:
            return False
        
    

    page.add(
        ft.ResponsiveRow([
            fieldset,
            ft.Row(controls=(textName, name)),
            ft.Row(controls=(textDir, dir)),
            ft.Row(controls=(ft.Text(value="Provincies",col={"md": 4}),color_dropdown)),
        ]   
        ),    
        ft.ResponsiveRow([
            fieldset2,
            ft.Row(controls=(ft.Text(value="contraseña", col={"md": 4}),passwd)),
            ft.Row(controls=(ft.Text(value="Repite la contraseña", col={"md": 4}),passwd2)),
        ]
        ),   
        ft.ResponsiveRow([
            ft.Checkbox(label="ToDo: Learn how to use ski",col={"md": 4}, value=False, on_change=checkbox_changed) ,
            ft.Row(
            controls=(
                ft.FilledButton(
                    "Rounded rectangle", on_click=onclicked,disabled=True,
                    style=ft.ButtonStyle(
                    shape=ft.RoundedRectangleBorder(radius=10),
                ),
            ),
        )
        )
        ])
        
    )


ft.app(target=main)