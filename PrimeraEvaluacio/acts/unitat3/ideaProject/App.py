import flet as ft
import os.path
from views.login import Login
from views.gente import Gente
from views.register import Register
from views.timeline import Timeline
from views.kurigram import Kurigram
import json

import os
import json

class App:
    def __init__(self, page):
        self.page = page
        self.user = User() 
        self.views = {
            '/': ft.View(
                route='/',
                controls=[
                    Kurigram(self.page, self.user.data)
                ]
            ),
            '/login': ft.View(
                route='/login',
                controls=[
                    Login(self.page)
                ]
            ),

        }

    def route_change(self, route):
        print(self.page.route)
        self.page.views.clear()
        self.page.views.append(self.views[self.page.route])

    def run(self):
        self.page.on_route_change = self.route_change
        self.page.go('/')

class User:
    def __init__(self):
        self._data = None

    @property
    def data(self):
        if self._data is None:
            with open(os.path.join(os.path.dirname(__file__),'views/datos.json'), 'r') as f:
                self._data = json.load(f)
        return self._data

    @data.setter
    def data(self, value):
        self._data = value
        with open(os.path.join(os.path.dirname(__file__),'views/datos.json'), 'w') as f:
            json.dump(self._data, f)

class Events:
    def __init__(self):
        self._data = None

    @property
    def data(self):
        if self._data is None:
            with open(os.path.join(os.path.dirname(__file__),'views/events.json'), 'r') as f:
                self._data = json.load(f)
        return self._data

    @data.setter
    def data(self, value):
        self._data = value
        with open(os.path.join(os.path.dirname(__file__),'views/events.json'), 'w') as f:
            json.dump(self._data, f)

class Posts:
    def __init__(self):
        self._data = None

    @property
    def data(self):
        if self._data is None:
            with open(os.path.join(os.path.dirname(__file__),'views/posts.json'), 'r') as f:
                self._data = json.load(f)
        return self._data

    @data.setter
    def data(self, value):
        self._data = value
        with open(os.path.join(os.path.dirname(__file__),'views/posts.json'), 'w') as f:
            json.dump(self._data, f)

class Data:
    def __init__(self):
        self._data = None

    @property
    def data(self):
        if self._data is None:
            with open(os.path.join(os.path.dirname(__file__),'views/data.json'), 'r') as f:
                self._data = json.load(f)
        return self._data

    @data.setter
    def data(self, value):
        self._data = value
        with open(os.path.join(os.path.dirname(__file__),'views/data.json'), 'w') as f:
            json.dump(self._data, f)



if __name__ == "__main__":
    def main(page: ft.Page):
        app = App(page)
        app.run()

    ft.app(target=main, view='web_browser')

