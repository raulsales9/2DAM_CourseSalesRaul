import os
import platform

from PySide6.QtCore import Qt
from PySide6.QtGui import QAction, QIcon, QKeySequence
from PySide6.QtWidgets import (
    QApplication, QMainWindow, QToolBar, QLabel, QDockWidget, QTextEdit)


class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle(
            "Ventana principal con menú, barra de herramientas " +
            " y barra de estado")

        barra_menus = self.menuBar()
        menu = barra_menus.addMenu("&Menu")
        ruta_a_icono = os.path.join(os.path.dirname(
            __file__), "images/console.png")
        accion = QAction(QIcon(ruta_a_icono), "Imprimir por consola", self)
        accion.setWhatsThis(
            "Al pulsar sobre el botón se imprimirá un texto por consola")
        accion.setStatusTip("Imprimir por consola")
        accion.setShortcut(QKeySequence("Ctrl+p"))
        accion.triggered.connect(self.imprimir_por_consola)
        menu.addAction(accion)

        barra_herramientas = QToolBar("Barra de herramientas 1")
        barra_herramientas.addAction(accion)
        self.addToolBar(barra_herramientas)

        self.barra_estado = self.statusBar()
        self.barra_estado.addPermanentWidget(QLabel(platform.system()))
        self.barra_estado.showMessage("Listo. Esperando acción ...")

    def imprimir_por_consola(self):
        print("fewuefhufreerifjgjiegrfjerfjierfiherfhuferhu")
        self.barra_estado.cleanMessage()


if __name__ == "__main__":
    app = QApplication([])
    #app.setAttribute(Qt.AA_dontShowIconsInMenus)
    ventana1 = VentanaPrincipal()
    ventana1.show()

    app.exec()