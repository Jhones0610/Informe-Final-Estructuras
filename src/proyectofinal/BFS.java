
package proyectofinal;

import java.util.*;

public class BFS {
    private int V; // Número de vértices
    private LinkedList<Integer> adj[]; // Lista de adyacencia


    BFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Método para agregar una arista al grafo
    void agregarArista(int v, int w) {
        adj[v].add(w);
    }

    // Método de búsqueda en anchura desde un vértice "s"
    void BFS(int s) {
        // Marcar todos los vértices como no visitados
        boolean visitado[] = new boolean[V];

        // Crear una cola para el BFS
        LinkedList<Integer> cola = new LinkedList<Integer>();

        // Marcar el vértice actual como visitado y agregarlo a la cola
        visitado[s] = true;
        cola.add(s);

        while (cola.size() != 0) {
            // Sacar un vértice de la cola e imprimirlo
            s = cola.poll();
            System.out.print(s + " ");

            // Obtener todos los vértices adyacentes al vértice sacado de la cola.
            // Si un vértice adyacente no ha sido visitado, entonces marcarlo como visitado
            // y agregarlo a la cola.
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visitado[n]) {
                    visitado[n] = true;
                    cola.add(n);
                }
            }
        }
    }

    // Método para insertar una arista al grafo
    void insertarArista(int v, int w) {
        agregarArista(v, w);
    }

    // Método para buscar una arista en el grafo
    boolean buscarArista(int v, int w) {
        return adj[v].contains(w);
    }

    // Método para actualizar una arista en el grafo
    void actualizarArista(int v, int w, int nuevoW) {
        adj[v].removeIf(x -> x == w);
        agregarArista(v, nuevoW);
    }

    public static void main(String args[]) {
        BFS g = new BFS(4);

        g.insertarArista(0, 1);
        g.insertarArista(0, 2);
        g.insertarArista(1, 2);
        g.insertarArista(2, 0);
        g.insertarArista(2, 3);
        g.insertarArista(3, 3);

        System.out.println("Recorrido BFS empezando desde el vértice 2:");
        g.BFS(2);

        // Menú para buscar y actualizar aristas
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar arista");
            System.out.println("2. Buscar arista");
            System.out.println("3. Actualizar arista");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el vértice de inicio: ");
                    int v = scanner.nextInt();
                    System.out.print("Ingresa el vértice de destino: ");
                    int w = scanner.nextInt();
                    g.insertarArista(v, w);
                    System.out.println("Arista insertada correctamente.");
                    break;
                case 2:
                    System.out.print("Ingresa el vértice de inicio: ");
                    v = scanner.nextInt();
                    System.out.print("Ingresa el vértice de destino: ");
                    w = scanner.nextInt();
                    if (g.buscarArista(v, w))
                        System.out.println("La arista existe en el grafo.");
                    else
                        System.out.println("La arista no existe en el grafo.");
                    break;
                case 3:
                    System.out.print("Ingresa el vértice de inicio: ");
                    v = scanner.nextInt();
                    System.out.print("Ingresa el vértice de destino a actualizar: ");
                    w = scanner.nextInt();
                    System.out.print("Ingresa el nuevo vértice de destino: ");
                    int nuevoW = scanner.nextInt();
                    g.actualizarArista(v, w, nuevoW);
                    System.out.println("Arista actualizada correctamente.");
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 4.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }
}
