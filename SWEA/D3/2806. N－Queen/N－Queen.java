import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static int[] queenPos;
    public static int cnt;
    public static int N;

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
        queenPos = new int[N];
        cnt = 0;
        dfs(0);
        return cnt;
    }

    private static void dfs(int currRow) {
        if (currRow == N) {
            ++cnt;
            return;
        }

        for (int col = 0; col < N; ++col) {
            boolean canPosQueen = validate(currRow, col);
            if (!canPosQueen) {
                continue;
            }
            queenPos[currRow] = col;
            dfs(currRow + 1);
        }
    }

    private static boolean validate(int currRow, int col) {
        for (int row = 0; row < currRow; ++row) {
            if (queenPos[row] == col) {
                return false;
            }
            if (Math.abs(row - currRow) == Math.abs(queenPos[row] - col)) {
                return false;
            }
        }
        return true;
    }
}
