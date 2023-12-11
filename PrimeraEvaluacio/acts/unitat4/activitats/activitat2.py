from PySide6.QtWidgets import QApplication,QMainWindow, QWidget, QPushButton, QGridLayout, QHBoxLayout, QVBoxLayout


class layouts(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Layouts anidados")
        layout_principal = QHBoxLayout()
        componente_principal = QWidget()
        componente_principal.setLayout(layout_principal)
        self.setCentralWidget(componente_principal)
    
        layout_vertical = QVBoxLayout()
        layout_vertical.addWidget(QPushButton('Uno'))
        layout_vertical.addWidget(QPushButton('Dos'))
        layout_vertical.addWidget(QPushButton('Tres'))
        layout_vertical.addWidget(QPushButton('Cuatro'))
        
        layout_horitzontal = QHBoxLayout()
        layout_horitzontal.addWidget(QPushButton('Uno'))
        layout_horitzontal.addWidget(QPushButton('Dos'))
        layout_horitzontal.addWidget(QPushButton('Tres'))
        layout_horitzontal.addWidget(QPushButton('Cuatro'))

        layout_principal.addLayout(layout_vertical)
        layout_principal.addLayout(layout_horitzontal)
        
if __name__ == "__main__":
    # Cada aplicació serà una sola instància de QApplication.
    app = QApplication([])
    # Creem un objecte Finestra.
    finestra1 = layouts()
    # Mostrem la Finestra, per defecte els controls estan ocults.
    finestra1.show()
    # Iniciem el bucle d'esdeveniments.
    app.exec()