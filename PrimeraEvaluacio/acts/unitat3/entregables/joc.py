import flet as ft

class App:
    def __init__(self, args) -> None:
        # self.num_blocks = num_blocks
        ft.app(target=self.main)

    def main(self, page: ft.Page):
        self.click_numbers()
        self.create_blocks()
        self.page = page
        page.title = "Troba el numero correcte "
        msg_text = "Numeros: "
        page.add(
            ft.Text(msg_text),
            ft.ElevatedButton(text="Elevated button")
        )

    def create_blocks(self, page):
        for _ in range(self.num_blocks):
            container = ft.Container(
                content=ft.Text("Clickable with Ink"),
                margin=10,
                padding=10,
                alignment=ft.alignment.center,
                bgcolor=ft.colors.YELLOW_200,
                width=150,
                height=150,
                border_radius=10,
                ink=True,
                on_click=lambda e: print("Clickable with Ink clicked!"),
            )
        page.add(container)    
        
    # method to click the numbers and reveal the correct
    def click_numbers():
        return True
    
    # method to restart the game
    def restart_btn():
        return False

if __name__ == "__main__":
    app = App(25)