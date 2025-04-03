import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for (int num = 1; num <= N; ++num) {
            dq.offerLast(num);
        }
        int[] pollNum = new int[M];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < M; ++idx) {
            pollNum[idx] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int idx = 0; idx < M; ++idx) {
            int tgtNum = pollNum[idx];

            int aCnt = 0;
            int bCnt = 0;
            ArrayDeque<Integer> aDq = new ArrayDeque<>(dq);
            ArrayDeque<Integer> bDq = new ArrayDeque<>(dq);
            while (aDq.peekFirst() != tgtNum) {
                aDq.offerFirst(aDq.pollLast());
                ++aCnt;
            }
            while (bDq.peekFirst() != tgtNum) {
                bDq.offerLast(bDq.pollFirst());
                ++bCnt;
            }

            if (aCnt < bCnt) {
                dq = new ArrayDeque<>(aDq);
                ans += aCnt;
            } else {
                dq = new ArrayDeque<>(bDq);
                ans += bCnt;
            }
            dq.pollFirst();
        }
        System.out.println(ans);
    }
}
