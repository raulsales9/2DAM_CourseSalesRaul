from PySide6.QtWidgets import QMainWindow, QTextEdit, QFileDialog, QApplication, QMessageBox, QToolBar
from PySide6.QtGui import QIcon, QAction, QKeySequence
import sys
import os

class Editor(QMainWindow):
    def __init__(self):
        super().__init__()

        self.text = QTextEdit(self)
        self.setCentralWidget(self.text)
        # imatges
        save = os.path.join(os.path.dirname(__file__), "assets/img/files-and-folders.png")
        close = os.path.join(os.path.dirname(__file__), "assets/img/cerrar-sesion.png")
        open = os.path.join(os.path.dirname(__file__), "assets/img/disquete.png")
        self.open_action = QAction(QIcon(open), 'Obrir', self)
        #shortcut per a tecles especials i controls
        self.open_action.setShortcut('Ctrl+O')
        self.open_action.triggered.connect(self.open_file)
        self.bar = QToolBar("Barra")
        #self.bar.setToolButtonStyle(Qt.ToolButtonTextUnderIcon)
        self.save_action = QAction(QIcon(save), 'Guardar', self)
        #self.save_action.setToolTip("Guardar arxiu")
        self.save_action.setShortcut('Ctrl+S')
        self.save_action.triggered.connect(self.save_file)

        self.exit_action = QAction(QIcon(close), 'Sortir', self)
        self.exit_action.setShortcut('Ctrl+Q')
        self.exit_action.triggered.connect(self.close_program)

        self.toolbar = self.addToolBar('Toolbar')
        self.toolbar.addAction(self.open_action)
        self.toolbar.addAction(self.save_action)

        self.menu = self.menuBar()
        self.file_menu = self.menu.addMenu('Fitxer')
        self.file_menu.addAction(self.open_action)
        self.file_menu.addAction(self.save_action)
        self.file_menu.addAction(self.exit_action)

    def open_file(self): 
        if self.text.document().isModified():
            tanca = QMessageBox.question(self, "Eixir", "Estas segur que vols perdre el teu treball?", QMessageBox.Save | QMessageBox.Discard | QMessageBox.Cancel)
            if tanca == QMessageBox.Save:
                self.save_file()
            elif tanca == QMessageBox.Discard:
                self.text.clear()
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
                
    def close_file(self):
        if self.text.document().isModified():
            tanca = QMessageBox.question(self,"Eixir", "Estas segur que vols perdre el teu treball?", QMessageBox.Save | QMessageBox.Discard | QMessageBox.Cancel)
            if tanca == QMessageBox.Save:
                self.save_file()
            elif tanca == QMessageBox.Discard:
                self.close()
        else:
            self.close()
    
    def close_program(self):
        if self.text.document().isModified():
            tanca = QMessageBox.question(self,"Eixir", "Estas segur que vols perdre el teu treball?", QMessageBox.Save | QMessageBox.Discard | QMessageBox.Cancel)
            if tanca == QMessageBox.Save:
                self.save_file()
                self.close()
            elif tanca == QMessageBox.Discard:
                self.close()
        else:
            self.close()

if __name__ == '__main__':
    app = QApplication(sys.argv)
    editor = Editor()
    editor.show()
    sys.exit(app.exec())
