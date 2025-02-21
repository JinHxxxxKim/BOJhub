import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, Q;
    static int[][] dist;
    static List<Edge>[] adList;
    static boolean[] visited;
    static int start;

    static class Edge {
        int num;
        int weight;

        public Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws Exception {

        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        adList = new ArrayList[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            adList[idx] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < N - 1; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adList[node1].add(new Edge(node2, weight));
            adList[node2].add(new Edge(node1, weight));
        }
        dist = new int[N + 1][N + 1];

        // SOLUTION
        // 1번 노드부터 N번 노드까지 시작점으로 하여 최소비용(유사도)을 계산한다.
        for (int startNode = 1; startNode <= N; ++startNode) {
            visited = new boolean[N + 1];
            start = startNode;
            dfs(startNode, 0);
        }
        for (int qCnt = 0; qCnt < Q; ++qCnt) {
            st = new StringTokenizer(br.readLine().trim());
            int k = Integer.parseInt(st.nextToken()); // 최소유사도
            int v = Integer.parseInt(st.nextToken()); // 시작점

            int ans = 0;
            for (int targetNodeNum = 1; targetNodeNum <= N; ++targetNodeNum) {
                if (targetNodeNum == v) continue;
                if (dist[v][targetNodeNum] >= k) ++ans;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int nodeNum, int currWeight) {
        visited[nodeNum] = true;
        for (Edge edge : adList[nodeNum]) {
            int nextNodeNum = edge.num;
            int nextWeight = edge.weight;
            if(visited[nextNodeNum]) continue;
            // 시작노드일 경우
            if (currWeight == 0) {
                dist[start][nextNodeNum] = nextWeight;
                dfs(nextNodeNum, nextWeight);
            } else {
                int minWeight = Math.min(currWeight, nextWeight);
                dist[start][nextNodeNum] = minWeight;
                dfs(nextNodeNum, minWeight);
            }
        }
    }
}
