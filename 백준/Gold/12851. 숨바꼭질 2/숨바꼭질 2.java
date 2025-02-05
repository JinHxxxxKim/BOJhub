import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int src, dest;
    static int ans = 0;
    static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        src = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        int[] dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(src);
        dist[src] = 0;
        while (!q.isEmpty()) {
            int currNum = q.poll();
            if (currNum == dest) {
                ++ans;
            }
            // 범위 check
            if (currNum + 1 < 100001 && dist[currNum + 1] > dist[currNum]) {
                dist[currNum + 1] = dist[currNum] + 1;
                q.offer(currNum + 1);
            }

            if (currNum - 1 >= 0 && dist[currNum - 1] > dist[currNum]) {
                dist[currNum - 1] = dist[currNum] + 1;
                q.offer(currNum - 1);
            }

            if (currNum * 2 < 100001 && dist[currNum * 2] > dist[currNum]) {
                dist[currNum * 2] = dist[currNum] + 1;
                q.offer(currNum * 2);
            }

        }
        System.out.println(dist[dest]);
        System.out.println(ans);
    }
}
