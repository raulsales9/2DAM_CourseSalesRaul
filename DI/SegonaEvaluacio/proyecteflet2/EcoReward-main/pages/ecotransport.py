import flet as ft

class Ecoreward(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        
        self.products = [
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_car.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_car.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_car.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
            {"img":"img/img_car.png","date": "03/08/2003"},
            {"img":"img/img_cycle.png","date": "03/08/2003"},
        ]

    def build(self):
        # Añadir AppBar
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Eco Transports",
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

        rows = []
        self.total_kms_label = ft.Container(ft.Text("Total kms:", weight='bold', size=30), alignment=ft.alignment.center)
        self.total_kms_value = ft.Container(ft.Text("49", size=25), alignment=ft.alignment.center)
        self.history = ft.Container(ft.Text("History:"), alignment=ft.alignment.center)
        rows.append(self.total_kms_label)
        rows.append(self.total_kms_value)
        self.register_button = ft.Container(ft.ElevatedButton("Register new trip", bgcolor="#2A7516", color="#FFFFFF"),margin=50, height=50, width=260, alignment=ft.alignment.center)
        rows.append(self.history)
        for product in self.products:
            self.icon_tree = ft.Container(ft.Image(src=product["img"], height=50, width=60), bgcolor="#2D7516",border_radius=10)
            self.date = ft.Container(ft.Text(product["date"], size=15))
            self.content_row = ft.Row([self.icon_tree,self.date])
            self.container = ft.Container(self.content_row,  alignment=ft.alignment.center,bgcolor="#FFFFFF",border_radius=10 )
            rows.append(self.container)
        self.content= ft.Container(ft.Column(rows, scroll="always", width=400, height=100), width=400, height=430)
        return ft.ResponsiveRow([self.content, self.register_button])

if __name__ == '__main__':
    def main(page: ft.Page):
        terms_and_conditions = Ecoreward(page)
        page.add(terms_and_conditions)
    ft.app(target=main, assets_dir="../assets/")
