import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int maxCnt;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                visited[row][col] = true;
                dfs(1, map[row][col], row, col);
                visited[row][col] = false;
//                System.out.println("row = " + row);
//                System.out.println("col = " + col);
//                System.out.println("maxCnt = " + maxCnt);
//                System.out.println("=====================");
            }
        }
        System.out.println(maxCnt);

    }

    static void dfs(int depth, int sum, int row, int col) {
        if (depth == 4) {
            maxCnt = Math.max(maxCnt, sum);
            return;
        }


        for (int dir = 0; dir < 4; ++dir) {
            int tempRow = row + dx[dir];
            int tempCol = col + dy[dir];
            if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                continue;
            }
            if (visited[tempRow][tempCol]) {
                continue;
            }
            if (depth == 2) { // ㅗ 모양 고려
                visited[tempRow][tempCol] = true;
                dfs(depth + 1, sum + map[tempRow][tempCol], row, col);
                visited[tempRow][tempCol] = false;
            }

            visited[tempRow][tempCol] = true;
            dfs(depth + 1, sum + map[tempRow][tempCol], tempRow, tempCol);
            visited[tempRow][tempCol] = false;
        }
    }
}
