import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, newScore, P;
    static int[] scores;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        newScore = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        scores = new int[P];
        Arrays.fill(scores, -1);
        if (N > 0) {
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < N; ++idx) {
                scores[idx] = Integer.parseInt(st.nextToken());
            }
        }

        // SOLUTION
        int rank = 1;
        boolean outOfRank = true;
        for(int idx = 0; idx < P; ++idx) {
            int cmpScore = scores[idx];
            if (newScore < cmpScore) {
                // 비교점수보다 작을 경우, 순위 +1
                ++rank;
            } else if (newScore > cmpScore) {
                // 비교점수보다 클 경우, 순위 확정
                outOfRank = false;
                break;
            } else {
                // 비교점수와 동일할 경우
                // 해당 점수가 끝나는 순위를 구한다.
                int endRank = idx;
                while (endRank < P && scores[endRank] == newScore) {
                    ++endRank;
                }
                if (endRank < P) {
                    outOfRank = false;
                }
                break;
            }
        }
        if (!outOfRank) {
            System.out.println(rank);
        } else {
            System.out.println(-1);
        }
    }
}
