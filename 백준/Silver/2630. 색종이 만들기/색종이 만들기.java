import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;
    private static int whiteCnt;
    private static int blueCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, N, 0, N);
        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }

    private static void DFS(int rowStart, int rowEnd, int colStart, int colEnd) {
        int color = whatColor(rowStart, rowEnd, colStart, colEnd);
        if (color == -1) {
            int rowMid = (rowStart + rowEnd)/2;
            int colMid = (colStart + colEnd) / 2;
            // 좌상단
            DFS(rowStart, rowMid, colStart, colMid);
            // 우상단
            DFS(rowStart, rowMid, colMid, colEnd);
            // 좌하단
            DFS(rowMid, rowEnd, colStart, colMid);
            // 우하단
            DFS(rowMid, rowEnd, colMid, colEnd);
        } else if (color == 1) {
            ++blueCnt;
        } else if (color == 0) {
            ++whiteCnt;
        }
    }

    private static int whatColor(int rowStart, int rowEnd, int colStart, int colEnd) {
        // 하나의 색이 아니라면 -1 반환
        // 어떤 색인지 반환
        int color = board[rowStart][colStart];
        for (int row = rowStart; row < rowEnd; ++row) {
            for (int col = colStart; col < colEnd; ++col) {
                if (board[row][col] != color) {
                    return -1;
                }
            }
        }
        return color;
    }
}