package proyectofinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ProyectoFinal {
    static final int ORDEN = 3;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1. BFS ");
            System.out.println("2. Arboles");
            System.out.println("3. algoritmoDijkstra");
            System.out.println("4. Pilas");
            System.out.println("5. Colas");
            System.out.println("6. Arboles B+");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Opción 1. BFS");
                    BFS();
                    break;
                case 2:
                    Arboles();
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3.");
                    algoritmoDijkstra();
                    break;
                case 4:
                    System.out.println("Has seleccionado la Opción 4.");
                    pilasConListas();
                    break;
                case 5:
                    System.out.println("Has seleccionado la Opción 5.");
                    colasConListas();
                    break;
                case 6:
                    System.out.println("Has seleccionado la Opción 6.");
                    ArbolesBMas();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 4.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    public static void BFS() {
        int[][] grafo = {
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0}
        };

        bfs(grafo, 0);

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar arista");
            System.out.println("2. Buscar arista");
            System.out.println("3. Actualizar arista");
            System.out.println("4. Imprimir grafo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nodo inicial y el nodo final de la arista a insertar:");
                    int nodoInicial = scanner.nextInt();
                    int nodoFinal = scanner.nextInt();
                    insertarArista(grafo, nodoInicial, nodoFinal);
                    System.out.println("Arista insertada correctamente.");
                    break;
                case 2:
                    System.out.println("Ingrese el nodo inicial y el nodo final de la arista a buscar:");
                    nodoInicial = scanner.nextInt();
                    nodoFinal = scanner.nextInt();
                    if (buscarArista(grafo, nodoInicial, nodoFinal)) {
                        System.out.println("La arista existe en el grafo.");
                    } else {
                        System.out.println("La arista no existe en el grafo.");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el nodo inicial y el nodo final de la arista a actualizar:");
                    nodoInicial = scanner.nextInt();
                    nodoFinal = scanner.nextInt();
                    System.out.println("Ingrese el nuevo valor para la arista:");
                    int nuevoValor = scanner.nextInt();
                    actualizarArista(grafo, nodoInicial, nodoFinal, nuevoValor);
                    System.out.println("Arista actualizada correctamente.");
                    break;
                case 4:
                    imprimirGrafo(grafo);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");

                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
                    break;
            }
        } while (opcion != 5);
    }

    public static void bfs(int[][] grafo, int nodoInicial) {
        int numNodos = grafo.length;
        boolean[] visitado = new boolean[numNodos];

        Queue<Integer> cola = new LinkedList<>();
        visitado[nodoInicial] = true;
        cola.add(nodoInicial);

        System.out.println("\nRecorrido BFS empezando desde el vértice " + nodoInicial + ":");
        while (!cola.isEmpty()) {
            int nodoActual = cola.poll();
            System.out.print(nodoActual + " ");

            for (int i = 0; i < numNodos; i++) {
                if (grafo[nodoActual][i] == 1 && !visitado[i]) {
                    visitado[i] = true;
                    cola.add(i);
                }
            }
        }
    }

    public static void insertarArista(int[][] grafo, int nodoInicial, int nodoFinal) {
        grafo[nodoInicial][nodoFinal] = 1;
        grafo[nodoFinal][nodoInicial] = 1; // Si es un grafo no dirigido
    }

    public static boolean buscarArista(int[][] grafo, int nodoInicial, int nodoFinal) {
        return grafo[nodoInicial][nodoFinal] == 1;
    }

    public static void actualizarArista(int[][] grafo, int nodoInicial, int nodoFinal, int nuevoValor) {
        grafo[nodoInicial][nodoFinal] = nuevoValor;
        grafo[nodoFinal][nodoInicial] = nuevoValor; // Si es un grafo no dirigido
    }

    public static void imprimirGrafo(int[][] grafo) {
        System.out.println("\nGrafo actualizado:");
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[0].length; j++) {
                System.out.print(grafo[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Nodo {

        int valor;
        Nodo izquierda, derecha;

        Nodo(int item) {
            valor = item;
            izquierda = derecha = null;
        }
    }

    static Nodo raiz;

    public static void Arboles() {
        Scanner scanner = new Scanner(System.in);

        Nodo raiz = null;

        System.out.println("Ingrese los valores para el árbol (ingrese -1 para detenerse):");
        int valor = scanner.nextInt();
        while (valor != -1) {
            raiz = insertar(raiz, valor);
            valor = scanner.nextInt();
        }

        System.out.println("\nRecorrido Inorden del árbol:");
        inOrden(raiz);

        System.out.println("\nRecorrido Preorden del árbol:");
        preOrden(raiz);

        System.out.println("\nRecorrido Postorden del árbol:");
        postOrden(raiz);

        scanner.close();
    }

    static Nodo insertar(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.izquierda = insertar(raiz.izquierda, valor);
        } else if (valor > raiz.valor) {
            raiz.derecha = insertar(raiz.derecha, valor);
        }

        return raiz;
    }

    static void inOrden(Nodo raiz) {
        if (raiz != null) {
            inOrden(raiz.izquierda);
            System.out.print(raiz.valor + " ");
            inOrden(raiz.derecha);
        }
    }

    static void preOrden(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.valor + " ");
            preOrden(raiz.izquierda);
            preOrden(raiz.derecha);
        }
    }

    static void postOrden(Nodo raiz) {
        if (raiz != null) {
            postOrden(raiz.izquierda);
            postOrden(raiz.derecha);
            System.out.print(raiz.valor + " ");
        }
    }

    static void algoritmoDijkstra() {
        System.out.print("Ingrese el número de nodos: ");
        int numNodos = scanner.nextInt();

        int[][] grafo = new int[numNodos][numNodos];
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (i != j) {
                    System.out.print("Ingrese el peso de la arista entre el nodo " + i + " y el nodo " + j + ": ");
                    grafo[i][j] = scanner.nextInt();
                } else {
                    grafo[i][j] = 0;
                }
            }
        }

        System.out.print("Ingrese el nodo de inicio: ");
        int nodoInicio = scanner.nextInt();
        dijkstra(grafo, nodoInicio);

        scanner.close();
    }

    public static void dijkstra(int[][] grafo, int nodoInicio) {
        int numNodos = grafo.length;
        int[] distancias = new int[numNodos];
        boolean[] visitado = new boolean[numNodos];

        for (int i = 0; i < numNodos; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }

        distancias[nodoInicio] = 0;

        for (int i = 0; i < numNodos - 1; i++) {
            int nodoActual = nodoMenorDistancia(distancias, visitado);
            visitado[nodoActual] = true;

            for (int j = 0; j < numNodos; j++) {
                if (!visitado[j] && grafo[nodoActual][j] != 0 && distancias[nodoActual] != Integer.MAX_VALUE
                        && distancias[nodoActual] + grafo[nodoActual][j] < distancias[j]) {
                    distancias[j] = distancias[nodoActual] + grafo[nodoActual][j];
                }
            }
        }

        System.out.println("Distancias más cortas desde el nodo " + nodoInicio + " a todos los demás nodos:");
        for (int i = 0; i < numNodos; i++) {
            System.out.println("Nodo " + i + ": " + distancias[i]);
        }
    }

    public static int nodoMenorDistancia(int[] distancias, boolean[] visitado) {
        int minDistancia = Integer.MAX_VALUE;
        int minNodo = -1;

        for (int i = 0; i < distancias.length; i++) {
            if (!visitado[i] && distancias[i] <= minDistancia) {
                minDistancia = distancias[i];
                minNodo = i;
            }
        }

        return minNodo;
    }

    public static void pilasConListas() {
        List<Integer> pila = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Apilar");
            System.out.println("2. Desapilar");
            System.out.println("3. Mostrar cima");
            System.out.println("4. Imprimir pila");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un valor para apilar: ");
                    int valor = scanner.nextInt();
                    apilar(pila, valor);
                    break;
                case 2:
                    Integer desapilado = desapilar(pila);
                    if (desapilado != null) {
                        System.out.println("Valor desapilado: " + desapilado);
                    } else {
                        System.out.println("La pila está vacía.");
                    }
                    break;
                case 3:
                    Integer cima = cima(pila);
                    if (cima != null) {
                        System.out.println("Valor en la cima: " + cima);
                    } else {
                        System.out.println("La pila está vacía.");
                    }
                    break;
                case 4:
                    imprimirPila(pila);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 5.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void apilar(List<Integer> pila, int valor) {
        pila.add(valor);
        System.out.println("Valor " + valor + " apilado.");
    }

    public static Integer desapilar(List<Integer> pila) {
        if (!pila.isEmpty()) {
            return pila.remove(pila.size() - 1);
        }
        return null;
    }

    public static Integer cima(List<Integer> pila) {
        if (!pila.isEmpty()) {
            return pila.get(pila.size() - 1);
        }
        return null;
    }

    public static void imprimirPila(List<Integer> pila) {
        if (pila.isEmpty()) {
            System.out.println("La pila está vacía.");
        } else {
            System.out.println("Pila: " + pila);
        }
    }

    public static void colasConListas() {
        List<Integer> cola = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Encolar");
            System.out.println("2. Desencolar");
            System.out.println("3. Mostrar frente");
            System.out.println("4. Imprimir cola");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un valor para encolar: ");
                    int valor = scanner.nextInt();
                    encolar(cola, valor);
                    break;
                case 2:
                    Integer desencolado = desencolar(cola);
                    if (desencolado != null) {
                        System.out.println("Valor desencolado: " + desencolado);
                    } else {
                        System.out.println("La cola está vacía.");
                    }
                    break;
                case 3:
                    Integer frente = frente(cola);
                    if (frente != null) {
                        System.out.println("Valor en el frente: " + frente);
                    } else {
                        System.out.println("La cola está vacía.");
                    }
                    break;
                case 4:
                    imprimirCola(cola);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 5.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }

    public static void encolar(List<Integer> cola, int valor) {
        cola.add(valor);
        System.out.println("Valor " + valor + " encolado.");
    }

    public static Integer desencolar(List<Integer> cola) {
        if (!cola.isEmpty()) {
            return cola.remove(0);
        }
        return null;
    }

    public static Integer frente(List<Integer> cola) {
        if (!cola.isEmpty()) {
            return cola.get(0);
        }
        return null;
    }

    public static void imprimirCola(List<Integer> cola) {
        if (cola.isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("Cola: " + cola);
        }
    }
    public static void ArbolesBMas(){
    List<Integer> raiz = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar valor");
            System.out.println("2. Imprimir árbol");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un valor para insertar: ");
                    int valor = scanner.nextInt();
                    raiz = insertar(raiz, valor);
                    break;
                case 2:
                    imprimirArbol(raiz);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 3.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    
    }
    public static List<Integer> insertar(List<Integer> nodo, int valor) {
        nodo.add(valor);
        Collections.sort(nodo);

        if (nodo.size() > ORDEN) {
            List<Integer> nuevoNodo = dividirNodo(nodo);
            // Aquí se debería manejar la división del nodo raíz y ajustar la estructura del árbol.
            // Para simplicidad, solo estamos mostrando el nuevo nodo creado.
            System.out.println("Nodo dividido: " + nuevoNodo);
        }

        return nodo;
    }

    public static List<Integer> dividirNodo(List<Integer> nodo) {
        int medio = nodo.size() / 2;
        List<Integer> nuevoNodo = new ArrayList<>(nodo.subList(medio, nodo.size()));
        nodo.subList(medio, nodo.size()).clear();
        return nuevoNodo;
    }

    public static void imprimirArbol(List<Integer> nodo) {
        System.out.println("Nodo: " + nodo);

    }
    
}
