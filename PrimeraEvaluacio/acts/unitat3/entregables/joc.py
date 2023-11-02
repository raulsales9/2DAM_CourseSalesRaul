import flet as ft
import random

class App:
    def __init__(self, num_items: int) -> None:
        self._num_items = num_items
        self._target_number = random.randint(1, num_items)
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
        self.page.title = "Troba el número"  # Afegeix el títol a la pàgina
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

        # Afegeix una vista per a aquesta ruta
        self.page.views.append(
            ft.View(
                "/items",
                [
                    ft.Column(
                        [
                            ft.Text(
                                "Numeros :"
                            ),
                            row,
                            ft.ElevatedButton("Reiniciar", on_click=self.restart),  # Botón para reiniciar el juego
                        ]
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
                bgcolor=ft.colors.AMBER,
                border_radius=ft.border_radius.all(5),
            )
            button.on_click = self.check_number(i, button)
            items.append(button)
            self.buttons.append(button) 
        return items

    def check_number(self, number: int, button: ft.Container):
        def on_click(args):
            if number == self.target_number:
                button.bgcolor = ft.colors.GREEN
                for btn in self.buttons:
                    btn.enabled = False
                self.page.clean()
                self.page.add(ft.Text("Has trobat el número!"))
                self.page.add(ft.ElevatedButton("Reiniciar", on_click=self.restart))  # Botón para reiniciar el juego

                # Afegeix una vista per a aquesta ruta
                self.page.views.append(
                    ft.View(
                        f"/{number}",
                        [
                            ft.AppBar(title=ft.Text(f"Número {number}"), bgcolor=ft.colors.SURFACE_VARIANT),
                            ft.Container(bgcolor=ft.colors.GREEN, width=100, height=100),
                            ft.ElevatedButton("Tornar", on_click=lambda _: self.main()),
                        ],
                    )
                )

            else:
                button.bgcolor = ft.colors.RED
                button.enabled = False  # Deshabilita el botón si el número no es correcto

                # Afegeix una vista per a aquesta ruta
                self.page.views.append(
                    ft.View(
                        f"/{number}",
                        [
                            ft.AppBar(title=ft.Text(f"Número {number}"), bgcolor=ft.colors.SURFACE_VARIANT),
                            ft.Container(bgcolor=ft.colors.RED, width=100, height=100),
                            ft.ElevatedButton("Tornar", on_click=lambda _: self.main()),
                        ],
                    )
                )

            self.page.go(f"/{number}")

        return on_click

    def restart(self, args=None):
        # Reinicia el joc generant un nou número objectiu i tornant a la pàgina principal.
        self._target_number = random.randint(1, self._num_items)
        print(f"El nou número objectiu és: {self._target_number}")
        for btn in self.buttons:
            btn.enabled = True
            btn.bgcolor = ft.colors.AMBER
        self.main()

if __name__ == "__main__":
    app = App(25)
    app.main()
