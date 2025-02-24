import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] root;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());
        root = new int[N];
        for (int idx = 0; idx < N; ++idx) {
            root[idx] = idx;
        }

        for (int srcNode = 0; srcNode < N; ++srcNode) {
            st = new StringTokenizer(br.readLine().trim());
            for (int targetNode = 0; targetNode < srcNode; ++targetNode) {
                int con = Integer.parseInt(st.nextToken());
                if (con == 0) {
                    continue;
                }
                union(srcNode, targetNode);
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        int[] course = new int[M];
        for (int cnt = 0; cnt < M; ++cnt) {
            course[cnt] = Integer.parseInt(st.nextToken()) - 1;
        }

        int cmpRoot = find(course[0]);
        for (int cnt = 1; cnt < M; ++cnt) {
            if (find(course[cnt]) != cmpRoot) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static void union(int node1, int node2) {
        int node1Root = find(node1);
        int node2Root = find(node2);
        if (node1Root != node2Root) {
            if (node1Root < node2Root) {
                root[node2Root] = node1Root;
            } else {
                root[node1Root] = node2Root;
            }
        }
    }

    static int find(int node) {
        if(node == root[node]) return node;
        else return root[node] = find(root[node]);
    }
}
