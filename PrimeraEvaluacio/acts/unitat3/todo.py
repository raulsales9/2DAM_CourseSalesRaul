import flet as ft


def main(page):
    def add_clicked(e):
        page.add(ft.Checkbox(label=new_task_textfield.value))
        new_task_textfield.value = ''
        button.disabled = True
        new_task_textfield.focus()
        page.update()
    
    def check_value(e):
        button.disabled = new_task_textfield.value == ''
        # if(new_task_textfield.value !=""):
        #    button.disabled = False
        #else:
        #    button.disabled = True
        button.update()

    new_task_textfield = ft.TextField(hint_text="Tasca pendent", width=300 ,on_change=check_value)
    button = ft.ElevatedButton("Afegir",on_click=add_clicked,disabled=True)
    page.add(ft.Row([new_task_textfield,button]))


ft.app(target=main)
