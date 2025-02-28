import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K, T, M;
    static int[][] scoreBoard;
    static int[] submitCnt;
    static int[] submitTime;

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < TC; ++testCase) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            scoreBoard = new int[N + 1][K + 1];
            submitCnt = new int[N + 1];
            submitTime = new int[N + 1];

            for (int time = 0; time < M; ++time) {
                st = new StringTokenizer(br.readLine().trim());
                int teamId = Integer.parseInt(st.nextToken());
                int qId = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());
                submitCnt[teamId]++;
                submitTime[teamId] = time;
                // 점수가 더 높다면 갱신
                if (scoreBoard[teamId][qId] < score) {
                    scoreBoard[teamId][qId] = score;    
                }
            }

            int ans = 0;
            int myScore = 0;
            for (int qId = 1; qId <= K; ++qId) {
                myScore += scoreBoard[T][qId];
            }
            for (int teamId = 1; teamId <= N; ++teamId) {
                if (teamId == T) {
                    continue;
                }
                int teamScore = 0;
                for (int qId = 1; qId <= K; ++qId) {
                    teamScore += scoreBoard[teamId][qId];
                }

                if (myScore < teamScore) {
                    ++ans;
                } else if (myScore == teamScore) {
                    // 제출 횟수
                    if (submitCnt[teamId] < submitCnt[T]) {
                        ++ans;
                    } else if (submitCnt[teamId] == submitCnt[T]) {
                        // 제출 시간
                        if (submitTime[teamId] < submitTime[T]) {
                            ++ans;
                        }
                    }
                }
            }
            sb.append(ans + 1).append("\n");
        }
        System.out.print(sb);
    }
}
