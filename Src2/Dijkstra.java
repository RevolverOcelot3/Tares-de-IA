package Src2;
import java.util.*;

class Dijkstra {
    static class Edge {
        int destino, peso;
        Edge(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    static class Grafo {
        int V; 
        List<List<Edge>> adj; 

        Grafo(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void agregarArista(int origen, int destino, int peso) {
            adj.get(origen).add(new Edge(destino, peso));
            adj.get(destino).add(new Edge(origen, peso)); 
        }

        void dijkstra(int origen) {
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[origen] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            pq.add(new int[]{0, origen});

            while (!pq.isEmpty()) {
                int[] actual = pq.poll();
                int distanciaActual = actual[0];
                int nodoActual = actual[1];

                if (distanciaActual > dist[nodoActual]) continue;

                for (Edge arista : adj.get(nodoActual)) {
                    int vecino = arista.destino;
                    int nuevoDist = dist[nodoActual] + arista.peso;

                    if (nuevoDist < dist[vecino]) {
                        dist[vecino] = nuevoDist;
                        pq.add(new int[]{nuevoDist, vecino});
                    }
                }
            }

            System.out.println("Distancias mínimas desde el nodo " + origen + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("A nodo " + i + " la distancia minima es de " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {
        Grafo g = new Grafo(6);
        g.agregarArista(0, 1, 4);
        g.agregarArista(0, 2, 2);
        g.agregarArista(1, 2, 5);
        g.agregarArista(1, 3, 10);
        g.agregarArista(2, 4, 3);
        g.agregarArista(4, 3, 4);
        g.agregarArista(3, 5, 11);

        g.dijkstra(0);
    }
}
