import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int start, end;
    static List<Edge>[] adList;
    static int[] dist;
    static boolean[] visited;

    public static void main(String args[]) throws Exception{
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        adList = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int idx = 1; idx <= N; ++idx){
            adList[idx] = new ArrayList<>();
        }

        for(int cnt = 0; cnt < M; ++cnt){
            st = new StringTokenizer(br.readLine().trim());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adList[src].add(new Edge(dest, cost));
        }
        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // LOGIC
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Edge curr = pq.poll();
            if(curr.nodeNum == end) break;
            if(visited[curr.nodeNum]) continue;
            visited[curr.nodeNum] = true;
            for(Edge next : adList[curr.nodeNum]){
                if(visited[next.nodeNum]) continue;
                if(curr.cost + next.cost < dist[next.nodeNum]){
                    dist[next.nodeNum] = curr.cost + next.cost;
                    pq.offer(new Edge(next.nodeNum, dist[next.nodeNum]));
                }
            }
        }
        System.out.println(dist[end]);

    }

    static class Edge implements Comparable<Edge>{
        int nodeNum;
        int cost;
        public Edge(int nodeNum, int cost){
            this.nodeNum = nodeNum;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
}