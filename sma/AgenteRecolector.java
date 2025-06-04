import jade.core.Agent;
import java.io.BufferedReader;
import java.io.FileReader;

public class AgenteRecolector extends Agent {
    protected void setup() {
        Object[] args = getArguments();
        int inicio = (args != null && args.length > 0) ? (int) args[0] : 0;
        int fin = (args != null && args.length > 1) ? (int) args[1] : Integer.MAX_VALUE;
        int idx = (args != null && args.length > 2) ? (int) args[2] : 0;

        System.out.println(getLocalName() + " procesando líneas " + inicio + " a " + (fin - 1));
        long suma = 0;
        int count = 0;
        long inicioTiempo = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader("../datos/datos1.csv"))) {
            String line;
            br.readLine(); // skip header
            int lineaActual = 0;
            while ((line = br.readLine()) != null) {
                if (lineaActual >= inicio && lineaActual < fin) {
                    // Usa split(",") para separar correctamente las columnas
                    String[] parts = line.split(",");
                    // Verifica que la cantidad de columnas sea la esperada
                    if (parts.length != 11) {
                        System.out.println(getLocalName() + " línea " + lineaActual + " columnas inesperadas: " + parts.length);
                    }
                    // Solo suma la columna 1 (igual que el tradicional)
                    suma += Integer.parseInt(parts[1]);
                    count++;
                }
                // Imprime para depuración
                if (lineaActual < 10 || lineaActual % 25000 == 0) {
                    System.out.println(getLocalName() + " leyendo línea " + lineaActual);
                }
                lineaActual++;
                if (lineaActual >= fin) break;
            }
            System.out.println(getLocalName() + " terminó en línea " + lineaActual);
        } catch (Exception e) {
            System.out.println(getLocalName() + " Error leyendo datos: " + e.getMessage());
        }

        long finTiempo = System.currentTimeMillis();
        long tiempoEjecucion = finTiempo - inicioTiempo;
        System.out.println(getLocalName() + ": suma total = " + suma);
        System.out.println(getLocalName() + ": registros procesados = " + count);
        System.out.println(getLocalName() + ": tiempo de ejecución (ms): " + tiempoEjecucion);

        // Sumar los resultados globalmente al finalizar
        try {
            Class<?> mainClass = Class.forName("MainAgente");
            java.lang.reflect.Field sumaTotalField = mainClass.getField("sumaTotal");
            java.lang.reflect.Field totalRegistrosField = mainClass.getField("totalRegistros");
            java.lang.reflect.Field tiemposField = mainClass.getField("tiempos");
            ((java.util.concurrent.atomic.AtomicLong) sumaTotalField.get(null)).addAndGet(suma);
            ((java.util.concurrent.atomic.AtomicInteger) totalRegistrosField.get(null)).addAndGet(count);
            ((java.util.concurrent.atomic.AtomicLongArray) tiemposField.get(null)).set(idx, tiempoEjecucion);
        } catch (Exception e) {
            System.out.println(getLocalName() + " no pudo actualizar el resumen global: " + e.getMessage());
        }

        doDelete();
    }
}
