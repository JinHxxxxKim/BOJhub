import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, root, Q;
    static boolean[] visited;
    static ArrayList<Integer>[] adList;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adList = new ArrayList[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            adList[idx] = new ArrayList<>();
        }

        for (int cnt = 0; cnt < N - 1; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adList[node1].add(node2);
            adList[node2].add(node1);
        }

        visited = new boolean[N + 1];
        dp = new int[N + 1];
        dp[root] = dfs(root);

        // 쿼리 시작
        while (Q-- > 0) {
            int tempRoot = Integer.parseInt(br.readLine().trim());
            sb.append(dp[tempRoot]).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int node) {
        int subNodeCnt = 1;
        visited[node] = true;
        for (Integer subNode : adList[node]) {
            if (visited[subNode]) {
                continue;
            }
            subNodeCnt += dfs(subNode);
        }

        return dp[node] = subNodeCnt;
    }
}