import java.io.*;
import java.util.*;

// 특정 노드가 얼리어답터일 경우 > 이웃한 모든 노드들이 얼리어답터일 수 도, 아닐 수도 있다.
// 특정 노드가 얼리어답터가 아닐 경우 > 이웃한 모든 노드들이 얼리어답터가 되어야한다.
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<Integer>[] adList;
    static int dp[][];

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
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

//        for (List<Integer> integers : adList) {
//            System.out.println(integers);
//        }

        // [0]: i번째 노드가 얼리어답터일 경우
        // [1]: i번째 노드가 얼리어답터가 아닐 경우
        dp = new int[N + 1][2];
        // Root = 1로 고정
        int ans = dfs(1, 0);
        System.out.print(ans);
//        for (int idx = 1; idx <= N; ++idx) {
//            System.out.print("NODE NUM: " + idx + " > ");
//            System.out.println(Arrays.toString(dp[idx]));
//        }

    }

    private static int dfs(int currNode, int rootNode) {
        // leaf node 탐색
        int adNodeCnt = 0;
        for (int adNode : adList[currNode]) {
            if (adNode == rootNode) {
                continue;
            }
            adNodeCnt++;
        }
        // 기저조건
        if (adNodeCnt == 0) {
            dp[currNode][0] = 1;
            dp[currNode][1] = 0;
        } else {
            for (int adNode : adList[currNode]) {
                if (adNode == rootNode) {
                    continue;
                }
                dfs(adNode, currNode);
            }
        }


        // currNode가 얼리어답터일 경우 vs currNode가 얼리어답터가 아닐 경우
        // 1. currNode가 얼리어답터가 아닐 경우 > 모든 자식 노드가 얼리어답터여야함
        int ret = 0;
        for (int adNode : adList[currNode]) {
            if (adNode == rootNode) {
                continue;
            }
            ret += dp[adNode][0];
        }
        dp[currNode][1] = ret;

        // 2. currNode가 얼리어답터일 경우
        ret = 0;
        for (int adNode : adList[currNode]) {
            if (adNode == rootNode) {
                continue;
            }
            ret += Math.min(dp[adNode][0], dp[adNode][1]);
        }
        dp[currNode][0] = ret + 1; // 자기자신 포함

        return Math.min(dp[currNode][0], dp[currNode][1]);
    }
}