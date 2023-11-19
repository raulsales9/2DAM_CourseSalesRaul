import flet as ft

class MyApp():
    def __init__(self, num_rows, num_cols):
        self.num_rows = num_rows
        self.num_cols = num_cols

    def create_button(self, i):
        return ft.Container(
            content=ft.Text(value=str(i)),
            alignment=ft.alignment.center,
            width=140,
            height=150,
            bgcolor=ft.colors.AMBER,
        )

    def main(self, page: ft.Page):
        for _ in range(self.num_rows):
            row = ft.Row(self.create_button)
            for _ in range(self.num_cols):
                pass
            page.add(row)

if __name__ == "__main__":
    app = MyApp(10, 10)  # Cambia estos números para modificar el número de filas y columnas
    ft.app(target=app.main)
