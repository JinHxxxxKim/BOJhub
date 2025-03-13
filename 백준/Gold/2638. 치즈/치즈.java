import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < M; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isAllMelt = false;
        int time = 0;
        while (!isAllMelt) {
            int startRow = 0;
            int startCol = 0;
            int[][] isMelt = new int[N][M]; // 이번 턴에 녹은 위치인지 여부
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{startRow, startCol});
            visited[startRow][startCol] = true;
            while (!q.isEmpty()) {
                int[] currNode = q.poll();
                int currRow = currNode[0];
                int currCol = currNode[1];
                for (int dir = 0; dir < 4; ++dir) {
                    int tempRow = currRow + dx[dir];
                    int tempCol = currCol + dy[dir];
                    // 범위
                    if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                        continue;
                    }
                    // 방문
                    if (visited[tempRow][tempCol]) {
                        continue;
                    }

                    // 치즈일 경우
                    if (map[tempRow][tempCol] == 1) {
                        isMelt[tempRow][tempCol]++;
                    }
                    // 아닐경우
                    else {
                        q.offer(new int[]{tempRow, tempCol});
                        visited[tempRow][tempCol] = true;
                    }

                }
            }
            // 이번 턴 종료
            // 이번 턴에 녹을 치즈 녹이기
            int meltCnt = 0;
            for (int row = 0; row < N; ++row) {
                for (int col = 0; col < M; ++col) {
                    if (isMelt[row][col] >= 2) {
                        map[row][col] = 0;
                        ++meltCnt;
                    }
                }
            }
//            System.out.println("TIME:: " + time);
//            for (int[] rows : map) {
//                System.out.println(Arrays.toString(rows));
//            }
//            System.out.println("==================================");

            if (meltCnt == 0) {
                isAllMelt = true;
            } else {
                ++time;
            }
        }
        System.out.println(time);
    }
}