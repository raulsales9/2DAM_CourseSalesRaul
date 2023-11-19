import flet as ft

class MyApp():
    def __init__(self, num_rows, num_cols):
        self.num_rows = num_rows
        self.num_cols = num_cols
        self.button_width = 140
        self.button_height = 150

    def create_button(self, i):
        return ft.Container(
            content=ft.Text(value=str(i)),
            alignment=ft.alignment.center,
            width=self.button_width,
            height=self.button_height,
            bgcolor=ft.colors.AMBER,
        )

    def main(self, page: ft.Page):
        column_controls = [self.create_button(i) for i in range(self.num_rows)]
        column = ft.Column(column_controls)

        row_controls = [self.create_button(i) for i in range(self.num_cols)]
        row = ft.Row(row_controls,width=page.window_width,)
    
        page.add(column, row)

        window_width = self.num_cols * self.button_width
        window_height = self.num_rows * self.button_height
        page.size = (window_width, window_height)


if __name__ == "__main__":
    app = MyApp(10, 10) 
    ft.app(target=app.main)
