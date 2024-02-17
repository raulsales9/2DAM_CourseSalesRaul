from PySide6.QtWidgets import (
    QMainWindow, QApplication, QDialog, QDialogButtonBox, QVBoxLayout, QLabel, QHBoxLayout,
    QPushButton, 
)
from PySide6.QtCore import QTranslator,QLibraryInfo

class DialogoPersonalizado(QDialog):
    def __init__(self, parent=None):
        super().__init__(parent)

        self.setWindowTitle("Dialogo personalizado")

        #self.botonok = QDialogButtonBox.Ok 
        #self.botoncancel = QDialogButtonBox.Cancel
        self.botonok = QPushButton("ok")
        self.botonok.clicked.connect(self.button_clicked)
        self.botoncncel = QPushButton("cancel")
        self.botoncncel.clicked.connect(self.button_clicked)
        #botones = self.botonok | self.botoncancel
        #botones = self.botonok | self.botoncncel
        #self.caja_botones = QDialogButtonBox(botones)
        #self.caja_botones.accepted.connect(self.accept)
        #self.caja_botones.rejected.connect(self.reject)
        self.layout_linea = QHBoxLayout()
        self.layout_dialogo = QVBoxLayout()
        self.layout_dialogo.addWidget(
            QLabel("Estás seguro de querer realizar esta acción?"))
        self.layout_linea.addWidget(self.botonok)
        self.layout_linea.addWidget(self.botoncncel)
        self.setLayout(self.layout_dialogo)
        self.layout_dialogo.addWidget(self.layout_linea)
        
        
        self.result = False
        
    def button_clicked(self):
        resultat = self.sender()
        if resultat == self.botonok:
            self.result = True
            print("click accept")
        else:
            print("click decline")
        self.hide()
        #return super().accept()

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Aplicación con diálogo personalizado")

        boton = QPushButton("Haz clic para que el dialogo aparezca")
        boton.clicked.connect(self.mostrar_dialogo)
        self.setCentralWidget(boton)

    def mostrar_dialogo(self):
        print("Clic recibido, se mostrará el dialogo.")
        ventana_dialogo = DialogoPersonalizado(self)
        ventana_dialogo.setWindowTitle("Ventana de dialogo personalizado")
        # 1 si s'executa accept
        # 0 si s'executa reject
        resultado = ventana_dialogo.exec()
        #if resultado:
        #    print("Aceptada")
        #else:
        #    print("Cancelada")
            
    
def carregar_traductor(self, app):
    translator = QTranslator(app)
    translations = QLibraryInfo.location(QLibraryInfo.TranslationsPath)
    translator.load("qt_ca", translations)
    app.installTranslator(translator)


app = QApplication([])

ventana_principal = VentanaPrincipal()
ventana_principal.show()

app.exec()