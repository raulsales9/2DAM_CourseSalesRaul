from PySide6.QtWidgets import QApplication, QLabel, QWidget, QLineEdit

class Finestra(QWidget):
    '''
    Classe Finestra, hereta de QWidget, component base.
    '''

    # Constructor de la classe Finestra
    def __init__(self):
        # Crida al constructor de la superclasse
        super().__init__()
        # Assignem el títol de la finestra
        self.setWindowTitle("finestra")
        # Creem una etiqueta amb la finestra com a parent.
        self.etiqueta2 = QLineEdit("Edit",self)
        self.etiqueta2.setGeometry(0,0,50,30)
        
        self.etiqueta1 = QLabel(self)
        self.etiqueta1.setGeometry(50,0,50,30)
        
        # self.etiqueta2.textChanged.connect(lambda :(self.etiqueta1.setText(self.etiqueta2.text())))
        # self.etiqueta2.textChanged.connect(self.etiqueta1.setText)
        self.etiqueta2.textChanged.connect(self.etiqueta1.setText)
        # fer utilitzant altra funcio propia que no siga connect

        def changeText():
            pass
        

if __name__ == "__main__":
    # Cada aplicació serà una sola instància de QApplication.
    app = QApplication([])
    # Creem un objecte Finestra.
    finestra1 = Finestra()
    # Mostrem la Finestra, per defecte els controls estan ocults.
    finestra1.show()
    # Iniciem el bucle d'esdeveniments.
    app.exec()