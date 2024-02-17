from PySide6.QtWidgets import QApplication, QDialog, QMainWindow, QPushButton

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Aplicación con diálogos")

        boton = QPushButton("Haz clic para que el dialogo aparezca")
        boton.clicked.connect(self.mostrar_dialogo)
        self.setCentralWidget(boton)

    def mostrar_dialogo(self):
        print("Clic recibido, se mostrará el dialogo.")
        ventana_dialogo = QDialog(self)
        ventana_dialogo.setWindowTitle("Ventana de dialogo")
        ventana_dialogo.exec()


app = QApplication([])

ventana_principal = VentanaPrincipal()
ventana_principal.show()

app.exec()