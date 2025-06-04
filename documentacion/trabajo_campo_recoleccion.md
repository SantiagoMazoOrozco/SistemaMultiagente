## ¿Cómo comenzar a recolectar los datos?

1. **Genera los datos sintéticos**  
   Abre la terminal en Visual Studio Code y ejecuta:
   ```sh
   cd datos
   python generar_datos.py
   ```
   Esto creará archivos de datos de prueba en la carpeta `/datos`.

2. **Compila y ejecuta el modelo tradicional**  
   En la terminal:
   ```sh
   cd ../tradicional
   javac AnalizadorTradicional.java
   java AnalizadorTradicional > resultados_tradicional.csv
   ```

3. **Compila y ejecuta el sistema multiagente (JADE)**  
   En la terminal:
   ```powershell
   javac -cp ".;jade.jar" *.java
   java -cp ".;jade.jar" MainAgente > resultados_sma.csv
   ```
   - **Importante:**  
     Si usas PowerShell, asegúrate de escribir el comando exactamente como arriba, con comillas dobles alrededor de `. ; jade.jar`.
   - Si usas Git Bash o Linux, usa `:` en vez de `;`:
   ```sh
   javac -cp ".:jade.jar" *.java
   java -cp ".:jade.jar" MainAgente > resultados_sma.csv
   ```
   - Si ves el error `error: no source files`, revisa que estés en la carpeta correcta y que los archivos tengan extensión `.java`.
   - Si ves el error `jade.jar : El término 'jade.jar' no se reconoce...`, ignóralo si solo aparece como sugerencia y no como parte de la compilación.  
     **Nunca ejecutes `jade.jar` como comando, solo como parte del parámetro `-cp`.**

4. **Verifica los archivos de resultados**  
   - Abre el archivo `resultados_sma.csv` en la carpeta `sma` y revisa que contenga la salida esperada (mensajes de los agentes y métricas).
   - **Si el archivo solo contiene mensajes como:**
     ```
     Modo de ejecución: SMA
     AgenteRecolector iniciado.
     AgenteAnalizador iniciado.
     Recolector: datos enviados.
     Analizador: datos procesados.
     ```
     **y no incluye métricas numéricas (ej. tiempo de ejecución, throughput, etc.), debes modificar el código de tus agentes para imprimir esas métricas.**
   - Por ejemplo, en `MainAgente.java` o en los agentes, agrega:
     ```java
     long inicio = System.currentTimeMillis();
     // ...lógica de ejecución...
     long fin = System.currentTimeMillis();
     System.out.println("Tiempo de ejecución (ms): " + (fin - inicio));
     ```
   - Puedes hacer esto en el método `setup()` de cada agente o al finalizar la tarea principal.
   - **Después de modificar el código, vuelve a compilar y ejecutar el sistema multiagente para que la salida útil se guarde en `resultados_sma.csv`.**
   - Así obtendrás datos numéricos para tu análisis comparativo.

5. **Guarda capturas de pantalla**  
   - Toma capturas de la terminal mostrando la ejecución del sistema multiagente y el contenido de `resultados_sma.csv`.
   - Guarda las imágenes en `/documentacion/capturas/`.

6. **Continúa con el análisis de datos**  
   - Abre ambos archivos de resultados (`resultados_tradicional.csv` y `resultados_sma.csv`) en Excel, Google Sheets o con Python.
   - Calcula y compara las métricas (tiempo de ejecución, throughput, etc.).
   - Inserta gráficos y tablas en tu informe para mostrar la comparación.
   - Redacta tus conclusiones sobre la eficiencia y adaptabilidad de ambos enfoques.


---

# 4. Trabajo de campo y recolección de datos

## Evidencia del trabajo realizado

Se desarrollaron dos sistemas funcionales bajo condiciones controladas:
- **Modelo tradicional:** Implementado en Java, ejecuta el procesamiento de datos de forma monolítica.
- **Sistema multiagente:** Implementado en JADE, distribuye las tareas entre agentes autónomos.

### Generación y aplicación del instrumento
- Los datos de entrada se generaron sintéticamente usando el script `generar_datos.py` en la carpeta `/datos`, simulando cargas de trabajo reales con diferentes volúmenes y formatos.
- Ambos sistemas fueron ejecutados desde la terminal de Visual Studio Code, utilizando los mismos archivos de entrada para asegurar la comparabilidad.
- Se emplearon scripts de logging y redirección de salida para recolectar automáticamente métricas como tiempo de ejecución, uso de memoria y tasa de éxito, almacenando los resultados en archivos `.csv`.

### Evidencia documental
- Se adjuntan capturas de pantalla de la ejecución de ambos sistemas, el código fuente y los archivos de resultados en `/documentacion/capturas/`.
- Los archivos `resultados_tradicional.csv` y `resultados_sma.csv` contienen los datos recolectados para cada escenario de prueba.

## Calidad y pertinencia de los datos recolectados

- Los datos recolectados son objetivos, cuantitativos y replicables, ya que provienen de ejecuciones automatizadas bajo escenarios equivalentes.
- Las métricas seleccionadas (tiempo de ejecución, throughput, uso de recursos, tasa de éxito) son pertinentes para evaluar la eficiencia y adaptabilidad de cada arquitectura, en línea con la hipótesis y los objetivos del estudio.
- La metodología garantiza la validez y confiabilidad de los datos, permitiendo su análisis estadístico y la verificación de la hipótesis.

> **Nota:**  
> Puedes descargar `jade.jar` directamente desde la terminal de Visual Studio Code usando uno de estos comandos:
>
> **En PowerShell (Windows):**
> ```powershell
> Invoke-WebRequest -Uri "https://jade.tilab.com/dl.php?file=JADE-all-4.5.0.zip" -OutFile "jade.zip"
> Expand-Archive jade.zip -DestinationPath .
> Copy-Item ".\JADE-all-4.5.0\lib\jade.jar" .\
> ```
>
> **En Bash (Linux/Mac o Git Bash):**
> ```sh
> curl -L -o jade.zip "https://jade.tilab.com/dl.php?file=JADE-all-4.5.0.zip"
> unzip jade.zip
> cp JADE-all-4.5.0/lib/jade.jar .
> ```
> 
> Así puedes obtener `jade.jar` sin descargarlo manualmente desde el navegador.

> **Solución a errores comunes:**
>
> - **error: no source files**  
>   Asegúrate de que los archivos `MainAgente.java`, `AgenteRecolector.java` y `AgenteAnalizador.java` estén en la carpeta `sma`. Si no existen, crea o copia los archivos fuente allí.
>
> - **jade.jar : El término 'jade.jar' no se reconoce como nombre de un cmdlet...**  
>   Este error ocurre si escribes `jade.jar` solo en la terminal.  
>   Debes usar `jade.jar` únicamente como parte del parámetro `-cp` en el comando `javac` o `java`, nunca como un comando independiente.
>
> - **¿Cómo compilar correctamente?**  
>   Una vez que tengas los archivos `.java` y `jade.jar` en la carpeta `sma`, ejecuta:
>   ```powershell
>   javac -cp ".;jade.jar" *.java
>   ```
>   Si usas Git Bash o Linux, usa `:` en vez de `;`:
>   ```sh
>   javac -cp ".:jade.jar" *.java
>   ```
>   Luego ejecuta:
>   ```powershell
>   java -cp ".;jade.jar" MainAgente
>   ```
>   o en Bash:
>   ```sh
>   java -cp ".:jade.jar" MainAgente
>   ```

> **¿Qué debes hacer si ves estos errores?**
>
> 1. **Verifica que tienes los archivos fuente Java en la carpeta `sma`:**
>    - Deben estar presentes: `MainAgente.java`, `AgenteRecolector.java`, `AgenteAnalizador.java`.
>    - **Si estos archivos están presentes y aún ves `error: no source files`, revisa:**
>      - Que estés en la carpeta correcta (`sma`) al ejecutar el comando.
>      - Que los archivos tengan la extensión `.java` y no, por ejemplo, `.txt`.
>      - Que los nombres no tengan espacios ni errores de mayúsculas/minúsculas.
>      - Prueba el comando:
>        ```powershell
>        dir *.java
>        ```
>        para listar los archivos `.java` en la carpeta. Si no aparece ninguno, revisa el nombre y extensión de los archivos.
>      - Si aparecen, intenta compilar de nuevo:
>        ```powershell
>        javac -cp ".;jade.jar" *.java
>        ```
>
> Si sigues viendo `error: no source files`, revisa que estés en la carpeta correcta y que los archivos `.java` existan.
>
> - Si los archivos `MainAgente.java`, `AgenteRecolector.java` y `AgenteAnalizador.java` están en la carpeta `sma`:
>      1. Ejecuta en la terminal (dentro de la carpeta `sma`):
>         ```powershell
>         dir *.java
>         ```
>         Verifica que aparecen los tres archivos listados.
>      2. Asegúrate de que también está el archivo `jade.jar` en la misma carpeta.
>         - Ejecuta:
>         ```powershell
>         dir jade.jar
>         ```
>         **Si ves un error como "no existe", significa que `jade.jar` NO está en la carpeta.**
>         - Debes descargar y copiar `jade.jar` a la carpeta `sma` antes de poder compilar.
>         - **Si al extraer el `.zip` obtuviste una carpeta llamada `jade` (o similar), busca el archivo `jade.jar` dentro de ella:**
>         - Navega a la carpeta extraída y localiza el archivo en una ruta como:
>           ```
>           jade\lib\jade.jar
>           ```
>         - Copia manualmente el archivo `jade.jar` desde esa carpeta a tu carpeta de trabajo `sma`.
>         - Ejemplo en PowerShell (ajusta la ruta si es necesario):
>           ```powershell
>           Copy-Item ".\jade\lib\jade.jar" .\
>           ```
>         - Luego verifica de nuevo con:
>         ```powershell
>         dir jade.jar
>         ```
>         - **Cuando veas `jade.jar` listado (como ahora), ya puedes compilar:**
>         ```powershell
>         javac -cp ".;jade.jar" *.java
>         ```
>         - Si no hay errores, ejecuta:
>         ```powershell
>         java -cp ".;jade.jar" MainAgente
>         ```
>         - Si todo funciona, continúa con la recolección de datos y el resto del procedimiento descrito arriba.

   - **Nota:**  
     Si al ejecutar el sistema multiagente en PowerShell o la terminal de Visual Studio Code la terminal no termina automáticamente (queda esperando y debes presionar Ctrl+C), debes:
     - Modificar el código de tus agentes para que todos llamen a `doDelete()` al finalizar su tarea.
     - Asegurarte de que el **último agente** (por ejemplo, `AgenteAnalizador`) también llame a `System.exit(0);` después de `doDelete();`.
     - Así, la plataforma JADE se cerrará automáticamente y la terminal finalizará sin necesidad de Ctrl+C.
     - Vuelve a compilar y ejecutar el sistema multiagente para comprobar que la terminal termina sola cuando los agentes finalizan.

   - **Comando para compilar y ejecutar en la terminal de Visual Studio Code:**  
     1. Abre la terminal integrada (`Ctrl + ñ` o desde el menú Ver > Terminal).
     2. Navega a la carpeta `sma`:
        ```sh
        cd sma
        ```
     3. Compila todos los archivos Java:
        ```sh
        javac -cp ".;jade.jar" *.java
        ```
     4. Ejecuta el sistema multiagente y guarda la salida:
        ```sh
        java -cp ".;jade.jar" MainAgente > resultados_sma.csv
        ```
     - Si usas Linux o Git Bash, reemplaza `;` por `:`:
        ```sh
        javac -cp ".:jade.jar" *.java
        java -cp ".:jade.jar" MainAgente > resultados_sma.csv
        ```

## ¿Qué hacer si los resultados de eficiencia son iguales?

- **Esto es normal en pruebas simples:**  
  Si ambos sistemas (tradicional y multiagente) hacen exactamente la misma tarea de forma secuencial y en un solo hilo, y no hay comunicación ni concurrencia real entre agentes, el desempeño será muy similar.
- **La eficiencia de los SMA se evidencia cuando:**
  - Hay múltiples agentes trabajando en paralelo (concurrencia real).
  - Se distribuyen tareas complejas o independientes entre varios agentes.
  - Se simulan eventos dinámicos, fallos, adaptabilidad o escalabilidad.
  - El entorno es distribuido (varias máquinas o procesos).

### ¿Cómo puedes evidenciar ventajas de los SMA?

1. **Divide el procesamiento entre varios agentes**
   - Por ejemplo, crea varios agentes recolectores, cada uno procesando una parte diferente del archivo (división por rangos de líneas).
   - Mide el tiempo total cuando trabajan en paralelo.

2. **Simula tareas independientes**
   - Haz que cada agente procese un archivo diferente o una parte distinta de los datos.

3. **Aumenta la complejidad y el número de agentes**
   - Más agentes = más potencial de paralelismo y adaptabilidad.

4. **Simula eventos dinámicos**
   - Haz que algunos agentes fallen y se recuperen, o que cambien de tarea en tiempo real.

5. **Documenta la limitación**
   - Si solo usas un agente para cada tarea y todo es secuencial, **no esperes mejoras de eficiencia**. Explica esto en tu informe y sugiere que para tareas paralelizables o distribuidas, los SMA pueden ser superiores.

---

**Ejemplo para tu informe:**

> "En este experimento, ambos sistemas procesan los datos de manera secuencial y monohilo, por lo que no se observa una mejora significativa en eficiencia. Para evidenciar ventajas de los sistemas multiagente, es necesario diseñar tareas que puedan ejecutarse en paralelo o escenarios donde la adaptabilidad y la tolerancia a fallos sean relevantes."

---

**¿Quieres un ejemplo de cómo dividir el procesamiento entre varios agentes para ver mejoras?**

> **¿Cómo ejecutar correctamente el sistema multiagente?**
>
> 1. **Compila todos los archivos Java**  
>    En la terminal, dentro de la carpeta `sma`, ejecuta:
>    ```sh
>    javac -cp ".;jade.jar" *.java
>    ```
>    (En Linux/Git Bash usa `:` en vez de `;`)
>
> 2. **Ejecuta el sistema**  
>    Luego ejecuta:
>    ```sh
>    java -cp ".;jade.jar" MainAgente
>    ```
>    Así verás toda la salida en la terminal y los agentes trabajarán en paralelo si tu código está preparado para ello.
>
> - **Recuerda:**  
>   Siempre debes compilar antes de ejecutar si hiciste cambios en los archivos `.java`.

> **¿Por qué solo un agente procesa datos y los demás no?**
>
> - Si solo ves registros procesados en `Recolector0` y los demás muestran `0`, probablemente el archivo `datos1.csv` **no tiene 10 millones de registros** o **no está en la ruta correcta**.
> - Verifica que:
>   - El archivo `datos1.csv` está en la carpeta `datos` y contiene exactamente 10,000,000 líneas de datos (más la cabecera).
>   - El código de generación de datos no fue interrumpido y el archivo no está incompleto.
>   - Todos los agentes están leyendo el archivo correcto y no hay errores de lectura.
> - Si el archivo es correcto, revisa que el cálculo de los rangos en `MainAgente.java` y el procesamiento en `AgenteRecolector.java` sean consistentes con el número de líneas reales del archivo.
> - Puedes abrir el archivo en un editor de texto o usar un comando como `wc -l datos1.csv` (en Linux/Git Bash) para contar las líneas.
>
> **Solución rápida:**
> - Regenera el archivo `datos1.csv` asegurándote de que tenga 10 millones de registros.
> - Vuelve a ejecutar el sistema y verifica que cada agente procese aproximadamente 2.5 millones de registros.
> - Si el problema persiste, imprime en consola el valor de `lineaActual` en cada agente para depurar.

---

## ¿Cómo interpretar y presentar los resultados del SMA en Excel?

Al finalizar el procesamiento con el sistema multiagente (SMA), en tu archivo de resultados (por ejemplo, `resultados_sma.csv`), debes mostrar y luego reportar en Excel:

- **El promedio del tiempo de ejecución de los agentes** (puedes calcularlo sumando los tiempos de cada agente y dividiendo entre el número de agentes).
- **El total de registros procesados** (la suma de los registros procesados por todos los agentes).
- **La suma total de las sumas de todos los agentes** (la suma de los resultados parciales de cada agente).

Ejemplo de cómo debe verse la tabla resumen en Excel:

| Agente        | Suma total | Registros procesados | Tiempo de ejecución (ms) |
|---------------|------------|---------------------|--------------------------|
| Recolector0   | ...        | ...                 | ...                      |
| Recolector1   | ...        | ...                 | ...                      |
| Recolector2   | ...        | ...                 | ...                      |
| Recolector3   | ...        | ...                 | ...                      |
| **Totales**   | =SUM(B2:B5)| =SUM(C2:C5)         | =AVERAGE(D2:D5)          |

- En la última fila, usa las funciones de Excel para calcular la suma total, el total de registros y el promedio de tiempo.
- Así podrás comparar fácilmente estos valores con los del modelo tradicional.

## ¿Cómo interpretar los resultados del experimento SMA vs Tradicional?

- **Ahora sí tienes paralelismo real:**  
  Cada agente SMA (`Recolector0`, `Recolector1`, `Recolector2`, `Recolector3`) procesó 25,000 registros de un total de 100,000, y cada uno reporta su propio tiempo de ejecución.

### ¿Qué debes observar?

1. **Suma total y registros procesados**
   - La suma total de cada agente debe ser diferente, pero la suma de todas las sumas debe coincidir con la suma total del sistema tradicional.
   - La suma de los registros procesados por todos los agentes debe ser igual al total de registros del archivo.

2. **Tiempo de ejecución**
   - El tiempo de ejecución de cada agente es el tiempo que tardó en procesar su parte.
   - **El tiempo total del SMA es el mayor tiempo de todos los agentes** (el más lento determina el tiempo global).
   - Compara este tiempo máximo con el tiempo del sistema tradicional.

3. **Interpretación**
   - Si el tiempo máximo de los agentes SMA es menor que el tiempo del sistema tradicional, el SMA es más eficiente para este caso.
   - Si es igual o mayor, no hay ventaja en paralelismo (puede deberse a sobrecarga de coordinación, pocos datos, o limitaciones del hardware).

### Ejemplo de interpretación

- Si tus resultados son:
  - Tradicional: 100,000 registros en 200 ms.
  - SMA: 4 agentes, cada uno 25,000 registros en 120-140 ms.
  - **El tiempo total del SMA es 137 ms** (el mayor de los 4).
  - **Conclusión:** El SMA fue más rápido (137 ms < 200 ms), por lo tanto, el paralelismo fue efectivo.

### ¿Qué reportar en tu informe?

- Explica que cada agente procesó una parte diferente del archivo en paralelo.
- Muestra la suma de los registros y la suma total para validar que no se perdió información.
- Compara el tiempo máximo de los agentes SMA con el tiempo del tradicional.
- Concluye si hubo o no mejora y por qué.

---

**Resumen:**  
- El SMA es más eficiente si el tiempo máximo de los agentes es menor que el tiempo del tradicional.
- La suma de los registros procesados por todos los agentes debe ser igual al total de registros.
- La suma de las sumas debe coincidir con el tradicional.
- Si esto se cumple, puedes afirmar que el paralelismo de los SMA fue efectivo en tu experimento.

- **¿Qué debes mostrar al final del experimento SMA?**
  - La suma total de todos los agentes (debe coincidir con la suma del tradicional).
  - El total de registros procesados por todos los agentes (debe coincidir con el total de registros del archivo).
  - El tiempo de ejecución de cada agente.
  - El promedio de tiempo de ejecución de los agentes.
  - La demora máxima (el mayor tiempo de los agentes, que representa el tiempo total del SMA).
