import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        board = new char[N + 1][N + 1];

        // 머리의 위치를 구한다.
        boolean headFind = false;
        int headRow = -1;
        int headCol = -1;
        for (int row = 1; row <= N; ++row) {
            String rowInfo = br.readLine().trim();
            for (int col = 1; col <= N; ++col) {
                board[row][col] = rowInfo.charAt(col - 1);
                if (!headFind && board[row][col] == '*') {
                    headFind = true;
                    headRow = row;
                    headCol = col;
                }
            }
        }

        // 머리 위치로부터 시작
        int heartRow = headRow + 1;
        int heartCol = headCol;
        int lArmLen = 0;
        int rArmLen = 0;
        int waistLen = 0;
        int lLegLen = 0;
        int rLegLen = 0;

        for (int col = heartCol - 1; col >= 1; --col) {
            if (board[heartRow][col] == '*') {
                ++lArmLen;
            } else {
                break;
            }
        }

        for (int col = heartCol + 1; col <= N; ++col) {
            if (board[heartRow][col] == '*') {
                ++rArmLen;
            } else {
                break;
            }
        }

        int endOfWaistRow = 0;
        for (int row = heartRow + 1; row <= N; ++row) {
            if (board[row][heartCol] == '*') {
                ++waistLen;
                endOfWaistRow = row;
            } else {
                break;
            }
        }

        for (int row = endOfWaistRow + 1; row <= N; ++row) {
            if (board[row][heartCol - 1] == '*') {
                ++lLegLen;
            } else {
                break;
            }
        }

        for (int row = endOfWaistRow + 1; row <= N; ++row) {
            if (board[row][heartCol + 1] == '*') {
                ++rLegLen;
            } else {
                break;
            }
        }
        sb.append(heartRow).append(" ").append(heartCol).append("\n");
        sb.append(lArmLen).append(" ").append(rArmLen).append(" ").append(waistLen).append(" ").append(lLegLen).append(" ").append(rLegLen);
        System.out.print(sb);
    }
}
