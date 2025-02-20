import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int idx = 0; idx < N; ++idx) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        for (int cnt = 0; cnt < M; ++cnt) {
            long num1 = pq.poll();
            long num2 = pq.poll();
            long sum = (num1 + num2);
            pq.offer(sum);
            pq.offer(sum);
        }
        long ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        System.out.println(ans);
    }
}
