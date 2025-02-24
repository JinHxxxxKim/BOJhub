import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[][] map;
    static List<Pos> houses;
    static List<Pos> stores;
    static Pos[] selectStore;

    static int ans;

    static class Pos{
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        houses = new ArrayList<>();
        stores = new ArrayList<>();
        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 2) {
                    stores.add(new Pos(row, col));
                } else if (map[row][col] == 1) {
                    houses.add(new Pos(row, col));
                }
            }
        }

        //SOLUTION
        selectStore = new Pos[M];
        ans = Integer.MAX_VALUE;
        comb(0, 0);

        // OUTPUT
        System.out.print(ans);
    }

    static void comb(int selectCnt, int selectIdx) {
        // 기저조건 1
        if (selectCnt == M) {
            // 치킨거리 계산
            int tempDist = 0;
            for (Pos house : houses) {
                int houseDist = Integer.MAX_VALUE;
                for (Pos store : selectStore) {
                    int temp = Math.abs(house.row - store.row) + Math.abs(house.col - store.col);
                    houseDist = Math.min(houseDist, temp);
                }
                tempDist += houseDist;
            }
            ans = Math.min(tempDist, ans);
            return;
        }

        // 기저조건 2
        if (selectIdx >= stores.size()) {
            return;
        }

        // 선택 O
        selectStore[selectCnt] = stores.get(selectIdx);
        comb(selectCnt + 1, selectIdx + 1);

        // 선택 X
        comb(selectCnt, selectIdx + 1);
    }
}
