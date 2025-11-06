import java.util.*;

class Node implements Comparable<Node> {
    public int id;
    public int cost;

    public Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class UCS {
    private int V;
    private LinkedList<int[]> adj[];

    public UCS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w, int weight) {
        adj[v].add(new int[]{w, weight});
    }

    void runUCS(int startNode, int goalNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int[] cost = new int[V];
        Arrays.fill(cost, Integer.MAX_VALUE);

        cost[startNode] = 0;
        pq.add(new Node(startNode, 0));

        System.out.println("--- UCS ---");
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;
            int currentCost = current.cost;

            if (u == goalNode) {
                System.out.println("Objetivo " + goalNode + " alcanzado con un costo total de: " + currentCost);
                return;
            }

            if (currentCost > cost[u]) {
                continue;
            }

            System.out.println("Visitando nodo: " + u + " (Costo acumulado: " + currentCost + ")");

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];
                int newCost = currentCost + weight;

                if (newCost < cost[v]) {
                    cost[v] = newCost;
                    pq.add(new Node(v, newCost));
                }
            }
        }
        System.out.println("El objetivo no es alcanzable desde el nodo de inicio.");
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        UCS g = new UCS(5);

        g.addEdge(0, 1, 10);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(3, 1, 3);
        g.addEdge(3, 2, 9);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 0, 7);
        g.addEdge(4, 2, 6);

        int startNode = 0;
        int goalNode = 2;
        
        g.runUCS(startNode, goalNode);
    }
}