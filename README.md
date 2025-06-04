# Comparación: Sistema Multiagente (JADE) vs. Modelo Tradicional

## Requisitos previos

- Java JDK 11 o superior
- Visual Studio Code
- Extensiones de Java para VS Code
- Python 3 (opcional, para generación/análisis de datos)
- JADE (descargar jade.jar y colocar en `/sma`)

## Estructura del proyecto

```
/Sistema MultiaGente
│
├── sma/
│   ├── jade.jar
│   ├── MainAgente.java
│   ├── AgenteRecolector.java
│   ├── AgenteAnalizador.java
│   ├── resultados_sma.csv
│
├── tradicional/
│   ├── AnalizadorTradicional.java
│   ├── resultados_tradicional.csv
│
├── datos/
│   ├── datos1.csv
│   ├── generar_datos.py
│
├── documentacion/
│   ├── metodologia.txt
│   ├── capturas/
│
├── README.md
```

## 1. Generar datos sintéticos

Abre terminal en VS Code y ejecuta:

```sh
cd datos
python generar_datos.py
```

## 2. Compilar y ejecutar el modelo tradicional

```sh
cd tradicional
javac AnalizadorTradicional.java
java AnalizadorTradicional > resultados_tradicional.csv
```

## 3. Compilar y ejecutar el sistema multiagente (JADE)

```sh
cd sma
javac -cp .;jade.jar *.java
java -cp .;jade.jar MainAgente > resultados_sma.csv
```

## 4. Analizar resultados

Puedes abrir los archivos `.csv` en Excel, Google Sheets o analizarlos con Python.

Ejemplo en Python:

```python
import pandas as pd
import matplotlib.pyplot as plt

df1 = pd.read_csv('../sma/resultados_sma.csv')
df2 = pd.read_csv('../tradicional/resultados_tradicional.csv')

# ...análisis y gráficos...
```

## 5. Documentación

- Guarda capturas de pantalla en `/documentacion/capturas/`.
- Describe el proceso en `/documentacion/metodologia.txt`.

---
