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
            # podria ser un Qmessagebox
            self.login_status.setText('Credenciales correctes')
            self.login_status.setStyleSheet("color: green")
            # continua endavant (hi ha que juntar el codi amb el editor??!)
            self.showMaximized()
        else:
            self.login_status.setText('Credencials incorrectes')
            self.login_status.setStyleSheet("color: red")
            QMessageBox.warning(self, "Error", "L'usuari o la contrasenya són incorrectes")

app = QApplication([])

ventana = VentanaPrincipal()
ventana.show()

app.exec_()
