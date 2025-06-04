import jade.core.Agent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AgenteAnalizador extends Agent {
    protected void setup() {
        System.out.println("AgenteAnalizador iniciado.");

        // Verifica que el archivo existe y muestra su tamaño
        File archivo = new File("../datos/datos1.csv");
        if (!archivo.exists()) {
            System.out.println("ERROR: El archivo ../datos/datos1.csv NO existe.");
            doDelete();
            System.exit(1);
        }
        System.out.println("Archivo encontrado: " + archivo.getAbsolutePath());
        System.out.println("Tamaño del archivo (bytes): " + archivo.length());

        long suma = 0;
        int count = 0;
        long inicio = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            br.readLine(); // skip header
            int muestra = 0;
            while ((line = br.readLine()) != null) {
                // Usa split(",") para separar correctamente las columnas
                String[] parts = line.split(",");
                // Verifica que la cantidad de columnas sea la esperada
                if (parts.length != 11) {
                    System.out.println("Analizador línea " + count + " columnas inesperadas: " + parts.length);
                }
                suma += Integer.parseInt(parts[1]);
                count++;
                if (muestra < 5) {
                    System.out.println("Analizador Dato[" + count + "]: id=" + parts[0] + ", valor=" + parts[1]);
                    muestra++;
                }
                if (count % 1_000_000 == 0) {
                    System.out.println("Analizador procesados: " + count + " registros...");
                }
            }
            System.out.println("Analizador lectura finalizada. Total registros leídos: " + count);
        } catch (Exception e) {
            System.out.println("Error leyendo datos: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Analizador: suma total = " + suma);
        System.out.println("Analizador: registros procesados = " + count);
        System.out.println("Tiempo de ejecución (ms): " + (fin - inicio));
        doDelete();
        System.exit(0);
    }
}
