import flet as ft

class Task(ft.UserControl):
    def __init__(self, task_name, todo_app):
        super().__init__()
        self._completed = False
        self._favorite = False
        self.task_name = task_name
        self.todo_app = todo_app

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
        self.update()

    def status_changed(self, e):
        self.completed = self.display_task.value

    def favorite_clicked(self, e):
        self.favorite = not self.favorite

    def build(self):
        self.display_task = ft.Checkbox(
            value=self.completed, label=self.task_name, on_change=self.status_changed
        )
        self.edit_name = ft.TextField(expand=1)

        self.favorite_icon = ft.IconButton(
            icon=ft.icons.STAR_BORDER,
            tooltip="Favoritos",
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
                            tooltip="Editar tarea",
                            on_click=self.edit_clicked,
                        ),
                        ft.IconButton(
                            ft.icons.DELETE_OUTLINE,
                            tooltip="Eliminar tarea",
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
                    tooltip="Actualizar tarea",
                    on_click=self.save_clicked,
                ),
            ],
        )
        return ft.Column(controls=[self.display_view, self.edit_view])

    def edit_clicked(self, e):
        self.edit_name.value = self.display_task.label
        self.display_view.visible = False
        self.edit_view.visible = True
        self.edit_name.focus()
        self.update()

    def save_clicked(self, e):
        self.display_task.label = self.edit_name.value
        self.display_view.visible = True
        self.edit_view.visible = False
        self.update()

    def delete_clicked(self, e):
        def close_dlg(e):
            dlg_modal.open = False
            if e.source.text == "Yes":
                self.todo_app.task_delete(self)
            self.update()

        dlg_modal = ft.AlertDialog(
            modal=True,
            title=ft.Text("Please confirm"),
            content=ft.Text("Do you really want to delete this task?"),
            actions=[
                ft.TextButton("Yes", on_click=close_dlg),
                ft.TextButton("No", on_click=close_dlg),
            ],
            actions_alignment=ft.MainAxisAlignment.END,
        )
        dlg_modal.open = True
        self.update()
        def favorite_clicked(self, e):
            self.favorite = not self.favorite


class TodoApp(ft.UserControl):
    def build(self):
        self.new_task = ft.TextField(
            hint_text="What needs to be done?", on_submit=self.add_clicked, expand=True
        )
        self.tasks = ft.Column()
        self.items_left = ft.Text("0 items left")
        return ft.Column(
            width=600,
            controls=[
                ft.Row(
                    [ft.Text(value="Todos", style=ft.TextThemeStyle.HEADLINE_MEDIUM)],
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
                        self.tasks,
                    ],
                ),
            ],
        )

    def add_clicked(self, e):
        if self.new_task.value:
            task = Task(self.new_task.value, self)
            self.tasks.controls.append(task)
            self.new_task.value = ""
            self.new_task.focus()
            self.update()

    def task_delete(self, task):
        self.tasks.controls.remove(task)
        self.update()


def main(page: ft.Page):
    page.title = "ToDo App"
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER
    page.scroll = ft.ScrollMode.ADAPTIVE
    page.add(TodoApp())


ft.app(main)
