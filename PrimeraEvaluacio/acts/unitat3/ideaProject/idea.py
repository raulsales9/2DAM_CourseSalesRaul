import flet as ft

class App:
    def main(self, page: ft.Page):
        page.appbar = ft.AppBar(
            leading=ft.Image('./assets/logo_kurigram_color_slogan.png', width=50),
            leading_width=50,
            bgcolor=ft.colors.INDIGO_900,
            actions=[
                ft.Column([
                    ft.IconButton(ft.icons.HOME),
                ], ),
                ft.Column([
                    ft.IconButton(ft.icons.PERSON),
                ],),
                ft.Column([
                    ft.IconButton(ft.icons.MENU),
                ], ),
                #visible={"md": True, "lg": True, "xl": True}
            ]
        )
        
        self.posts(page)
        self.events(page)
        page.add(ft.ResponsiveRow([
            ft.Container(
                bgcolor=ft.colors.GREY_300,  
                col={"sm": 12, "md": 6, "xl": 4},
            )
        ]))

    def posts(self, page: ft.Page):
        for post in self.get_post_data():
            post_container = ft.Container(
                ft.Text(post),
                bgcolor=ft.colors.WHITE, 
                padding=16,
                margin={"bottom": 16},
                col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
                border_radius=8,  
            )

            page.add(post_container)
    def events(self, page: ft.Page):
        for post in self.get_post_data():
            events_container = ft.Container(
                ft.Text(post),
                bgcolor=ft.colors.WHITE, 
                padding=16,
                margin={"bottom": 16},
                col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
                border_radius=8,  
            )

            page.add(events_container)

    def get_post_data(self):
        return ["Post 1", "Post 2", "Post 3"]
    
    def get_events_data(self):
        return ["Event 1", "Event 2", "Event 3"]

if __name__ == "__main__":
    app = App()
    ft.app(target=app.main)
