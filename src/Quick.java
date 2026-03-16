/**
 * Implementación de Quicksort.
 */
public class Quick {

    // Metodo principal de ordenamiento
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    // Sobrecarga recursiva
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        
        int j = partition(a, lo, hi); // Particionamiento segun el ejemplo visual
        
        System.out.print("Pivote colocado. Estado actual: ");
        show(a);
        
        sort(a, lo, j - 1); // Ordenar mitad izquierda
        sort(a, j + 1, hi); // Ordenar mitad derecha
    }

    // Logica de particion
    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable pivot = a[hi]; // Se elige el ultimo como pivote
        int i = lo - 1; // Indice del elemento mas pequeño

        for (int j = lo; j < hi; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (less(a[j], pivot)) {
                i++;
                exch(a, i, j); // Intercambio
            }
        }
        // Colocar el pivote en su posición definitiva (i + 1)
        exch(a, i + 1, hi);
        return i + 1;
    }

    // --- METODOS REQUERIDOS POR LOS LINEAMIENTOS ---

    // Compara dos objetos que implementan Comparable
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // Intercambia dos elementos en el arreglo
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // Imprime el estado actual del arreglo
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    // Verifica si el arreglo esta ordenado
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    // Cliente de prueba con el ejemplo de la presentación
    public static void main(String[] args) {
        // Ejemplo: 7, 2, 1, 6, 8, 5, 3, 4
        Integer[] a = {7, 2, 1, 6, 8, 5, 3, 4};
        
        System.out.println("Arreglo inicial:");
        show(a);
        System.out.println("----------------------------------");

        sort(a);

        System.out.println("----------------------------------");
        if (isSorted(a)) {
            System.out.println("Resultado final ordenado:");
            show(a);
        }
    }
}