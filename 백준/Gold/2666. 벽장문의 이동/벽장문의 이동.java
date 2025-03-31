import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int cnt;
    static int[] seq;
    static int ans;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        int pos1 = Integer.parseInt(st.nextToken());
        int pos2 = Integer.parseInt(st.nextToken());

        cnt = Integer.parseInt(br.readLine().trim());
        seq = new int[cnt];
        for (int idx = 0; idx < cnt; ++idx) {
            seq[idx] = Integer.parseInt(br.readLine().trim());
        }
        ans = Integer.MAX_VALUE;
        sol(pos1, pos2, 0, 0);
        System.out.println(ans);
    }

    static void sol(int pos1, int pos2, int currCnt, int moveCnt) {
        if (currCnt == cnt) {
            ans = Math.min(ans, moveCnt);
            return;
        }

        // pruning
        if (moveCnt >= ans) {
            return;
        }

        // pos1 이동
        sol(seq[currCnt], pos2, currCnt + 1, moveCnt + Math.abs(pos1 - seq[currCnt]));

        // pos2 이동
        sol(pos1, seq[currCnt], currCnt + 1, moveCnt + Math.abs(pos2 - seq[currCnt]));
    }
}
