import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static final int DIVIDE = 1_000_000_007;

    static int N, R;
    static int[] elemes;
    static int ans;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        // elemes = new int[N];

        // SOLUTION
        // combination(0, 0);
        dp = new int[N + 1][N + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int row = 2; row < N + 1; ++row) {
            for (int col = 0; col < row + 1; ++col) {
                if (col == 0 || col == row) {
                    dp[row][col] = 1;
                    continue;
                }
                dp[row][col] = ((dp[row - 1][col] % DIVIDE) + (dp[row - 1][col - 1] % DIVIDE) % DIVIDE);
            }
        }

        System.out.print(dp[N][R]);
    }

    static void combination(int selectCnt, int selectIdx) {
        // 기저조건 1
        if (selectCnt == R) {
            ++ans;
            return;
        }

        // 기저조건 2
        if (selectIdx >= N) {
            return;
        }

        // 선택했을 때
        combination(selectCnt + 1, selectIdx + 1);

        // 선택하지 않았을 떄
        combination(selectCnt, selectIdx + 1);
    }
}
