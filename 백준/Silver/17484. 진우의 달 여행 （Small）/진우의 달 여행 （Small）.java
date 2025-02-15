import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] comeLeft = new int[N][M];
        int[][] comeCenter = new int[N][M];
        int[][] comeRight = new int[N][M];

        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기값 세팅
        for (int col = 0; col < M; ++col) {
            comeLeft[0][col] = map[0][col];
            comeCenter[0][col] = map[0][col];
            comeRight[0][col] = map[0][col];
        }
        
        // SOLUTION 
        // [row][col]: 여기까지 오는데 최소비용(직전 행동에 따른)
        for (int row = 1; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                int l, c, r;
                // 가운데에서 오는거 계산
                l = comeLeft[row - 1][col];
                r = comeRight[row - 1][col];
                comeCenter[row][col] = Math.min(l, r) + map[row][col];

                // 왼쪽에서 오는거 계산
                if (col == 0) {
                    comeLeft[row][col] = Integer.MAX_VALUE;
                } else {
                    r = comeRight[row - 1][col - 1];
                    c = comeCenter[row - 1][col - 1];
                    comeLeft[row][col] = Math.min(r, c) + map[row][col];
                }
                // 오른쪽에서 오는거 계산
                if (col == M - 1) {
                    comeRight[row][col] = Integer.MAX_VALUE;
                } else {
                    l = comeLeft[row - 1][col + 1];
                    c = comeCenter[row - 1][col + 1];
                    comeRight[row][col] = Math.min(l, c) + map[row][col];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int col = 0; col < M; ++col) {
            ans = Math.min(ans, comeCenter[N - 1][col]);
            ans = Math.min(ans, comeRight[N - 1][col]);
            ans = Math.min(ans, comeLeft[N - 1][col]);
        }
        System.out.println(ans);
    }
}