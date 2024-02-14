import os
import bcrypt
from PySide6.QtSql import QSqlDatabase, QSqlQuery
from pytestqt.qt_compat import qt_api
from practica2.practica2 import CreateUserDialog

def test_insercion_usuario(qtbot):
    ruta_db = 'test_usuarios.db'
    ventana_login = CreateUserDialog(ruta_db)
    ventana_login.create_database()
    qtbot.addWidget(ventana_login)

    ventana_login.user.setText('usuario_prueba')
    ventana_login.password.setText('contraseña_prueba')
    ventana_login.btn_create.click()

    assert ventana_login.db.open()
    consulta = QSqlQuery()
    consulta.prepare("SELECT * FROM users WHERE user = ?")
    consulta.addBindValue('usuario_prueba')
    consulta.exec_()
    assert consulta.next()
    contraseña_almacenada = consulta.value(1)
    assert bcrypt.checkpw('contraseña_prueba'.encode('utf-8'), contraseña_almacenada.encode('utf-8'))

    os.remove(ruta_db)