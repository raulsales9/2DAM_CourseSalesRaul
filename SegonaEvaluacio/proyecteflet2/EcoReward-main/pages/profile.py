import flet as ft
from flet import *
from flet_core.matplotlib_chart import MatplotlibChart
from matplotlib import pyplot as plt

DATA = [
    {
        "tipo": "Arboles plantados",
        "count": 23
    },
    {
        "tipo": "Productos comprados",
        "count": 5
    },
    {
        "tipo": "Conduccion",
        "count": 23
    }
]

class Profile(ft.UserControl):
    def __init__(self, page):
        super().__init__()
        self.page = page
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 0
        self.page.window_width = 430
        self.page.window_height = 932
        self.page.vertical_alignment = MainAxisAlignment.CENTER
        self.page.horizontal_alignment = CrossAxisAlignment.CENTER
        self.page.update()

    def build(self):
        user_stack = Stack(
            [
                CircleAvatar(
                    foreground_image_url="https://avatars.githubusercontent.com/u/5041459?s=88&v=4",
                    width=225,
                    height=225,
                ),
                Container(
                    content=Image(
                        src="img/trophy.png",
                        width=65,
                        height=63,
                        tooltip="trophy",
                        fit=ImageFit.CONTAIN,
                    ),
                    alignment=ft.alignment.top_right,
                    on_click=lambda e: self.page.go("/achievements") ,
                ),
            ],
            width=200,
            height=200,
        )

        mail_container = Container(
            content=Row(
                [
                    Image(src="img/message.png", width=30, height=30),
                    Text("pepe@gmail.com", size=24, color="#2A7516"),
                    Image(src="img/lapiz.png", width=30, height=30),
                ],
                alignment=MainAxisAlignment.CENTER,
            ),
            alignment=ft.alignment.center,
            on_click=lambda e: print("edit mail"),
        )

        graph_container = self.create_graph_container()

        main_container = Container(
            content=Column(
                [user_stack, mail_container, graph_container], 
                horizontal_alignment=CrossAxisAlignment.CENTER,
            ),
            height=650,
            width=self.page.window_width,
        )

        return main_container



    def create_graph_container(self):
        categories = [entry["tipo"] for entry in DATA]
        counts = [entry["count"] for entry in DATA]

        fig, ax = plt.subplots()
        ax.bar(categories, counts)
        ax.set_xlabel('Tipo de Acción')
        ax.set_ylabel('Número de Acciones')
        ax.set_title('Número de Acciones por Tipo')
        ax.set_facecolor('#F0FCE9')
        fig.set_facecolor('#F0FCE9')
        chart = MatplotlibChart(fig, expand=True)
        return chart

if __name__ == "__main__":
    def main(page: Page):
        profile = Profile(page)
        page.add(profile)

    ft.app(target=main)
