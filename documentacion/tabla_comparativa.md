# Tabla Comparativa: Sistema Multiagente vs. Modelo Tradicional

| Arquitectura   | Plataforma      | Características clave                        | Tiempo ejecución (ms) | Memoria (bytes) | Throughput (registros/ms) | Suma total | Eventos exitosos | Observaciones                |
|----------------|----------------|----------------------------------------------|-----------------------|-----------------|---------------------------|------------|------------------|------------------------------|
| Tradicional    | Java puro      | Monolítico, sin agentes, control central     | [1]                  | [2]             | [3]                       | [4]        | [5]              | [6]                         |
| Multiagente    | JADE (Java)    | Múltiples agentes, comunicación, distribuido | [7]                  | [8]             | [9]                       | [10]       | [11]             | [12]                        |

**Cómo llenar cada columna:**

- **[1] Tiempo ejecución (ms):**  
  Usa el valor reportado por el sistema tradicional (por ejemplo, el promedio de varias ejecuciones).

- **[2] Memoria (bytes):**  
  Si tienes medición de uso de memoria, colócala aquí (puedes usar herramientas como el Monitor de Tareas de Windows o `Runtime.getRuntime().totalMemory()` en Java). Si no, deja en blanco o pon "N/A".

- **[3] Throughput (registros/ms):**  
  Calcula:  
  ```
  Throughput = Total de registros procesados / Tiempo de ejecución (ms)
  ```
  Ejemplo: 1,000,000 registros / 400 ms = 2,500 registros/ms

- **[4] Suma total:**  
  Es la suma total calculada por el sistema (debe coincidir entre ambos si procesan los mismos datos).

- **[5] Eventos exitosos:**  
  Número de registros procesados correctamente (normalmente igual al total de registros si no hubo errores).

- **[6] Observaciones:**  
  Cualquier comentario relevante (ejemplo: "Procesamiento secuencial", "Sin errores", etc.).

- **[7] Tiempo ejecución (ms) Multiagente:**  
  Usa el mayor tiempo de los agentes (demora máxima) o el promedio, según lo que quieras comparar.

- **[8] Memoria (bytes) Multiagente:**  
  Igual que en el tradicional, si tienes la medición.

- **[9] Throughput Multiagente:**  
  ```
  Throughput = Total de registros procesados / Tiempo de ejecución (ms) (usa la demora máxima)
  ```

- **[10] Suma total Multiagente:**  
  Suma total reportada por todos los agentes (debe coincidir con el tradicional).

- **[11] Eventos exitosos Multiagente:**  
  Total de registros procesados por todos los agentes.

- **[12] Observaciones Multiagente:**  
  Ejemplo: "Procesamiento paralelo", "Mejora del X%", "Sin errores", etc.

**Recomendación:**  
- Llena la tabla con los promedios de varias ejecuciones para mayor representatividad.
- Si no tienes algún dato, puedes dejarlo en blanco o poner "N/A".
