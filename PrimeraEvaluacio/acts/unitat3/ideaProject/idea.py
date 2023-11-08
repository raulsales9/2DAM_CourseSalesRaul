import flet as ft

class App(ft.App):
    def __init__(self) -> None:
        super().__init__(
            routes={
                "/": self.home,
            },
            initial_route="/",
        )

    def home(self):
        return ft.Scaffold(
            appBar=ft.AppBar(
                title="Mi Red Social",
            ),
            body=ft.Row(
                children=[
                    self._build_left_column(),
                    self._build_middle_column(),
                    self._build_right_column(),
                ],
            ),
        )

    def _build_left_column(self):
        return ft.Column(
            children=[
                ft.CircleAvatar(
                    child=ft.Icon(ft.Icons.person),
                ),
                ft.Text("Hola, Usuario!"),
                ft.RaisedButton(
                    child=ft.Text("Aplicar tema oscuro"),
                    onPressed=lambda: print("Botón Aplicar tema oscuro presionado"),
                ),
            ],
        )

    def _build_middle_column(self):
        return ft.Column(
            children=[
                ft.TextField(
                    decoration=ft.InputDecoration(
                        labelText="Crear una publicación",
                    ),
                ),
                ft.Expanded(
                    child=ft.ListView(
                        children=[
                            ft.ListTile(
                                title=ft.Text("Publicación 1"),
                            ),
                            ft.ListTile(
                                title=ft.Text("Publicación 2"),
                            ),
                            # Añade más ListTile aquí para más publicaciones
                        ],
                    ),
                ),
            ],
        )

    def _build_right_column(self):
        return ft.Column(
            children=[
                ft.Text("Eventos próximos"),
                ft.RaisedButton(
                    child=ft.Text("Mostrar interés"),
                    onPressed=lambda: print("Botón Mostrar interés presionado"),
                ),
                # Añade más elementos aquí para más secciones
            ],
        )

if __name__ == "__main__":
    app = App()
    app.run()
