import flet as ft
import random
from colours import light_theme, dark_theme

class App:
    def __init__(self, num_items: int) -> None:
        self._num_items = num_items
        self._target_number = random.randint(1, num_items)
        self.pressed_numbers = []
        self.theme = light_theme
        self.game_won = False 
        self.button_states = [ft.colors.AMBER] * num_items  
        print(f"El número objectiu és: {self._target_number}")
        ft.app(target=self.start)

    @property
    def num_items(self):
        return self._num_items

    @num_items.setter
    def num_items(self, value):
        self._num_items = value

    @property
    def target_number(self):
        return self._target_number

    @target_number.setter
    def target_number(self, value):
        self._target_number = value

    def start(self, page: ft.Page):
        self.page = page
        self.page.title = "Troba el número" 
        self.page.add(ft.ElevatedButton("Comença el joc", on_click=self.main))
    
    def main(self, args=None):
        self.page.clean()
        self.buttons = [] 
        row = ft.Row(
            wrap=True,
            spacing=10,
            run_spacing=10,
            controls=self.items(self.num_items),
            width=self.page.window_width,
        )

        if self.game_won:
            controls = [row, ft.ElevatedButton("Reiniciar", on_click=self.restart)]
        else:
            controls = [row]

        self.page.views.append(
            ft.View(
                "/items",
                [
                    ft.Column(
                        [
                            ft.Text("Numeros :"),
                        ] + controls
                    )
                ]
            )
        )

        self.page.go("/items")
        
    def items(self, count):
        items = []
        for i in range(1, count + 1):
            button = ft.Container(
                content=ft.Text(value=str(i)),
                alignment=ft.alignment.center,
                width=140,
                height=150,
                bgcolor=self.button_states[i - 1],  
                border_radius=ft.border_radius.all(5),
            )
            button.on_click = self.check_number(i, button)
            items.append(button)
            self.buttons.append(button) 
        return items
    
    def check_number(self, number: int, button: ft.Container):
        def on_click(args):
            self.pressed_numbers.append(number) 
            if number == self.target_number:
                self.game_won = True
                button.bgcolor = ft.colors.GREEN
                self.button_states[number - 1] = ft.colors.GREEN 
            else:
                button.bgcolor = ft.colors.RED
                self.button_states[number - 1] = ft.colors.RED 
            button.enabled = False

            # Afegeix una vista per a aquesta ruta
            self.page.views.append(
                ft.View(
                    f"/{number}",
                    [
                        ft.AppBar(title=ft.Text(f"Número {number}"), bgcolor=ft.colors.SURFACE_VARIANT),
                        ft.Container(bgcolor=ft.colors.RED if number != self.target_number else ft.colors.GREEN, width=100, height=100),
                        ft.Text("Incorrecte" if number != self.target_number else "Correcto"),
                        ft.ElevatedButton("Tornar", on_click=lambda _: self.main()),
                    ],
                )
            )

            self.page.go(f"/{number}")
        return on_click


    def restart(self, args=None):
        self._target_number = random.randint(1, self._num_items)
        print(f"El nou número objectiu és: {self._target_number}")
        for i, btn in enumerate(self.buttons):
            btn.enabled = True
            btn.bgcolor = ft.colors.AMBER  
            self.button_states[i] = ft.colors.AMBER 
        self.game_won = False 
        self.main()

if __name__ == "__main__":
    app = App(25)
