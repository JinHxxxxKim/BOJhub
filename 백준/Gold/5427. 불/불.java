import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws Exception{
        // INPUT
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < TC; ++testCase) {
            st = new StringTokenizer(br.readLine().trim());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            map = new char[N][M];

            int startRow = 0;
            int startCol = 0;
            int[][] escapeTime = new int[N][M];
            boolean[][] escapeVisited = new boolean[N][M];
            for (int[] row : escapeTime) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            Queue<int[]> fireQ = new LinkedList<>();
            boolean[][] fireVisited = new boolean[N][M];
            int[][] fireTime = new int[N][M];
            for (int[] row : fireTime) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            for (int row = 0; row < N; ++row) {
                String line = br.readLine().trim();
                for (int col = 0; col < M; ++col) {
                    map[row][col] = line.charAt(col);
                    if (line.charAt(col) == '@') {
                        startRow = row;
                        startCol = col;
                        map[row][col] = '.';
                        escapeTime[row][col] = 0;
                        escapeVisited[row][col] = true;
                    } else if (line.charAt(col) == '*'){
                        fireQ.add(new int[]{row, col});
                        map[row][col] = '.';
                        fireTime[row][col] = 0;
                        escapeVisited[row][col] = true;
                    }
                }
            }

            // SOLUTION
            // 1. fire
            while (!fireQ.isEmpty()) {
                int[] currPos = fireQ.poll();
                for (int dir = 0; dir < 4; ++dir) {
                    int tempRow = currPos[0] + dx[dir];
                    int tempCol = currPos[1] + dy[dir];

                    // 범위
                    if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                        continue;
                    }
                    // 벽
                    if (map[tempRow][tempCol] == '#') {
                        continue;
                    }
                    // 방문
                    if (fireVisited[tempRow][tempCol]) {
                        continue;
                    }
                    fireQ.add(new int[]{tempRow, tempCol});
                    fireTime[tempRow][tempCol] = fireTime[currPos[0]][currPos[1]] + 1;
                    fireVisited[tempRow][tempCol] = true;
                }
            }

            // 2. escape
            boolean canEscape = false;
            int ans = -1;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{startRow, startCol});
            while (!q.isEmpty()) {
                int[] currPos = q.poll();
                for (int dir = 0; dir < 4; ++dir) {
                    int tempRow = currPos[0] + dx[dir];
                    int tempCol = currPos[1] + dy[dir];

                    // 범위
                    if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                        canEscape = true;
                        ans = escapeTime[currPos[0]][currPos[1]] + 1;
                        break;
                    }
                    // 벽
                    if (map[tempRow][tempCol] == '#') {
                        continue;
                    }
                    // 방문
                    if (escapeVisited[tempRow][tempCol]) {
                        continue;
                    }
                    // 불
                    if ((escapeTime[currPos[0]][currPos[1]] + 1) >= fireTime[tempRow][tempCol]) {
                        continue;
                    }
                    q.add(new int[]{tempRow, tempCol});
                    escapeTime[tempRow][tempCol] = escapeTime[currPos[0]][currPos[1]] + 1;
                    escapeVisited[tempRow][tempCol] = true;
                }
                if (canEscape) {
                    break;
                }
            }
            if (canEscape) {
                sb.append(ans).append("\n");
            }else{
                sb.append("IMPOSSIBLE").append("\n");
            }
        }
        System.out.println(sb.toString().trim());
    }
}
