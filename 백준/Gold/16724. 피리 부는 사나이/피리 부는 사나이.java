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

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        boolean[][] alreadyFind = new boolean[N][M];

        for (int row = 0; row < N; ++row) {
            String str = br.readLine().trim();
            for (int col = 0; col < M; ++col) {
                map[row][col] = str.charAt(col);
            }
        }

        // CYCLIC, ACYCLIC 구분
        int ans = 0;
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                if (visited[row][col]) {
                    continue;
                }

                int currRow = row;
                int currCol = col;
                Queue<int[]> q = new LinkedList<>();
                while (!visited[currRow][currCol]) {
                    visited[currRow][currCol] = true;
                    q.offer(new int[]{currRow, currCol});
                    switch (map[currRow][currCol]) {
                        case 'U':
                            currRow = currRow - 1;
                            break;
                        case 'D':
                            currRow = currRow + 1;
                            break;
                        case 'L':
                            currCol = currCol - 1;
                            break;
                        case 'R':
                            currCol = currCol + 1;
                            break;
                    }
                }
                if (!alreadyFind[currRow][currCol]) {
                    ++ans;
                }
                for (int[] info : q) {
                    alreadyFind[info[0]][info[1]] = true;
                }
            }
        }
        System.out.println(ans);
    }
}
