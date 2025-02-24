import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] root;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        root = new int[N + 1];
        for (int idx = 1; idx < N + 1; ++idx) {
            root[idx] = idx;
        }

        for (int opCnt = 0; opCnt < M; ++opCnt) {
            st = new StringTokenizer(br.readLine().trim());
            int op = Integer.parseInt(st.nextToken());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            if (op == 0) {
                union(node1, node2);
            } else {
                int node1Root = find(node1);
                int node2Root = find(node2);
                if (node1Root == node2Root) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    static void union(int node1, int node2) {
        int node1Root = find(node1);
        int node2Root = find(node2);
        // 서로 다른 집합일 경우
        if (node1Root != node2Root) {
            if (node1Root < node2Root) {
                root[node2Root] = node1Root;
            } else {
                root[node1Root] = node2Root;
            }
        }
    }

    static int find(int node) {
        if(root[node] == node) return node;
        else return root[node] = find(root[node]);
    }
}
