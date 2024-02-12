import flet as ft
from flet import *
from flet_core.matplotlib_chart import MatplotlibChart
from matplotlib import pyplot as plt
import requests


class OtherProfile(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        self.page.title = "Views"
        self.page.theme_mode = ThemeMode.LIGHT
        self.page.padding = 0
        self.page.window_width = 430
        self.page.window_height = 932
        self.page.vertical_alignment = MainAxisAlignment.CENTER
        self.page.horizontal_alignment = CrossAxisAlignment.CENTER
        self.page.update()

        self.data = [
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

    def build(self):
        user_stack = self.create_user_stack()
        mail_container = self.create_mail_container()
        buttons_row = self.create_buttons_row()
        graph_container = self.create_graph_container()

        main_container = Container(
            content=Column([Container(content=user_stack, alignment=ft.alignment.center),
                            mail_container, buttons_row, graph_container]),
            height=650,
            width=self.page.window_width,
        )

        return main_container

    def create_user_name_container(self):
        user_name = Container(
            content=Row([Text("Pepe", size=48, color="#FFFFFF")], alignment=MainAxisAlignment.CENTER)
        )
        return user_name

    def create_money_container(self):
        money = Container(
            content=Row([
                Text("160", size=40, color="#FFFFFF"),
                Image(src="img/coin.png", width=35, height=35),
            ], alignment=MainAxisAlignment.CENTER),
            alignment=ft.alignment.center,
        )
        return money

    def create_user_stack(self):
        user_avatar = CircleAvatar(
            foreground_image_url="https://avatars.githubusercontent.com/u/5041459?s=88&v=4",
            width=225,
            height=190
        )
        trophy_icon = Image(
            src="img/trophy.png",
            width=65,
            height=63,
            tooltip="trophy",
            fit=ImageFit.CONTAIN,
        )
        trophy_container = Container(content=trophy_icon, alignment=ft.alignment.top_right,
                                     on_click=lambda e: self.page.go("/achievements"))

        user_stack = Stack([user_avatar, trophy_container], width=225, height=190)
        return user_stack

    def create_mail_container(self):
        mail_container = Container(
            content=Row([
                Image(src="img/message.png", width=25, height=25),
                Text("pepe@gmail.com", size=19),
            ], alignment=MainAxisAlignment.CENTER),
            alignment=ft.alignment.center,
        )
        return mail_container

    def create_graph_container(self):
        categories = [entry["tipo"] for entry in self.data]
        counts = [entry["count"] for entry in self.data]

        fig, ax = plt.subplots()
        ax.bar(categories, counts)
        ax.set_xlabel('Tipo de Acción')
        ax.set_ylabel('Número de Acciones')
        ax.set_title('Número de Acciones por Tipo')
        # ax.set_facecolor('#F0FCE9')
        # fig.set_facecolor('#F0FCE9')
        chart = MatplotlibChart(fig, expand=True)
        return chart

    def create_buttons_row(self):
        follow_button = Container(
            content=Text("Follow", color="#FFFFFF", size=24),
            bgcolor="#2A7516",
            width=128,
            height=35,
            border_radius=25,
            alignment=ft.alignment.center,
            on_click=lambda e: print("Follow")
        )
        messages_button = Container(
            content=Text("Messages", color="#FFFFFF", size=24),
            bgcolor="#2A7516",
            width=128,
            height=35,
            border_radius=20,
            alignment=ft.alignment.center,
            on_click=lambda e: self.page.go("/message")
        )

        buttons_row = Row([follow_button, messages_button], alignment=MainAxisAlignment.CENTER)
        return buttons_row


if __name__ == '__main__':
    def main(page: Page):
        op = OtherProfile(page)
        page.add(op)


    ft.app(target=main, assets_dir="../assets/")
