import flet as ft
import datetime


class Recycling(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        page.bgcolor = "#BDF3A3"
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Recycling",
                style=ft.TextStyle(
                    color="white",
                    size=36,
                ),
            ),
            bgcolor="#63d138",
            automatically_imply_leading=False,
            toolbar_height=100,
            center_title=True,
        )
        self.page.add(self.appbar)

        self.products = [
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
            {"date": "03/08/2003"},
        ]

    def build(self):
        rows = []
        self.history = ft.Container(ft.Text("History:"), alignment=ft.alignment.center)
        self.register_button = ft.Container(
            ft.ElevatedButton("New Recycling", bgcolor="#2A7516", color="#FFFFFF", on_click=self.add_recycling),
            margin=50, height=50, width=260, alignment=ft.alignment.center)
        rows.append(self.history)
        for product in self.products:
            self.icon_tree = ft.Container(ft.Image(src=f"img/img_reci.png", height=30, width=40), bgcolor="#2D7516")
            self.date = ft.Container(ft.Text(product["date"], size=15))
            self.content_row = ft.Row([self.icon_tree, self.date], height=50, width=150, alignment=ft.alignment.center)
            self.container = ft.Container(self.content_row, alignment=ft.alignment.center, bgcolor="#FFFFFF",
                                          border_radius=10, width=300)
            rows.append(self.container)
        self.content = ft.Container(
            ft.Column(rows, scroll="always", width=400, height=100, alignment=ft.alignment.center), width=400,
            height=430,#image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit
            )
        return ft.ResponsiveRow([self.content, self.register_button], alignment=ft.alignment.center)

    def add_recycling(self, sender):
        new_recycling = {"date": datetime.datetime.now().strftime("%d/%m/%Y")}
        self.products.append(new_recycling)
        self.page.clean()
        self.page.add(self)


if __name__ == '__main__':
    def main(page: ft.Page):
        terms_and_conditions = Recycling(page)
        page.add(terms_and_conditions)


    ft.app(target=main, assets_dir="../assets/")
