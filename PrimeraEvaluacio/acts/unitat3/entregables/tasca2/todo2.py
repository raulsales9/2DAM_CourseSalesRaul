import flet as ft

# Classe per a gestionar les tasques destacades
class Destacades(ft.UserControl):
    def __init__(self):
        super().__init__()
        self.task_list = []

    # Mètode per a afegir una tasca a la llista
    def add_task(self, task):
        self.task_list.append(task)
        self.update()

    # Mètode per a eliminar una tasca de la llista
    def remove_task(self, task):
        self.task_list.remove(task)
        self.update()

    # Mètode per a actualitzar la llista de tasques
    def update(self):
        new_controls = []
        for task in self.task_list:
            if not task.completed: 
                new_controls.append(task.build())
        self.build().controls = new_controls

    # Mètode per a construir la llista de tasques
    def build(self):
        return ft.Column(controls=[task.build() for task in self.task_list])

# Classe per a gestionar una tasca individual
class Task(ft.UserControl):
    def __init__(self, task_name, task_delete, task_status_change, highlighted_tasks):
        super().__init__()
        self._completed = False
        self._favorite = False
        self.task_name = task_name
        self.task_delete = task_delete
        self.task_status_change = task_status_change
        self.highlighted_tasks = highlighted_tasks

    @property
    def completed(self):
        return self._completed

    @completed.setter
    def completed(self, value):
        self._completed = value
        self.display_task.value = self._completed
        self.update()

    @property
    def favorite(self):
        return self._favorite

    @favorite.setter
    def favorite(self, value):
        self._favorite = value
        self.favorite_icon.icon = ft.icons.STAR if self._favorite else ft.icons.STAR_BORDER
        if self._favorite:
            self.highlighted_tasks.add_task(self)
        else:
            self.highlighted_tasks.remove_task(self)
        self.update()

    # Mètode per a canviar l'estat de la tasca
    def status_changed(self, e):
        self.completed = self.display_task.value
        self.task_status_change(self)

    # Mètode per a marcar la tasca com a favorita
    def favorite_clicked(self, e):
        self.favorite = not self.favorite

    # Mètode per a construir la tasca
    def build(self):
        self.display_task = ft.Checkbox(
            value=self.completed, label=self.task_name, on_change=self.status_changed
        )
        self.edit_name = ft.TextField(expand=1)

        self.favorite_icon = ft.IconButton(
            icon=ft.icons.STAR_BORDER,
            tooltip="Favorits",
            on_click=self.favorite_clicked,
        )

        self.display_view = ft.Row(
            alignment=ft.MainAxisAlignment.SPACE_BETWEEN,
            vertical_alignment=ft.CrossAxisAlignment.CENTER,
            controls=[
                self.display_task,
                ft.Row(
                    spacing=0,
                    controls=[
                        self.favorite_icon,
                        ft.IconButton(
                            icon=ft.icons.CREATE_OUTLINED,
                            tooltip="Editar tasca",
                            on_click=self.edit_clicked,
                        ),
                        ft.IconButton(
                            ft.icons.DELETE_OUTLINE,
                            tooltip="Eliminar tasca",
                            on_click=self.delete_clicked,
                        ),
                    ],
                ),
            ],
        )

        self.edit_view = ft.Row(
            visible=False,
            alignment=ft.MainAxisAlignment.SPACE_BETWEEN,
            vertical_alignment=ft.CrossAxisAlignment.CENTER,
            controls=[
                self.edit_name,
                ft.IconButton(
                    icon=ft.icons.DONE_OUTLINE_OUTLINED,
                    icon_color=ft.colors.GREEN,
                    tooltip="Actualitzar tasca",
                    on_click=self.save_clicked,
                ),
            ],
        )
        return ft.Column(controls=[self.display_view, self.edit_view])

    # Mètode per a editar la tasca
    def edit_clicked(self, e):
        self.edit_name.value = self.display_task.label
        self.display_view.visible = False
        self.edit_view.visible = True
        self.edit_name.focus()
        self.update()

    # Mètode per a guardar els canvis realitzats en la tasca
    def save_clicked(self, e):
        self.display_task.label = self.edit_name.value
        self.display_view.visible = True
        self.edit_view.visible = False
        self.update()

    # Mètode per a eliminar la tasca
    def delete_clicked(self, e):
        def close_dlg(e):
            dlg_modal.open = False
            if e.source.text == "Sí":
                self.todo_app.task_delete(self)
            self.update()

        dlg_modal = ft.AlertDialog(
            modal=True,
            title=ft.Text("Si us plau, confirma"),
            content=ft.Text("Realment vols eliminar aquesta tasca?"),
            actions=[
                ft.TextButton("Sí", on_click=close_dlg),
                ft.TextButton("No", on_click=close_dlg),
            ],
            actions_alignment=ft.MainAxisAlignment.END,
        )
        dlg_modal.open = True
        self.update()

    # Mètode per a marcar la tasca com a favorita
    def favorite_clicked(self, e):
        self.favorite = not self.favorite

# Classe per a gestionar l'aplicació de tasques
class TodoApp(ft.UserControl):
    def build(self):
        self.new_task = ft.TextField(
            hint_text="Què cal fer?", on_submit=self.add_clicked, expand=True
        )
        self.highlighted_tasks = Destacades()
        self.tasks = ft.Column(controls=[self.highlighted_tasks]) 
        self.filter = ft.Tabs(
            scrollable=False,
            selected_index=0,
            on_change=self.tabs_changed,
            tabs=[ft.Tab(text="totes"), ft.Tab(text="destacades")],
        )

        self.pending_tasks = ft.Text("0 tasques pendents")
        return ft.Column(
            width=600,
            controls=[
                ft.Row(
                    [ft.Text(value="Totes", style=ft.TextThemeStyle.HEADLINE_MEDIUM)],
                    alignment=ft.MainAxisAlignment.CENTER,
                ),
                ft.Row(
                    controls=[
                        self.new_task,
                        ft.FloatingActionButton(
                            icon=ft.icons.ADD, on_click=self.add_clicked
                        ),
                    ],
                ),
                ft.Column(
                    spacing=25,
                    controls=[
                        self.filter,
                        self.tasks,
                        ft.Row(
                            alignment=ft.MainAxisAlignment.SPACE_BETWEEN,
                            vertical_alignment=ft.CrossAxisAlignment.CENTER,
                            controls=[
                                self.pending_tasks,
                                ft.OutlinedButton(
                                    text="Netejar completades", on_click=self.clear_clicked
                                ),
                            ],
                        ),
                    ],
                ),
            ],
        )

    # Mètode per a afegir una nova tasca
    def add_clicked(self, e):
        if self.new_task.value:
            task = Task(self.new_task.value, self.task_status_change, self.task_delete, self.highlighted_tasks)
            self.tasks.controls.append(task)
            self.new_task.value = ""
            self.new_task.focus()
            self.update()

    # Mètode per a canviar l'estat de la tasca
    def task_status_change(self, task):
        self.update()

    # Mètode per a eliminar una tasca
    def task_delete(self, task):
        self.tasks.controls.remove(task)
        self.update()

    # Mètode per a canviar les pestanyes
    def tabs_changed(self, e):
        self.update()

    # Mètode per a netejar les tasques completades
    def clear_clicked(self, e):
        for task in self.tasks.controls[:]:
            if task._completed:
                self.task_delete(task)

    # Mètode per a actualitzar la llista de tasques
    def update(self):
        status = self.filter.tabs[self.filter.selected_index].text
        count = 0
        for task in self.tasks.controls:
            task.visible = (
                status == "totes"
                or (status == "destacades" and task._completed == False)
            )
            if isinstance(task, Task) and not task._completed:
                count += 1
        self.pending_tasks.value = f"{count} Tasques Pendents"
        super().update()

# Mètode principal per a iniciar l'aplicació
def main(page: ft.Page):
    page.title = "Aplicació de Tasques"

    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE
    page.add(TodoApp())
ft.app(main)
