# 5. Análisis de datos

## Técnicas estadísticas utilizadas

- Se calcularon medidas descriptivas (media, mínimo, máximo, desviación estándar) del tiempo de ejecución y throughput para ambos sistemas.
- Se realizó una comparación gráfica (barras) usando Python y matplotlib para visualizar diferencias de desempeño.
- Se calculó el porcentaje de mejora del sistema multiagente respecto al tradicional.

## Resultados

| Prueba | Tradicional (ms) | SMA (ms) | Mejora (%) |
|--------|------------------|----------|------------|
| 1      | 428              | 289      | 32,48      |
| 2      | 521              | 371      | 28,79      |
| 3      | 520              | 286      | 45,00      |
| 4      | 449              | 376      | 16,26      |
| 5      | 484              | 398      | 17,77      |
| 6      | 421              | 285      | 32,30      |
| 7      | 457              | 305      | 33,26      |
| 8      | 435              | 300      | 31,03      |
| 9      | 472              | 306      | 35,17      |
| 10     | 482              | 317      | 34,23      |

**Promedio mejora SMA:**  
≈ 30,83 %

## Interpretación

- El sistema multiagente mostró una mejora promedio del **30,83%** en eficiencia respecto al modelo tradicional, superando el umbral del 25% planteado en la hipótesis.
- Los resultados apoyan la hipótesis de que la arquitectura multiagente implementada con JADE es más eficiente que la arquitectura centralizada tradicional bajo escenarios equivalentes.
- La diferencia observada es consistente con la naturaleza distribuida y cooperativa de los SMA, que permite una mejor gestión de cargas y respuesta a eventos dinámicos.

## Gráfico comparativo

![Gráfico de comparación de tiempos](../datos/grafico_comparativo.png)

*Incluye aquí el gráfico generado por el script de análisis de Python o una captura de Excel/Sheets.*
