from PySide6.QtWidgets import (
    QApplication,
    QMainWindow,
    QWidget,
    QFormLayout,
    QLabel,
    QLineEdit,
    QSpinBox,
    QDoubleSpinBox,
    QPushButton
)


class VentanaPrincipal(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("Layout formulario")

        layout_formulario = QFormLayout()
        componente_principal = QWidget()
        componente_principal.setLayout(layout_formulario)
        self.setCentralWidget(componente_principal)

        layout_formulario.addRow(QLabel("usuari: "), QLineEdit())
        layout_formulario.addRow(QLabel("contrasenya: "), QLineEdit())
        layout_formulario.addRow(QPushButton("login"))

    def login(self):
        pass
app = QApplication([])

ventana = VentanaPrincipal()
ventana.show()

app.exec()