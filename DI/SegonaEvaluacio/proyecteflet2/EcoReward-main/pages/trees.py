import flet as ft
import datetime


class Trees(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Trees",
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
        self.total_trees = ft.Container(ft.Text("Total trees: " + str(len(self.products))),
                                        alignment=ft.alignment.center)
        self.history = ft.Container(ft.Text("History:"), alignment=ft.alignment.center)
        self.register_button = ft.Container(ft.ElevatedButton("Register new tree", on_click=self.add_tree), margin=50,
                                            height=50, width=260, alignment=ft.alignment.center)
        rows.append(self.total_trees)
        rows.append(self.history)
        for product in self.products:
            self.icon_tree = ft.Container(ft.Image(src=f"img/img_tree.png", height=50, width=60), bgcolor="#2D7516")
            self.date = ft.Container(ft.Text(product["date"], size=15))
            self.content_row = ft.Row([self.icon_tree, self.date])
            self.container = ft.Container(self.content_row, alignment=ft.alignment.center, bgcolor="#FFFFFF")
            rows.append(self.container)
        self.content = ft.Container(ft.Column(rows, scroll="always", width=400, height=100), width=400, height=430,#image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit
            )
        return ft.ResponsiveRow([self.content, self.register_button])

    def add_tree(self, sender):
        new_tree = {"date": datetime.datetime.now().strftime("%d/%m/%Y")}
        self.products.append(new_tree)
        self.page.clean()
        self.page.add(self)


if __name__ == '__main__':
    def main(page: ft.Page):
        terms_and_conditions = Trees(page)
        page.add(terms_and_conditions)


    ft.app(target=main, assets_dir="../assets/")
