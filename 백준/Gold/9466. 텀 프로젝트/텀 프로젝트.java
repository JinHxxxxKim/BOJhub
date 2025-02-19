import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] nextNodes;
    static boolean[] cycleChk, visited;
    static int N, ans;

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < TC; ++testCase) {
            // INPUT
            N = Integer.parseInt(br.readLine().trim());
            ans = N;
            nextNodes = new int[N + 1];
            cycleChk = new boolean[N + 1];
            visited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine().trim());
            for (int node = 1; node <= N; ++node) {
                nextNodes[node] = Integer.parseInt(st.nextToken());
                if(node == nextNodes[node]){
                    --ans;
                    cycleChk[node] = true;
                }
            }


            // SOLUTION
            // 1번 노드부터 N번 노드까지 각 노드를 시작점으로 하여 탐색한다.
            // 단, n < 100,000이므로, pruning 필요
            for (int startNode = 1; startNode <= N; ++startNode) {
                // cycle검증 완료 시, 해당 노드를 시작점으로 하는 탐색은 생략한다.
                if (cycleChk[startNode]) continue;
                dfs(startNode);
            }

            // OUTPUT
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int node) {
        if(visited[node]) {
            cycleChk[node] = true;
            --ans;
        }else{
            visited[node] = true;
        }

        int nextNode = nextNodes[node];
        if(!cycleChk[nextNode]) {
            dfs(nextNode);

        }
        visited[node] = false;
        cycleChk[node] = true;
    }
}
