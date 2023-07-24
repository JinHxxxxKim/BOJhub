import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;

    private static int[] rootInfo;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rootInfo = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            rootInfo[i] = i;
        }
        PriorityQueue<Edge1647> pq = new PriorityQueue<>();
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Edge1647(n1, n2, w));
        }
        int ans = 0;
        int maxEdgeWeight = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            Edge1647 currEdge = pq.poll();
            int n1 = currEdge.getN1();
            int n2 = currEdge.getN2();
            int w = currEdge.getW();

            int n1Root = find(n1);
            int n2Root = find(n2);
            if (n1Root != n2Root) {
                union(n1, n2);
                ans += w;
                maxEdgeWeight = Math.max(maxEdgeWeight, w);
            }
        }
        System.out.println(ans - maxEdgeWeight);
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

class Edge1647 implements Comparable<Edge1647> {
    private int n1;
    private int n2;
    private int w;

    public Edge1647(int n1, int n2, int w) {
        this.n1 = n1;
        this.n2 = n2;
        this.w = w;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public int getW() {
        return w;
    }

    @Override
    public int compareTo(Edge1647 edge1647) {
        return this.w - edge1647.getW();
    }
}
