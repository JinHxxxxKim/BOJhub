import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[][] adList;
    static boolean[] visited;

    static int[][] ans;

    public static void main(String[] args) throws Exception {
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        adList = new int[N][N];
        visited = new boolean[N];
        ans = new int[N][N];

        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; ++col) {
                adList[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // SOLUTION
        // 모든 정점에서 탐색
        for (int nodeNum = 0; nodeNum < N; ++nodeNum) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(nodeNum);
            visited = new boolean[N];
            visited[nodeNum] = true;
            while(!q.isEmpty()){
                int currNode = q.poll();
                for(int adNode = 0; adNode < N; ++adNode) {
                    if(adList[currNode][adNode] == 0)
                        continue;
                    ans[nodeNum][adNode] = 1;
                    if(visited[adNode])
                        continue;
                    q.offer(adNode);
                    visited[adNode] = true;
                }
            }
        }

        // OUTPUT
        for (int row = 0; row < N; ++row) {
            for(int col = 0; col < N; ++col) {
                sb.append(ans[row][col]);
                if (col != N - 1) {
                    sb.append(" ");
                }
            }
            if (row != N - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}