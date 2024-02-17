import sys
import bcrypt
from PySide6.QtWidgets import QApplication, QDialog, QFormLayout, QLineEdit, QPushButton, QLabel, QMainWindow
from PySide6.QtSql import QSqlDatabase, QSqlQuery

class CreateUserDialog(QDialog):
    def __init__(self, database_path):
        super().__init__()
        self.user = QLineEdit(self)
        self.user.setPlaceholderText("usuari")
        self.password = QLineEdit(self)
        self.password.setEchoMode(QLineEdit.Password)
        self.password.setPlaceholderText("contrasenya")
        self.btn_create = QPushButton('Create user', self)
        self.layout = QFormLayout(self)
        self.layout.addRow(QLabel("Usuari:"), self.user)
        self.layout.addRow(QLabel("Contrasenya:"), self.password)
        self.layout.addRow(self.btn_create)
        self.btn_create.clicked.connect(self.createUser)
        self.db = QSqlDatabase.addDatabase('QSQLITE')
        self.db.setDatabaseName(database_path)

    def createUser(self):
        if self.db.open():
            if (self.user.text() != '' and self.password.text() != ''):
                query = QSqlQuery()
                query.prepare("INSERT INTO users (user, password) VALUES (?, ?)")
                query.addBindValue(self.user.text())
                hashed_password = bcrypt.hashpw(self.password.text().encode('utf-8'), bcrypt.gensalt())
                query.addBindValue(hashed_password.decode('utf-8'))
                query.exec()
        self.close()

    def create_database(self):
        if self.db.open():
            query = QSqlQuery()
            query.exec_('CREATE TABLE IF NOT EXISTS users (user TEXT, password BLOB)')
            # Insert some users
            users = [("user1", "password1"), ("user2", "password2")]
            for user, password in users:
                hashed_password = bcrypt.hashpw(password.encode('utf-8'), bcrypt.gensalt())
                query.prepare("INSERT INTO users (user, password) VALUES (?, ?)")
                query.addBindValue(user)
                query.addBindValue(hashed_password.decode('utf-8'))
                query.exec_()

def main():    
    app = QApplication(sys.argv)
    login = CreateUserDialog('users.db')
    login.create_database()
    login.show()
    sys.exit(app.exec())

if __name__ == '__main__':
    main()