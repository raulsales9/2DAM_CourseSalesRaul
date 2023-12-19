from PySide6.QtWidgets import QMainWindow, QTextEdit, QAction, QFileDialog, QApplication
from PySide6.QtGui import QIcon
import sys
import os

class Editor(QMainWindow):
    def __init__(self):
        super().__init__()

        self.text = QTextEdit(self)
        self.setCentralWidget(self.text)

        self.open_action = QAction(QIcon('open.png'), 'Obrir', self)
        self.open_action.setShortcut('Ctrl+O')
        self.open_action.triggered.connect(self.open_file)

        self.save_action = QAction(QIcon('save.png'), 'Guardar', self)
        self.save_action.setShortcut('Ctrl+S')
        self.save_action.triggered.connect(self.save_file)

        self.exit_action = QAction(QIcon('exit.png'), 'Sortir', self)
        self.exit_action.setShortcut('Ctrl+Q')
        self.exit_action.triggered.connect(self.close)

        self.toolbar = self.addToolBar('Toolbar')
        self.toolbar.addAction(self.open_action)
        self.toolbar.addAction(self.save_action)

        self.menu = self.menuBar()
        self.file_menu = self.menu.addMenu('Fitxer')
        self.file_menu.addAction(self.open_action)
        self.file_menu.addAction(self.save_action)
        self.file_menu.addAction(self.exit_action)

    def open_file(self):
        options = QFileDialog.Options()
        filename, _ = QFileDialog.getOpenFileName(self, 'Obrir', '', 'Text Files (*.txt)', options=options)
        if filename:
            with open(filename, 'r') as f:
                self.text.setText(f.read())

    def save_file(self):
        options = QFileDialog.Options()
        filename, _ = QFileDialog.getSaveFileName(self, 'Guardar', '', 'Text Files (*.txt)', options=options)
        if filename:
            with open(filename, 'w') as f:
                f.write(self.text.toPlainText())

if __name__ == '__main__':
    app = QApplication(sys.argv)
    editor = Editor()
    editor.show()
    sys.exit(app.exec())
