import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int limitCal;
    private static int[][] nutInfo;
    private static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            solution(br);

            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solution(BufferedReader br) throws Exception {
        ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        limitCal = Integer.parseInt(st.nextToken());
        nutInfo = new int[N][2];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            nutInfo[i][0] = Integer.parseInt(st.nextToken());
            nutInfo[i][1] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, 0);
    }

    private static void dfs(int curNutIdx, int currScore, int calSum) {
        if (curNutIdx == N) {
            if (calSum > limitCal) {
                return;
            } else {
                ans = Math.max(ans, currScore);
                return;
            }
        }
        dfs(curNutIdx + 1, currScore + nutInfo[curNutIdx][0], calSum + nutInfo[curNutIdx][1]);// 선택
        dfs(curNutIdx + 1, currScore, calSum);// 미선택
    }
}
