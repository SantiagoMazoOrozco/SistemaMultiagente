public class AnalizadorTradicional {
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        System.out.println("Modo de ejecución: Centralizado");

        long suma = 0;
        int count = 0;
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("../datos/datos1.csv"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Suma todas las columnas de valor (de la 1 a la 10), igual que el SMA hacía originalmente
                for (int i = 1; i < parts.length; i++) {
                    suma += Integer.parseInt(parts[i]);
                }
                count++;
            }
        } catch (Exception e) {
            System.out.println("Error leyendo datos: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Centralizado: suma total = " + suma);
        System.out.println("Centralizado: registros procesados = " + count);
        System.out.println("Tiempo de ejecución (ms): " + (fin - inicio));
    }
}
