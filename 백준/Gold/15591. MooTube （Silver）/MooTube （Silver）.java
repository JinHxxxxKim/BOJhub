import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, Q;
    static int[][] dist;
    static List<Edge>[] adList;

    static class Edge {
        int num;
        int weight;

        public Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws Exception {

        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        adList = new ArrayList[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            adList[idx] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < N - 1; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adList[node1].add(new Edge(node2, weight));
            adList[node2].add(new Edge(node1, weight));
        }
        dist = new int[N + 1][N + 1];

        // SOLUTION
        // 1번 노드부터 N번 노드까지 시작점으로 하여 최소비용(유사도)을 계산한다.
        for (int startNode = 1; startNode <= N; ++startNode) {
            boolean[] visited = new boolean[N + 1];
            Queue<Edge> q = new LinkedList<>();
            q.offer(new Edge(startNode, 0));
            visited[startNode] = true;
            while (!q.isEmpty()) {
                Edge currNode = q.poll();
                int currNodeNum = currNode.num;
                int currWeight = currNode.weight;
                // 연결노드 확인
                for (Edge next : adList[currNodeNum]) {
                    // 방문여부 검증
                    if (visited[next.num]) continue;

                    visited[next.num] = true;
                    // 현재 노드가 시작점일 경우 별도 분기처리
                    if (currWeight == 0) {
                        int minCost = next.weight;
                        q.offer(new Edge(next.num, minCost));
                        // dist 갱신
                        dist[startNode][next.num] = minCost;
                    } else {
                        // 현재 노드까지의 비용과 다음 노드로 가는 비용 중 최소값을 선택
                        int minCost = Math.min(currWeight, next.weight);
                        q.offer(new Edge(next.num, minCost));
                        // dist 갱신
                        dist[startNode][next.num] = minCost;
                    }
                }
            }
        }
        for (int qCnt = 0; qCnt < Q; ++qCnt) {
            st = new StringTokenizer(br.readLine().trim());
            int k = Integer.parseInt(st.nextToken()); // 최소유사도
            int v = Integer.parseInt(st.nextToken()); // 시작점

            int ans = 0;
            for (int targetNodeNum = 1; targetNodeNum <= N; ++targetNodeNum) {
                if (targetNodeNum == v) continue;
                if (dist[v][targetNodeNum] >= k) ++ans;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

}
