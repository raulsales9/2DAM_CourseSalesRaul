import flet as ft


def main(page, self):
    self.featured = False
    
    @property
    self.featured(self):
        return self._featured

    @featured.setter
    def featuredç(self, value):
        self._featured = value

    def featured_changed(self, e):
        self.featured = not self.featured
        
    def add_clicked(e):
        page.add(ft.Checkbox(label=new_task_textfield.value))
        new_task_textfield.value = ''
        button.disabled = True
        new_task_textfield.focus()
        page.update()
    
    def check_value(e):
        button.disabled = new_task_textfield.value == ''
        button.update()

    new_task_textfield = ft.TextField(hint_text="Tasca pendent", width=300 ,on_change=check_value)
    button = ft.ElevatedButton("Afegir",on_click=add_clicked,disabled=True)
    page.add(ft.Row([new_task_textfield,button]))


ft.app(target=main)
