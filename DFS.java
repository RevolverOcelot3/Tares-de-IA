import java.util.*;

public class DFS {
    private int V;
    private LinkedList<Integer> adj[];

    public DFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<Integer>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    private void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void runDFS(int v) {
        boolean visited[] = new boolean[V];
        System.out.println("--- DFS ---");
        System.out.print("Recorrido (iniciando en el nodo " + v + "): ");
        DFSUtil(v, visited);
        System.out.println("\n-----------------");
    }

    public static void main(String[] args) {
        DFS g = new DFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.runDFS(2);
    }
}