import flet as ft
from pages.routeroutlet import views_router

def main(page: ft.Page):
    def route_change(route):
        page.views.clear()
        page.views.append(
            views_router(page)[page.route]
        )
        
    page.on_route_change = route_change
    page.go("/login")

ft.app(target=main)


# class main(ft.UserControl):
#     def __init__(self, page: ft.Page):
#         super().__init__()
#         self.page =page
#         self.firebase_validator()
    
#     def route_change(self, route):
#         self.page.views.clear()
#         self.page.views.append(
#             views_router(self.page)[self.page.route]
#         )

#     def firebase_validator(self):
#         self.page.on_route_change = self.route_change
#         token = self.load_token()
#         if authenticate_token(token):
#             page.go("/login")
#         else:
#             page.go("/kurigram")

#     def load_token(self,):
#         try:
#             with open('token.pickle', 'rb') as f:
#                 token = pickle.load(f)
#             return token
#         except:
#             return None
        


