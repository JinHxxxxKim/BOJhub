import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] root;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        root = new int[N];
        for (int idx = 0; idx < N; ++idx) {
            root[idx] = idx;
        }
        int turn = 1;
        boolean end = false;
        for (int cnt = 0; cnt < M; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int node1Root = find(node1);
            int node2Root = find(node2);
            if (node1Root != node2Root) {
                ++turn;
                union(node1Root, node2Root);
            } else {
                end = true;
                break;
            }
        }
        if (end) {
            System.out.println(turn);
        } else {
            System.out.println(0);
        }
    }

    static int find(int node) {
        if(node == root[node]) return node;
        return root[node] = find(root[node]);
    }

    static void union(int n1, int n2) { // root info
        root[n2] = n1;
    }
}
