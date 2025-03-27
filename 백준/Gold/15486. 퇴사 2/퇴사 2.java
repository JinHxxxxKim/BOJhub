import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Task {
        int time, pay;

        public Task(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        Task[] schedule = new Task[N];
        for (int idx = 0; idx < N; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            schedule[idx] = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] dp = new int[N + 1];
        int max = -1;
        for (int day = 0; day < N; ++day) {
            Task todayTask = schedule[day];
            int completeDay = day + todayTask.time;
            if (max < dp[day]) {
                max = dp[day];
            }
            if (completeDay > N) {
                continue;
            }

            dp[completeDay] = Math.max(dp[completeDay], max + todayTask.pay);
        }
        System.out.println(Math.max(dp[N], max));
    }
}