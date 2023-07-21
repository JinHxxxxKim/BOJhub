import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int V;
    private static int E;

    private static int[] rootInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(node1, node2, weight);
        }
        Arrays.sort(edges);

        int ans = 0;
        rootInfo = new int[V + 1];
        for (int i = 1; i <= V; ++i) {
            rootInfo[i] = i;
        }
        for (int edgeNum = 0; edgeNum < E; ++edgeNum) {
            Edge currEdge = edges[edgeNum];
            int n1 = currEdge.getNode1();
            int n2 = currEdge.getNode2();
            int w = currEdge.getWeight();
            // Cycle 유효성 검사
            int n1_root = find(n1);
            int n2_root = find(n2);
            if (n1_root == n2_root) {
                // Cyclic
                continue;
            } else {
                // Acyclic
                union(n1, n2);
                ans += w;
            }
        }
        System.out.println(ans);
    }

    private static void union(int n1, int n2) {
        int n1_root = find(n1);
        int n2_root = find(n2);
        if (n1_root < n2_root) {
            rootInfo[n2_root] = n1_root;
        } else {
            rootInfo[n1_root] = n2_root;
        }
    }

    private static int find(int node) {
        if (rootInfo[node] == node) {
            return node;
        } else {
            return rootInfo[node] = find(rootInfo[node]);
        }
    }
}

class Edge implements Comparable<Edge>{
    private int node1;
    private int node2;
    private int weight;

    @Override
    public String toString() {
        return "Edge{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", weight=" + weight +
                '}';
    }

    public Edge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public int getNode1() {
        return node1;
    }

    public void setNode1(int node1) {
        this.node1 = node1;
    }

    public int getNode2() {
        return node2;
    }

    public void setNode2(int node2) {
        this.node2 = node2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}
