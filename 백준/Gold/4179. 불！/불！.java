import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[][] map;
    static int R, C;
    static int[][] fireTime;
    static int[][] escapeTime;
    static boolean[][] visited;
    static int startRow, startCol;
    static List<int[]> fireList;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireTime = new int[R][C];
        for (int[] fireRow : fireTime) {
            Arrays.fill(fireRow, Integer.MAX_VALUE);
        }

        escapeTime = new int[R][C];
        for (int[] row : escapeTime) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        fireList = new ArrayList<>();

        for (int row = 0; row < R; ++row) {
            String string = br.readLine().trim();
            for (int col = 0; col < C; ++col) {
                map[row][col] = string.charAt(col);
                if (map[row][col] == 'J') {
                    startRow = row;
                    startCol = col;
                    map[row][col] = '.';
                } else if (map[row][col] == 'F') {
                    fireList.add(new int[]{row, col});
                }
            }
        }

        // SOLUTION
        // 모든 불에 대해 BFS > 모든 지점까지 불이 도달하는 최소 시간을 구한다.
        for (int[] currFire : fireList) {
            visited = new boolean[R][C];
            Queue<int[]> q = new LinkedList<>();
            q.offer(currFire);
            fireTime[currFire[0]][currFire[1]] = 0;
            visited[currFire[0]][currFire[1]] = true;
            while (!q.isEmpty()) {
                int[] currNode = q.poll();
                for(int dir = 0; dir < 4; ++dir){
                    int nextRow = currNode[0] + dx[dir];
                    int nextCol = currNode[1] + dy[dir];
                    if(nextRow >= 0 && nextCol >= 0 && nextRow < R && nextCol < C && !visited[nextRow][nextCol] && map[nextRow][nextCol] != '#') {
                        q.offer(new int[]{nextRow, nextCol});
                        fireTime[nextRow][nextCol] = Math.min(fireTime[nextRow][nextCol], fireTime[currNode[0]][currNode[1]] + 1);
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        // 탈출 시작
        boolean canEscape = false;
        int ans = Integer.MAX_VALUE;
        visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        escapeTime[startRow][startCol] = 0;
        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            for(int dir = 0; dir < 4; ++dir){
                int nextRow = currNode[0] + dx[dir];
                int nextCol = currNode[1] + dy[dir];

                // 탈출여부 확인
                if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C){
                    ans = escapeTime[currNode[0]][currNode[1]] + 1;
                    canEscape = true;
                    break;
                }
                if(nextRow >= 0 && nextCol >= 0 && nextRow < R && nextCol < C && !visited[nextRow][nextCol] && map[nextRow][nextCol] != '#' && escapeTime[currNode[0]][currNode[1]] + 1 < fireTime[nextRow][nextCol]) {
                    q.offer(new int[]{nextRow, nextCol});
                    escapeTime[nextRow][nextCol] = Math.min(escapeTime[nextRow][nextCol], escapeTime[currNode[0]][currNode[1]] + 1);
                    visited[nextRow][nextCol] = true;
                }
            }
            if(canEscape)
                break;
        }

        // OUTPUT
        if (canEscape){
            System.out.print(ans);
        }else{
            System.out.print("IMPOSSIBLE");
        }
    }
}
