import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int N, M;
    static boolean[][] isLight;
    static boolean[][] isVisited;
    static LinkedList<int[]>[][] on;

    public static void main(String[] args) throws Exception{
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isLight = new boolean[N][N];
        isVisited = new boolean[N][N];
        on = new LinkedList[N][N];
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                on[row][col] = new LinkedList<>();
            }
        }

        for (int cnt = 0; cnt < M; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            on[a - 1][b - 1].add(new int[]{c - 1, d - 1});
        }

        // SOLUTION
        int ans = 1;
        Queue<int[]> q = new LinkedList<>();
        isVisited[0][0] = true;
        isLight[0][0] = true;
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] currPos = q.poll();
            boolean needReset = false;
            if (!on[currPos[0]][currPos[1]].isEmpty()) {
                // 불 킬 수 있는 방이 있는 경우 > 불 키고 다시 시작
                for (int[] roomPos : on[currPos[0]][currPos[1]]) {
                    if(!isLight[roomPos[0]][roomPos[1]]){
                        ++ans;
                        isLight[roomPos[0]][roomPos[1]] = true;
                        needReset = true;
                    }
                }
            }
            // 불 킬 수 있는 방이 없는 경우
            for (int dir = 0; dir < 4; ++dir) {
                int tempRow = currPos[0] + dx[dir];
                int tempCol = currPos[1] + dy[dir];

                // 범위
                if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= N) {
                    continue;
                }
                // 불
                if (!isLight[tempRow][tempCol]) {
                    continue;
                }
                // 방문
                if (isVisited[tempRow][tempCol]) {
                    continue;
                }
                q.offer(new int[]{tempRow, tempCol});
                isVisited[tempRow][tempCol] = true;
            }


            // 불 킨 방이 하나라도 있을 경우, 해당 위치에서 다시 시작
            if (needReset) {
                q.clear();
                isVisited = new boolean[N][N];
                isVisited[currPos[0]][currPos[1]] = true;
                q.offer(new int[]{currPos[0], currPos[1]});
            }
        }
        System.out.println(ans);
    }
}
