import java.io.*;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static String str;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        str = br.readLine().trim();

        map = new boolean[5][N / 5];
        int idx = 0;

        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < N / 5; ++col) {
                if (str.charAt(idx) == '#') {
                    map[row][col] = true;
                } else {
                    map[row][col] = false;
                }
                ++idx;
            }
        }


        boolean flag = false;
        for (int col = 0; col < N / 5; ++col) {
            if (!flag && map[0][col]) {
                int num = calNum(0, col);
                if (num != 1) {
                    col += 3;
                }else{
                    col += 1;
                }
                sb.append(num);
            }
        }
        System.out.println(sb);
    }

    static int calNum(int row, int col) {
        // 오른쪽으로 갈 수 있다?
        if (canMoveRight(row, col)) {
            // 0, 2, 3, 5, 6, 7, 8, 9
            if (canMoveDown(row, col)) {
                // 0, 5, 6, 8, 9
                if (!map[row + 2][col + 1]) {
                    return 0;
                } else if (map[row + 1][col + 2]) {
                    // 8, 9
                    if (map[row + 3][col]) {
                        return 8;
                    } else {
                        return 9;
                    }
                } else {
                    // 5, 6
                    if (map[row + 3][col]) {
                        return 6;
                    } else {
                        return 5;
                    }
                }
            } else {
                // 2, 3, 7
                if (map[row + 3][col]) {
                    return 2;
                } else if (map[row + 2][col]) {
                    return 3;
                } else {
                    return 7;
                }
            }
        } else {
            // 1, 4
            if (map[row + 3][col]) {
                return 1;
            } else {
                return 4;
            }
        }
    }

    static boolean canMoveRight(int row, int col) {
        if (chkRange(row, col + 1)) {
            if (map[row][col + 1]) {
                return true;
            }
        }
        return false;
    }

    static boolean canMoveDown(int row, int col) {
        if (chkRange(row + 1, col)) {
            if (map[row + 1][col]) {
                return true;
            }
        }
        return false;
    }

    static boolean chkRange(int row, int col) {
        if (row < 5 && row >= 0 && col < N / 5 && col >= 0)
            return true;
        return false;
    }
}