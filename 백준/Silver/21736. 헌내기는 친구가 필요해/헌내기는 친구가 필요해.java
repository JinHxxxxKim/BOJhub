import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int[] dx = new int[]{-1, 1, 0, 0};
    static final int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        int startRow = -1;
        int startCol = -1;
        int cnt = 0;
        for (int row = 0; row < N; ++row) {
            String r = br.readLine().trim();
            for (int col = 0; col < M; ++col) {
                map[row][col] = r.charAt(col);
                if (map[row][col] == 'I') {
                    startRow = row;
                    startCol = col;
                }
            }
        }
        visited[startRow][startCol] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startRow, startCol});
        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            for (int dir = 0; dir < 4; ++dir) {
                int tempRow = currRow + dx[dir];
                int tempCol = currCol + dy[dir];
                // 범위검증
                if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                    continue;
                }
                // 벽검증
                if (map[tempRow][tempCol] == 'X') {
                    continue;
                }
                // 방문 검증
                if (visited[tempRow][tempCol]) {
                    continue;
                }
                if (map[tempRow][tempCol] == 'P') {
                    ++cnt;
                }

                q.offer(new int[]{tempRow, tempCol});
                visited[tempRow][tempCol] = true;
            }
        }
        if (cnt == 0) {
            System.out.print("TT");
        } else {
            System.out.print(cnt);
        }
    }
}