import flet as ft
from timeline import *

COLOR_PRIMARY = ft.colors.INDIGO_900
COLOR_SECONDARY = 'rgb(242, 242, 242)'
COLOR_BACKGROUND = ft.colors.WHITE

class App:
    def main(self, page: ft.Page):
        page.bgcolor = COLOR_SECONDARY

        page.appbar = ft.AppBar(
            leading=ft.Image('./assets/logo_kurigram_color_slogan.png', width=50),
            leading_width=50,
            bgcolor=COLOR_PRIMARY,
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
            ]
        )
        timeline = Timeline() 
        page.add(ft.ResponsiveRow([
            self.sidebar(),
            self.posts(),
            timeline.get_timeline(),
            self.event(), 
        ]))

    def sidebar(self):
        sidebar_container = ft.Container(
            bgcolor=COLOR_SECONDARY, 
            col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
        )
        return sidebar_container

    def posts(self):
        post_containers = []

        for post in self.get_post_data():
            post_container = ft.Container(
                ft.Text(post),
                bgcolor=COLOR_BACKGROUND,
                padding=16,
                margin={"bottom": 16},
                border_radius=8,  
            )
            post_containers.append(post_container)

        posts_container = ft.Container(
            *post_containers,
            bgcolor=COLOR_SECONDARY,
            col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
        )

        return posts_container

    def event(self):
        events_container = []

        for event in self.get_events_data():
            event = ft.Container(
                ft.Text(event),
                bgcolor=COLOR_BACKGROUND, 
                padding=16,
                margin={"bottom": 16},
                border_radius=8,  
            )
            events_container.append(event)
        event_container = ft.Container(
            *events_container,
            bgcolor=COLOR_SECONDARY, 
            col={"xs": 12, "sm": 6, "md": 4, "lg": 3, "xl": 2},
        )

        return event_container

    def get_post_data(self):
        return ["Post 1", "Post 2", "Post 3"]
    
    def get_events_data(self):
        return ["Event 1", "Event 2", "Event 3"]

if __name__ == "__main__":
    app = App() 
