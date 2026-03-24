
/**
 * Adaptación de Quicksort con métricas y visualización de casos.
 */
class Producto implements Comparable<Producto> {

    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public int compareTo(Producto otro) {
        return Double.compare(this.precio, otro.precio);
    }

    @Override
    public String toString() {
        return String.format("%-15s | $%8.2f", nombre, precio);
    }
}

public class QuickInventario {

    // Variables estaticas para contar operaciones
    private static int comparaciones = 0;
    private static int intercambios = 0;

    public static void sort(Comparable[] a) {
        comparaciones = 0; // Reiniciar contadores antes de cada prueba
        intercambios = 0;
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            comparaciones++; // Contamos cada comparacion
            if (less(a[j], pivot)) {
                i++;
                exch(a, i, j);
            }
        }
        exch(a, i + 1, hi);
        return i + 1;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        intercambios++; // Contamos cada intercambio
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // Metodo auxiliar para ver los resultados
    public static void imprimirResumen(Producto[] arreglo) {
        for (Producto p : arreglo) {
            System.out.println(p);
        }
        System.out.println(">> METRICAS: " + comparaciones + " comparaciones, " + intercambios + " intercambios.");
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        // Escenario A: Caso Promedio
        Producto[] aleatorio = {
            new Producto("Laptop", 1200), new Producto("Mouse", 20),
            new Producto("Teclado", 50), new Producto("Monitor", 300),
            new Producto("Cable", 10), new Producto("Webcam", 80),
            new Producto("Silla", 250), new Producto("Audifonos", 120)
        };

        // Escenario B: Peor Caso (Ordenado)
        Producto[] ordenado = {
            new Producto("Cable", 10), new Producto("Mouse", 20),
            new Producto("Teclado", 50), new Producto("Webcam", 80),
            new Producto("Audifonos", 120), new Producto("Silla", 250),
            new Producto("Monitor", 300), new Producto("Laptop", 1200)
        };

        // Escenario C: Caso Invertido
        Producto[] invertido = {
            new Producto("Laptop", 1200), new Producto("Monitor", 300),
            new Producto("Silla", 250), new Producto("Audifonos", 120),
            new Producto("Webcam", 80), new Producto("Teclado", 50),
            new Producto("Mouse", 20), new Producto("Cable", 10)
        };

        System.out.println("--- PRUEBA 1: CATALOGO ALEATORIO ---");
        sort(aleatorio);
        imprimirResumen(aleatorio);

        System.out.println("\n--- PRUEBA 2: CATALOGO ORDENADO (Peor Caso) ---");
        sort(ordenado);
        imprimirResumen(ordenado);

        System.out.println("\n--- PRUEBA 3: CATALOGO INVERTIDO (Caso Inverso) ---");
        sort(invertido);
        imprimirResumen(invertido);
    }
}
