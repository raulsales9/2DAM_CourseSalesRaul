import flet as ft

class PoliticOfPrivacity(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        #self.bgimage = ft.Image(src=f"img/fondo.png", fit=ft.ImageFit.COVER)
        page.title = "Terms & conditions"
        
        self.appbar = ft.AppBar( 
            title=ft.Text(
                "Politic of privacity",
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
        self.title = ft.Text("Politic of privacity")
        self.content = ft.Text("Our Companyn the Website.", color="#000000")
        container = ft.Container(
            ft.Column(controls=[self.title, self.content]),
            width=430,
            height=700,
            #image_src=self.bgimage.src, 
            #image_fit=self.bgimage.fit, 
        )
        return ft.ResponsiveRow([
            container,
        ])

if __name__ == '__main__':
    def main(page: ft.Page):
        terms_and_conditions = PoliticOfPrivacity(page)
        page.add(terms_and_conditions)
    ft.app(target=main)
