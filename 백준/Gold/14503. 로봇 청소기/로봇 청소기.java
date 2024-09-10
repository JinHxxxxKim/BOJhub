import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int N, M;
    static int cnt;
    static int[][] map;
    static boolean[][] isCleaned;

    static int currRow, currCol, currDir;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isCleaned = new boolean[N][M];

        st = new StringTokenizer(br.readLine().trim());
        currRow = Integer.parseInt(st.nextToken());
        currCol = Integer.parseInt(st.nextToken());
        currDir = Integer.parseInt(st.nextToken());

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < M; col++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 로직 시작
        cnt = 1;
        isCleaned[currRow][currCol] = true;
        solution();

        System.out.println(cnt);
    }

    private static void solution() {
        // System.out.println("=======================");
        // System.out.println("currRow = " + currRow);
        // System.out.println("currCol = " + currCol);
        // 주위 더러운 빈 칸이 있는 경우
        if (isDirty()) {
            // 반시계 회전 후 앞쪽 칸이 더러울 경우 전진
            currDir -= 1;
            if(currDir < 0) {
                currDir = 3;
            }

            if(map[currRow + dx[currDir]][currCol + dy[currDir]] != 1 && !isCleaned[currRow + dx[currDir]][currCol + dy[currDir]]){
                currRow += dx[currDir];
                currCol += dy[currDir];
                isCleaned[currRow][currCol] = true;
                ++cnt;
            }
            solution();
        }
        // 주위 더러운 빈 칸이 없는 경우
        else {
            // 후진 가능
            if(!isBehindWall()){
                // 후진
                int behindDir = (currDir + 2) % 4;
                currRow += dx[behindDir];
                currCol += dy[behindDir];
                solution();
            }
            // 후진 불가능 시 종료
        }
    }

    private static boolean isDirty() {
        for (int dir = 0; dir < 4; dir++) {
            if (map[currRow + dx[dir]][currCol + dy[dir]] == 1) {
                continue;
            }
            if (!isCleaned[currRow + dx[dir]][currCol + dy[dir]]) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBehindWall() {
        int behind = (currDir + 2) % 4;
        int tempRow = currRow + dx[behind];
        int tempCol = currCol + dy[behind];
        return map[tempRow][tempCol] == 1;
    }
}