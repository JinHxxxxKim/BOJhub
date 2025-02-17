import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static List<boolean[][]> stickerList;
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[N][M]; // 스티커가 붙어있으면 true, 아니라면 false
        stickerList = new ArrayList<>();
        for (int cnt = 0; cnt < K; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int sRow = Integer.parseInt(st.nextToken());
            int sCol = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[sRow][sCol];
            for (int row = 0; row < sRow; ++row) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < sCol; ++col) {
                    sticker[row][col] = Integer.parseInt(st.nextToken()) == 1;
                }
            }
            stickerList.add(sticker);
        }

        // SOLUTION
        // 모든 스티커에 대해서 로직 실행
        for (int stickerNum = 0; stickerNum < K; ++stickerNum) {
            boolean[][] currSticker = stickerList.get(stickerNum);
            boolean flag = false;
            // 스티커를 90도씩 회전시켜보며 부착해본다.
            for (int dir = 0; dir < 4; ++dir) {
                boolean[][] rotateSticker = currSticker;
                for (int cnt = 0; cnt < dir; ++cnt) {
                    rotateSticker = rotate90(rotateSticker);
                }

                int stickerRowSize = rotateSticker.length;
                int stickerColSize = rotateSticker[0].length;
                // 스티커를 노트북 좌상단부터 부착해본다.
                for (int row = 0; row < (N - stickerRowSize + 1); ++row) {
                    for (int col = 0; col < (M - stickerColSize + 1); ++col) {
                        boolean canAttach = chkAttach(row, col, rotateSticker);
                        if (canAttach) {
                            // 부착 가능
                            attach(row, col, rotateSticker);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
                if (flag) break;
            }
        }

        // OUTPUT
        int ans = 0;
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                if (map[row][col]) ++ans;
            }
        }
        System.out.print(ans);
    }

    private static void attach(int row, int col, boolean[][] rotateSticker) {
        int rSize = rotateSticker.length;
        int cSize = rotateSticker[0].length;
        for (int r = 0; r < rSize; ++r) {
            for (int c = 0; c < cSize; ++c) {
                map[row + r][col + c] = rotateSticker[r][c] || map[row + r][col + c];
            }
        }
    }

    private static boolean chkAttach(int row, int col, boolean[][] rotateSticker) {
        int rSize = rotateSticker.length;
        int cSize = rotateSticker[0].length;
        for (int r = 0; r < rSize; ++r) {
            for (int c = 0; c < cSize; ++c) {
                if (map[row + r][col + c] && rotateSticker[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 90도 시계방향 회전
    private static boolean[][] rotate90(boolean[][] currSticker) {
        int rowSize = currSticker.length;
        int colSize = currSticker[0].length;
        boolean[][] rotateSticker = new boolean[colSize][rowSize];
        for (int row = 0; row < rowSize; ++row) {
            for (int col = 0; col < colSize; ++col) {
                rotateSticker[col][(rowSize - row - 1)] = currSticker[row][col];
            }
        }
        return rotateSticker;
    }
}
