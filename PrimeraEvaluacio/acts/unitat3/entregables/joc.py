import flet as ft
import random

class App:
    def __init__(self, num_items: int) -> None:
        self.num_items = num_items
        self.target_number = random.randint(1, num_items)
        print(f"El número objetivo es: {self.target_number}")
        ft.app(target=self.main)

    def main(self, page: ft.Page):
        self.page = page
        self.buttons = [] 
        row = ft.Row(
            wrap=True,
            spacing=10,
            run_spacing=10,
            # generem 30 items
            # controls=self.items(30)
            # pasem de forma dinamica el numero d'items mitjançant App(25), per lo tant aquesta manera no hauriem d'establir cada vegada la cantitat d'items
            controls=self.items(self.num_items),
            width=page.window_width,
        )

        self.page.add(
            ft.Column(
                [
                    ft.Text(
                        "Change the row width to see how child items wrap onto multiple rows:"
                    ),
                    row,
                ]
            )
        )

    def items(self, count):
        items = []
        for i in range(1, count + 1):
            button = ft.Container(
                content=ft.Text(value=str(i)),
                alignment=ft.alignment.center,
                width=50,
                height=50,
                bgcolor=ft.colors.AMBER,
                border_radius=ft.border_radius.all(5),
            )
            button.on_click = self.check_number(i)
            items.append(button)
            self.buttons.append(button) 
        return items

    def check_number(self, number: int):
        def on_click(args):
            # print(self.control.content.value)
            if number == self.target_number:
                self.page.clean()
                self.page.add(ft.Text("Has trobat el número!"))
                for button in self.buttons:
                    button.enabled = False
                self.page.add(ft.ElevatedButton("Reiniciar", on_click=self.restart))
            else:
                
                # Cambia la ruta de la página al número clicado
                self.page.go(f"/{number}")
                self.page.add(ft.Text(f"El número {number} no és el número buscat."))
                # Añade una vista para esta ruta
                self.page.views.append(
                    ft.View(
                        f"/{number}",
                        [
                            ft.AppBar(title=ft.Text(f"Número {number}"), bgcolor=ft.colors.SURFACE_VARIANT),
                            ft.ElevatedButton("Volver", on_click=lambda _: self.page.go("/")),
                        ],
                    )
                )
        return on_click

    def restart(self, args=None):
        self.page.clean()
        self.target_number = random.randint(1, self.num_items)
        print(f"El nuevo número objetivo es: {self.target_number}") 
        self.main(self.page)

if __name__ == "__main__":
    app = App(25)