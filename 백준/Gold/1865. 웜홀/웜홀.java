import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Edge{
        int num, cost;
        public Edge(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge>[] adList = new ArrayList[N + 1];
            for (int node = 1; node <= N; ++node) {
                adList[node] = new ArrayList<>();
            }

            // 인접 리스트 초기화
            for (int edge = 0; edge < M; ++edge) {
                st = new StringTokenizer(br.readLine().trim());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adList[node1].add(new Edge(node2, cost));
                adList[node2].add(new Edge(node1, cost));
            }

            // 웜홀 연결
            for (int edge = 0; edge < W; ++edge) {
                st = new StringTokenizer(br.readLine().trim());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                adList[node1].add(new Edge(node2, (-1) * cost));
            }

            // 벨만포드(음의 사이클 발생 == YES)
            // 1번 노드를 시작점으로 설정
            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            for (int startNode = 1; startNode <= N; ++startNode) {
                if (dist[startNode] != Integer.MAX_VALUE) {
                    continue;
                }
                dist[startNode] = 0;
                for (int cnt = 1; cnt <= N; ++cnt) {
                    // 모든 간선 확인
                    for (int node = 1; node <= N; ++node) {
                        if (dist[node] == Integer.MAX_VALUE) {
                            continue;
                        }
                        for (Edge edge : adList[node]) {
                            int tempCost = dist[node] + edge.cost;
                            int nextNode = edge.num;
                            // 갱신 여부 확인
                            if (tempCost < dist[nextNode]) {
                                dist[nextNode] = tempCost;
                            }
                        }
                    }
                }
            }

            // 음의 사이클 확인
            boolean hasCycle = false;
            for (int node = 1; node <= N; ++node) {
                // 현재 노드의 모든 간선 확인
                for (Edge edge : adList[node]) {
                    int tempCost = dist[node] + edge.cost;
                    int nextNode = edge.num;
                    // 갱신 여부 확인
                    if (tempCost < dist[nextNode]) {
                        dist[nextNode] = tempCost;
                        hasCycle = true;
                        break;
                    }
                }
                if (hasCycle) {
                    break;
                }
            }
            if (hasCycle) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
// 5, 500, 2500 > 2500 * 2500 = 6,250,000 25 * 25 =