from PySide6.QtWidgets import (
    QApplication,
    QMainWindow,
    QWidget,
    QFormLayout,
    QLabel,
    QLineEdit,
    QPushButton,
    QMessageBox
)
from PySide6.QtGui import QColor

class VentanaPrincipal(QMainWindow):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("Layout formulario")

        layout_formulario = QFormLayout()
        componente_principal = QWidget()
        componente_principal.setLayout(layout_formulario)
        self.setCentralWidget(componente_principal)

        self.name = QLineEdit()
        self.name.setPlaceholderText('Usuario')
        layout_formulario.addRow(QLabel("usuari: "), self.name)

        self.password = QLineEdit()
        self.password.setPlaceholderText('Contraseña')
        self.password.setEchoMode(QLineEdit.Password)
        layout_formulario.addRow(QLabel("contrasenya: "), self.password)

        btn = QPushButton('login')
        btn.clicked.connect(self.check_passwd)
        layout_formulario.addRow(btn)

        self.login_status = QLabel()
        layout_formulario.addRow(self.login_status)

    def check_passwd(self):
        if self.name.text() == 'admin' and self.password.text() == 'admin':
            self.login_status.setText('Credenciales correctas')
            self.login_status.setStyleSheet("color: green")
        else:
            self.login_status.setText('Credenciales incorrectas')
            self.login_status.setStyleSheet("color: red")

app = QApplication([])

ventana = VentanaPrincipal()
ventana.show()

app.exec_()
