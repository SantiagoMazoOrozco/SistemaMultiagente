import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public class MainAgente {
    public static AtomicInteger totalRegistros = new AtomicInteger(0);
    public static AtomicLong sumaTotal = new AtomicLong(0);
    public static AtomicLongArray tiempos = new AtomicLongArray(4); // Para 4 agentes

    public static void main(String[] args) throws Exception {
        System.out.println("Modo de ejecución: SMA");

        Runtime rt = Runtime.instance();
        ProfileImpl p = new ProfileImpl();
        ContainerController cc = rt.createMainContainer(p);

        int totalAgentes = 4;
        int totalLineas = 1_000_000; // Ajusta según el archivo generado
        int lineasPorAgente = totalLineas / totalAgentes;

        for (int i = 0; i < totalAgentes; i++) {
            int inicio = i * lineasPorAgente;
            int fin = (i == totalAgentes - 1) ? totalLineas : (i + 1) * lineasPorAgente;
            Object[] argsAgente = new Object[] { inicio, fin, i }; // Pasa el índice del agente
            AgentController recolector = cc.createNewAgent("Recolector" + i, "AgenteRecolector", argsAgente);
            recolector.start();
        }

        // Espera a que todos los agentes terminen (simple, no óptimo)
        Thread.sleep(5000); // Ajusta el tiempo según el tamaño de los datos

        // Imprime la suma total y el total de registros procesados por todos los agentes
        System.out.println("=== RESUMEN SMA ===");
        System.out.println("Suma total de todos los agentes: " + sumaTotal.get());
        System.out.println("Total de registros procesados por todos los agentes: " + totalRegistros.get());
        // Imprime el tiempo de cada recolector y el promedio
        long sumaTiempos = 0;
        long maxTiempo = 0;
        for (int i = 0; i < 4; i++) {
            long t = tiempos.get(i);
            System.out.println("Recolector" + i + " tiempo de ejecución (ms): " + t);
            sumaTiempos += t;
            if (t > maxTiempo) maxTiempo = t;
        }
        System.out.println("Promedio tiempo de ejecución (ms): " + (sumaTiempos / 4));
        System.out.println("Demora máxima (ms): " + maxTiempo);
        System.out.println("===================");
    }
}

// Este archivo está correcto si no tienes errores de sintaxis y todos los imports están presentes.
// Si ves líneas rojas en Visual Studio Code, pueden deberse a:
// - El proyecto no reconoce el classpath de JADE (jade.jar).
// - No tienes instalada la extensión de Java en VS Code.
// - El archivo jade.jar no está en la carpeta correcta o no está referenciado en la configuración del proyecto.

// Solución:
// 1. Asegúrate de que jade.jar está en la carpeta sma.
// 2. Compila siempre usando el parámetro -cp ".;jade.jar".
// 3. Ignora las líneas rojas si la compilación y ejecución funcionan correctamente.
// 4. Si quieres soporte completo en VS Code, instala la extensión "Java Extension Pack" y configura el classpath en .vscode/settings.json si es necesario.

// El código Java en este archivo es válido y no tiene errores de sintaxis.
