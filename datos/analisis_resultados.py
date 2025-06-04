import pandas as pd
import matplotlib.pyplot as plt

# Cargar resultados (ajusta los nombres de archivo si es necesario)
trad = pd.read_csv('../tradicional/resultados_tradicional.csv', names=['output'])
sma = pd.read_csv('../sma/resultados_sma.csv', names=['output'])

# Extraer tiempos de ejecución (busca la línea correspondiente)
trad_time = trad[trad['output'].str.contains('Tiempo')]['output'].str.extract(r'(\d+)').astype(int)
sma_time = sma[sma['output'].str.contains('Tiempo')]['output'].str.extract(r'(\d+)').astype(int)

# Crear gráfico comparativo
plt.bar(['Tradicional', 'Multiagente'], [trad_time.mean()[0], sma_time.mean()[0]])
plt.ylabel('Tiempo de ejecución (ms)')
plt.title('Comparación de tiempo de ejecución')
plt.show()
