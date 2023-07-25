import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;

    private static double[][] position;
    private static int[] rootInfo;

    private static PriorityQueue<Edge4386> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        position = new double[N][2];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            position[i][0] = Double.parseDouble(st.nextToken());
            position[i][1] = Double.parseDouble(st.nextToken());
        }
        pq = new PriorityQueue<>();
        for (int n1 = 0; n1 < N; ++n1) {
            for (int n2 = n1 + 1; n2 < N; ++n2) {
                pq.offer(new Edge4386(n1, n2, Math.sqrt(Math.pow(position[n1][0] - position[n2][0], 2) + Math.pow(position[n1][1] - position[n2][1], 2))));
            }
        }
        rootInfo = new int[N];
        for (int i = 0; i < N; ++i) {
            rootInfo[i] = i;
        }
        double ans = 0;
        while (!pq.isEmpty()) {
            Edge4386 currEdge = pq.poll();
            int n1 = currEdge.getN1();
            int n2 = currEdge.getN2();
            double dist = currEdge.getDistance();

            int rootN1 = find(n1);
            int rootN2 = find(n2);
            if (rootN1 != rootN2) {
                union(n1, n2);
                ans += dist;
            }
        }
        System.out.println(ans);
    }

    private static void union(int n1, int n2) {
        int rootN1 = find(n1);
        int rootN2 = find(n2);
        if (rootN1 != rootN2) {
            if (rootN1 < rootN2) {
                rootInfo[rootN2] = rootN1;
            } else {
                rootInfo[rootN1] = rootN2;
            }
        }
    }

    private static int find(int node) {
        if (node == rootInfo[node]) {
            return node;
        } else {
            return rootInfo[node] = find(rootInfo[node]);
        }
    }
}

class Edge4386 implements Comparable<Edge4386>{
    private int n1;
    private int n2;
    private double distance;

    public Edge4386(int n1, int n2, double distance) {
        this.n1 = n1;
        this.n2 = n2;
        this.distance = distance;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge4386 edge4386) {
        if (distance - edge4386.getDistance() < 0) {
            return -1;
        } else if (distance - edge4386.getDistance() == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}