import sys, os

from PySide6.QtWidgets import QApplication, QMainWindow, QWidget, QVBoxLayout, QLabel, QPushButton
from PySide6.QtCore import Qt
from PySide6.QtGui import QIcon


directorio_recursos = os.path.dirname(__file__)

class MainWindow(QMainWindow):

    def __init__(self):
        super(MainWindow, self).__init__()

        self.setWindowTitle("PySide6 executable")

        layout = QVBoxLayout()

        label = QLabel("Hola mundo!")
        label.setAlignment(Qt.AlignCenter)
        layout.addWidget(label)

        button = QPushButton("Cerrar")
        button.setIcon(QIcon(os.path.join(directorio_recursos, "resources", "close.png")))
        button.pressed.connect(self.close)
        layout.addWidget(button)

        widget = QWidget()
        widget.setLayout(layout)
        self.setCentralWidget(widget)

def main():
    app = QApplication(sys.argv)
    app.setWindowIcon(QIcon(os.path.join(directorio_recursos, "resources", "hand.png")))

    window = MainWindow()
    window.show()

    app.exec()

if __name__ == "__main__":
    main()