import mysql.connector
import pyrebase

#mysql
mydb = mysql.connector.connect(
  host="localhost",
  port="3306",
  user="root",
  password="root",
  database="redsocial"
)

#firebase
firebaseConfig = {
  "apiKey": "AIzaSyB2gpUpAxU094pTK-xasruW-DGXj0otFMw",
  "authDomain": "kurigram-flet.firebaseapp.com",
  "projectId": "kurigram-flet",
  "storageBucket": "kurigram-flet.appspot.com",
  "messagingSenderId2": "392890686144",
  "appId": "1:392890686144:web:ceb29208aaf7273e0d6b25",
  "measurementId": "G-6PYPSRF8D4"
}