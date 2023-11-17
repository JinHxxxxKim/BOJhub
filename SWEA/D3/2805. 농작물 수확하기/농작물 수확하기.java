import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    private static int N;
    private static int[][] score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int ans = solution(br);
            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(BufferedReader br) throws Exception {
        N = Integer.parseInt(br.readLine());
        score = new int[N][N];
        for (int i = 0; i < N; ++i) {
            String temp = br.readLine();
            for (int j = 0; j < N; ++j) {
                score[i][j] = temp.charAt(j) - '0';
            }
        }
        int ans = 0;
        int center = N / 2;
        int left = 0;
        int right = 0;

        boolean isInc = true;

        for (int row = 0; row < N; ++row) {
            ans += score[row][center];
            for (int i = 1; i <= left; ++i) {
                ans += score[row][center - i];
            }
            for (int i = 1; i <= right; ++i) {
                ans += score[row][center + i];
            }
            if ((1 + left + right) < N && isInc) {
                ++left;
                ++right;
            } else {
                --left;
                --right;
                isInc = false;
            }
        }
        return ans;
    }
}
