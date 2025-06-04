# Para generar el archivo datos1.csv:
# 1. Abre la terminal en Visual Studio Code.
# 2. Ejecuta:
#    cd datos
#    python generar_datos.py
# Esto creará datos1.csv con 1,000,000 registros y 10 columnas en la carpeta /datos.
import csv
import random

# Genera un archivo datos1.csv con 1,000,000 registros y 10 columnas de datos
num_filas = 1_000_000
num_columnas = 10

with open("datos1.csv", "w", newline="") as f:
    writer = csv.writer(f)
    # Cabecera
    header = ["id"] + [f"valor{i+1}" for i in range(num_columnas)]
    writer.writerow(header)
    # Datos
    for i in range(num_filas):
        row = [i] + [random.randint(1, 100) for _ in range(num_columnas)]
        writer.writerow(row)
