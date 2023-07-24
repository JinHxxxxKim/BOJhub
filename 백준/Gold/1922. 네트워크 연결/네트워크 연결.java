import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;

    private static int[] rootInfo;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        PriorityQueue<Edge1922> pq = new PriorityQueue<>();
        for (int i = 0; i < M; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Edge1922(n1, n2, w));
        }
        int ans = 0;
        rootInfo = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            rootInfo[i] = i;
        }
        while (!pq.isEmpty()) {
            Edge1922 currEdge = pq.poll();
            int n1 = currEdge.getNode1();
            int n2 = currEdge.getNode2();
            int w = currEdge.getWeight();

            int n1Root = find(n1);
            int n2Root = find(n2);
            if (n1Root != n2Root) {
                union(n1, n2);
                ans += w;
            }
        }
        System.out.println(ans);
    }

    private static void union(int n1, int n2) {
        int n1Root = find(n1);
        int n2Root = find(n2);
        if (n1Root != n2Root) {
            if (n1Root < n2Root) {
                rootInfo[n2Root] = n1Root;
            } else {
                rootInfo[n1Root] = n2Root;
            }
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

class Edge1922 implements Comparable<Edge1922> {
    private int node1;
    private int node2;
    private int weight;

    public Edge1922(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge1922 edge1922) {
        return this.weight - edge1922.weight;
    }
}
