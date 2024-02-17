import flet as ft
from flet import *

class Greenshop(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)

        self.products = [
            {"title": "Solar Panel", "subtitle": "Germans Hnr", "description": "80*80cm 1790 kW", "price": "$160", "image": "img/logo_transparent.png"},
            {"title": "Air Purifier", "subtitle": "Germans Hnr", "description": "", "price": "$160", "image": "img/logo_transparent.png"},
            {"title": "Imrobot roomba", "subtitle": "Cosins Hnr", "description": "120*80cm 1790 kW", "price": "$160", "image": "img/logo_transparent.png"},
            {"title": "Solar Panel", "subtitle": "Germans Hnr", "description": "80*80cm 1790 kW", "price": "$160", "image": "img/logo_transparent.png"},

        ]
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Greenshop",
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
        
    def build(self):
        rows = []
        for product in self.products:
            image_container = ft.Container(ft.Image(src=product["image"], width=100, height=30), width=100, height=100)
            title_row = ft.Row([ft.Container(ft.Text(product["title"], size=20), width=100, height=30)])
            subtitle_row = ft.Row([ft.Container(ft.Text(product["subtitle"], size=15), width=100, height=30)])
            buy_price_row = ft.Row([ft.Container(ft.Text(product["price"], size=18), width=50, height=30), ft.Container(ft.ElevatedButton("Buy", bgcolor="#2A7516", color="#FFFFFF"), width=50, height=30)])
            info_container = ft.Container(ft.Column([title_row, subtitle_row, buy_price_row]), width=300, height=100)
            main_container = ft.Container(ft.Row([image_container, info_container]), width=400, height=130, margin=10)
            rows.append(main_container)
        self.content = ft.Container(ft.Column(rows, scroll="always", width=400,height=100), width=400, height=530) 
        return ft.ResponsiveRow([
            self.content
        ])
        

if __name__ == "__main__":
    def main(page: ft.Page):
        registry = Greenshop(page)
        page.add(registry)
    ft.app(target=main, assets_dir="../assets/")