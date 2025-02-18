import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX_DIST = 2;

    static int N, M;
    static List<Integer>[] adList;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws Exception{
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        adList = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];

        for (int idx = 1; idx <= N; ++idx) {
            adList[idx] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < M; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            adList[node1].add(node2);
            adList[node2].add(node1);
        }

        // SOLUTION
        // 자신의 친구와 친구의 친구를 초대 > dist가 최대 2인 node
        int ans = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int currNode = q.poll();
            ++ans;
            // 친구의 친구일 경우 더 확인할 필요 X
            if(dist[currNode] == MAX_DIST)
                continue;

            for (int nextNode : adList[currNode]) {
                if (!visited[nextNode]) {
                    dist[nextNode] = dist[currNode] + 1;
                    visited[nextNode] = true;
                    q.offer(nextNode);
                }
            }
        }

        // OUTPUT 
        // 자기자신 제외
        System.out.print(ans - 1);
    }
}