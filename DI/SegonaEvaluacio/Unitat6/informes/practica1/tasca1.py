import datapane as dp
import pandas as pd
import matplotlib.pyplot as plt
import os

dataframe_path = os.path.dirname(__file__)
dpath =  os.path.join(dataframe_path,"top_100_world_university_2024.csv")
dataframe = pd.read_csv(dpath)

plt.figure(figsize=(20,9))
dataframe['overall_score'].plot(kind='bar')
plt.title('Puntuaciones de las universidades')
plt.xlabel('Universidad')
plt.ylabel('Puntuación')
plt.savefig('grafico.png')

titulo = dp.HTML('<p style="font-size:30px;text-align:center;color:#ffffff;background-color:#4d4d4d;">Informe de universitats</p>')
tabla = dp.DataTable(dataframe)
texto = dp.Text('**Puedes descargar el fichero con los datos del informe.**')
fichero = dp.Attachment(file=dpath)
imagen = dp.Media(file='grafico.png')  
grupo_tabla = dp.Group(titulo, tabla, columns=1)
grupo_grafico = dp.Group(titulo, imagen, columns=1) 
selector = dp.Select(blocks=[texto, fichero])
pagina1 = dp.Page(title="Página 1", blocks=[grupo_tabla])
pagina2 = dp.Page(title="Página 2", blocks=[grupo_grafico, selector])
reporte = dp.Report(pagina1, pagina2)

# Guardar el informe
report_path = os.path.join("informe_universidades.html")
reporte.save(path=report_path, open=True)
