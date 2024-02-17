import datapane as dp
import pandas as pd
import matplotlib.pyplot as plt
import os
import sqlite3 as sql

connection = sql.connect('informes/practica2/chinook.db')
df1 = pd.read_sql("SELECT * FROM artists", connection)
""" json = [{"album":"Music of the Spheres","año":"2021"},{"album":"Higher Power","año":"2021"},{"album":"Everyday Life","año":"2019"},{"album":"Live in Buenos Aires","año":"2018"},{"album":"Love in Tokyo","año":"2018"},{"album":"Greatest Songs","año":"2018"},{"album":"Kaleidoscope EP","año":"2017"},{"album":"Hymn for the Weekend","año":"2016"},{"album":"A Head Full of Dreams","año":"2015"},{"album":"Ghost Stories Live 2014","año":"2014"}]
df2 = pd.read_json(json) """
df2 = pd.read_json("informes/practica2/coldplay_album.json")

url = "https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population"
df3_list = pd.read_html(url)
df3 = df3_list[0]  

report1 = dp.Report(dp.DataTable(df1)) 
report2 = dp.Report(dp.DataTable(df2)) 
report3 = dp.Report(dp.DataTable(df3)) 

report3.save(path='informes/practica2/listatpaisos.html', name='Informe de Población por Países', open=True)

