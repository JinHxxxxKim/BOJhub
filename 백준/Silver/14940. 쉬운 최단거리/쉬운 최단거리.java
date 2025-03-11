import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dist = new int[N][M];
        int startRow = 0;
        int startCol = 0;
        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine().trim());
            Arrays.fill(dist[row], -1);
            for (int col = 0; col < M; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 2) {
                    startRow = row;
                    startCol = col;
                } else if (map[row][col] == 0) {
                    dist[row][col] = 0;
                }
            }
        }
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        dist[startRow][startCol] = 0;
        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            for (int dir = 0; dir < 4; ++dir) {
                int tempRow = currRow + dx[dir];
                int tempCol = currCol + dy[dir];
                // 범위 검증
                if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                    continue;
                }
                // 방문 검증
                if (visited[tempRow][tempCol]) {
                    continue;
                }
                // 벽 검증
                if (map[tempRow][tempCol] == 0) {
                    continue;
                }
                q.offer(new int[]{tempRow, tempCol});
                visited[tempRow][tempCol] = true;
                dist[tempRow][tempCol] = dist[currRow][currCol] + 1;
            }
        }
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                sb.append(dist[row][col]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}