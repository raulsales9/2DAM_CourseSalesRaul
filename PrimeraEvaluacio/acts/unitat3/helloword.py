import flet as ft

def main(page: ft.Page):
    page.title = "Hello world with Flet!!"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER

    # txt_number = ft.Text(value='Hello world with Flet!!', text_align=ft.TextAlign.LEFT)
    text = ft.Text(value="Hello, world!", color="green")
    row = ft.Row(controls=[
        ft.TextField(label="Your name"),
        ft.ElevatedButton(text="Send")
    ])
    page.add(
        #txt_number
        text,
        row
    )

ft.app(target=main)