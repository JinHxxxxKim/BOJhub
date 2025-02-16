import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, L, K;
    static Pos[] starPos;

    public static void main(String[] args) throws Exception{
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        starPos = new Pos[K];
        for (int idx = 0; idx < K; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            starPos[idx] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(starPos);
//        System.out.println(Arrays.toString(starPos));
        // SOLUTION
        int ans = Integer.MAX_VALUE;
        // x를 기준으로 정렬한 > 트램펄린의 x좌표를 별똥별의 x좌표로 고정시킨 후 탐색
        for (int idx = 0; idx < K; ++idx) {
            int currXPos = starPos[idx].x;
            int canSave = calCanSave(idx, currXPos);
            ans = Math.min(ans, K - canSave);
        }
        System.out.println(ans);
    }

    private static int calCanSave(int startIdx, int currXPos) {
        int sum = Integer.MIN_VALUE;
        for (int idx = startIdx; idx < K; ++idx) {
            int tempSum = 0;
            if (currXPos + L < starPos[idx].x) {
                break;
            }
            int currYPos = starPos[idx].y;
            // x, y 하한선이 정해짐 (currXPos, currYPos)
            for (int tempIdx = startIdx; tempIdx < K; ++tempIdx) {
                Pos chkPos = starPos[tempIdx];
                if (currXPos + L < chkPos.x) {
                    break;
                }
                if (chkPos.x >= currXPos && chkPos.x <= currXPos + L && chkPos.y >= currYPos && chkPos.y <= currYPos + L) {
                    ++tempSum;
                }
            }
            sum = Math.max(sum, tempSum);
        }
        return sum;
    }

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos pos) {
            return this.x - pos.x;
        }

        @Override
        public String toString() {
            return "x: " + this.x + " | " + "y: " + this.y + "\n";
        }
    }
}
