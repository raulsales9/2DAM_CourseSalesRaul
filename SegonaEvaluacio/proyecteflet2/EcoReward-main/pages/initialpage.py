import flet as ft
import threading

class InitialPage(ft.UserControl):
    def __init__(self, page: ft.Page):
        super().__init__()
        self.page = page
        page.window_width = 430
        page.window_height = 932
        page.padding = 0
        self.bgimage = ft.Image(src=f"img/fondo_degradado.png", fit=ft.ImageFit.COVER, width=430, height=932)
        
    def build(self):
        self.bottom_image = ft.Image(src=f"img/appbar_login.png", fit=ft.ImageFit.CONTAIN, width=430, height=380)
        loading_text = ft.Container(ft.Text("loading...", color="white", size=24) , alignment=ft.alignment.center)
        self.top_image = ft.Image(src=f"img/logo_transparent.png", fit=ft.ImageFit.CONTAIN, width=430, height=380)
        main_container = ft.Container(ft.Column([self.top_image, loading_text, self.bottom_image]), image_src=self.bgimage.src, image_fit=self.bgimage.fit, expand=True)
        threading.Timer(5.0, self.go_to_login).start()
        return main_container

    def go_to_login(self):
        self.page.go("/login")
        print("done")

if __name__ == "__main__":
    def main(page: ft.Page):
        init = InitialPage(page)
        page.add(init)       
    ft.app(target=main, assets_dir="../assets/")
